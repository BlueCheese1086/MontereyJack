package frc.robot.autonomous.sections;

import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.FieldMap;
import frc.robot.Robot;
import frc.robot.mechanisms.*;
import frc.robot.sensors.Limelight;

public class Fire extends AutoSection {

    Hopper hopper;
    Feeder feeder;
    Turret turret;
    Limelight camera;
    double angle;
    double groundDistance;
    double velocity;
    int counter;
    int count;
    boolean tester;

    public Fire(int count) {
        super(0);
        hopper = Robot.hopper;
        feeder = Robot.feeder;
        turret = Robot.turret;
        camera = Robot.camera;
        angle = 0;
        groundDistance = 0;
        velocity = Constants.LAUNCHER_DEFAULT_VELOCITY;
        counter = 0;
        this.count = count;
        tester = false;
    }

    public Fire(int count, double timeout) {
        super(timeout);
        hopper = Robot.hopper;
        feeder = Robot.feeder;
        turret = Robot.turret;
        camera = Robot.camera;
        angle = 0;
        groundDistance = 0;
        velocity = Constants.LAUNCHER_DEFAULT_VELOCITY;
        counter = 0;
        this.count = count;
        tester = false;
    }

    @Override
    public void init() {
        super.init();
        groundDistance = camera.getGroundDistance(FieldMap.POWERPORT_TARGET_HEIGHT);
    }

    @Override
    public void update() {

        feeder.setKickerSpeed(Constants.FEEDER_DEFAULT_VELOCITY);

        camera.setCameraMode(0);
        camera.setLights(3);
        turret.setTurretPosition(0, camera.getXAngle());
        angle = Turret.findDesiredAngle(groundDistance, FieldMap.POWERPORT_CENTER_HEIGHT, Constants.LAUNCHER_DEFAULT_VELOCITY);
        angle *= 0.75;
        clampAngle();
        turret.setLauncherSpeed(Turret.appliedVelocity(velocity));
        turret.setHoodPosition(angle);

        if (feeder.ballDetected()) {
            tester = true;
        }
        if (tester && !feeder.ballDetected()) {
            tester = false;
            counter++;
        }
            
        if (turret.getLauncherSpeed() > turret.getDesiredLauncherSpeed() / Constants.LAUNCHER_VELOCITY_SCALE * 0.9) {

            feeder.setElevatorCurrent(1);

        } else if (!feeder.ballDetected()) {
            feeder.setElevatorCurrent(0.7);
            hopper.setLeftCurrent(0.7);
            hopper.setRightCurrent(0.7);
        } else {
            feeder.setElevatorCurrent(0);
            hopper.setLeftCurrent(0);
            hopper.setRightCurrent(0);
        }
    }

    @Override
    public void disabled() {
        //camera.setCameraMode(1);
        //camera.setLights(1);
        turret.setLauncherCurrent(0);
        turret.setTurretCurrent(0);
        turret.setHoodCurrent(0);
        hopper.setLeftCurrent(0);
        hopper.setRightCurrent(0);
        feeder.setElevatorCurrent(0);
        feeder.setKickerCurrent(0);
    }

    @Override
    public boolean disableCondition() {
        return counter >= count || super.disableCondition();
    }

    public void clampAngle() {
        angle = MathUtil.clamp(angle, Constants.HOOD_MIN_POSITION, Constants.HOOD_MAX_POSITION);
        if (angle == Constants.HOOD_MIN_POSITION || angle == Constants.HOOD_MAX_POSITION) {
            velocity = Turret.findDesiredVelocity(groundDistance, FieldMap.POWERPORT_CENTER_HEIGHT, angle);
        }
    }
    
}
