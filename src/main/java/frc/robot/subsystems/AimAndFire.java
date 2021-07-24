package frc.robot.subsystems;

import edu.wpi.first.wpilibj.util.Units;
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
    double lastGroundDistance;
    boolean run;

    public AimAndFire() {
        turret = Robot.turret;
        camera = Robot.camera; 
        c = Robot.controls;
        velocity = Constants.LAUNCHER_DEFAULT_VELOCITY;
        groundDistance = 0;
        angle = Constants.HOOD_MIN_POSITION;
        run = false;
    }

    @Override
    public void update() {

        System.out.println(turret.getTurretPosition());

        if (c.getStartLaunch()) {
            camera.setCameraMode(0);
            camera.setLights(3);
        }

        if (c.getLaunch()) {

            if (camera.getYAngle() != 0 && !run) {
                groundDistance = camera.getGroundDistance(FieldMap.POWERPORT_TARGET_HEIGHT);
                run = true;
            }
            if (run) {
                turret.setTurretPosition(0, camera.getXAngle());
                angle = Turret.findDesiredAngle(groundDistance, FieldMap.POWERPORT_CENTER_HEIGHT, Constants.LAUNCHER_DEFAULT_VELOCITY);
                angle *= 0.8;
                clampAngle();
                turret.setLauncherSpeed(Turret.appliedVelocity(velocity));
                turret.setHoodPosition(angle);
            }

        } else if (c.revLauncher()) {

            turret.setLauncherSpeed(Turret.appliedVelocity(velocity));

        } else {
            run = false;
            camera.setCameraMode(1);
            camera.setLights(1);
            turret.setLauncherCurrent(0);
            turret.setHoodCurrent(0);
            turret.setTurretCurrent(0);
            turret.setTurretPosition(0, -turret.getTurretPosition());

            velocity = Constants.LAUNCHER_DEFAULT_VELOCITY;

        }
        
    }

    public void clampAngle() {
        angle = MathUtil.clamp(angle, Constants.HOOD_MIN_POSITION, Constants.HOOD_MAX_POSITION);
        if (Double.isNaN(angle)) {
            angle = Constants.HOOD_MIN_POSITION;
        }
        if (angle == Constants.HOOD_MIN_POSITION || angle == Constants.HOOD_MAX_POSITION) {
            velocity = Math.min(10.5, Turret.findDesiredVelocity(groundDistance, FieldMap.POWERPORT_CENTER_HEIGHT, angle));
        }
    }
    
}
