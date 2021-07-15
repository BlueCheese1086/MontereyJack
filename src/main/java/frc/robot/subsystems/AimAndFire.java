package frc.robot.subsystems;

import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.Controls;
import frc.robot.FieldMap;
import frc.robot.Robot;
import frc.robot.mechanisms.Turret;
import frc.robot.sensors.Limelight;

public class AimAndFire extends Subsystem {

    Turret turret;
    Limelight camera;
    Controls c;
    double velocity;
    double groundDistance;
    double angle;

    public AimAndFire() {
        turret = Robot.turret;
        camera = Robot.camera; 
        c = Robot.controls;
        velocity = Constants.LAUNCHER_DEFAULT_VELOCITY;
        groundDistance = 0;
        angle = Constants.HOOD_MIN_POSITION;
    }

    @Override
    public void update() {

        if (c.getLaunch()) {

            camera.setCameraMode(0);
            camera.setLights(3);
            turret.setTurretPosition(0, camera.getXAngle());
            groundDistance = camera.getGroundDistance(FieldMap.POWERPORT_TARGET_HEIGHT);
            angle = Turret.findDesiredAngle(groundDistance, FieldMap.POWERPORT_CENTER_HEIGHT, Constants.LAUNCHER_DEFAULT_VELOCITY);
            clampAngle();
            turret.setLauncherSpeed(Turret.appliedVelocity(velocity));
            turret.setHoodPosition(angle);

        } else {

            camera.setCameraMode(1);
            camera.setLights(1);
            turret.setLauncherCurrent(0);
            turret.setHoodCurrent(0);

        }
        
    }

    public void clampAngle() {
        angle = MathUtil.clamp(angle, Constants.HOOD_MIN_POSITION, Constants.HOOD_MAX_POSITION);
        if (angle == Constants.HOOD_MIN_POSITION || angle == Constants.HOOD_MAX_POSITION) {
            velocity = Turret.findDesiredVelocity(groundDistance, FieldMap.POWERPORT_CENTER_HEIGHT, angle);
        }
    }
    
}
