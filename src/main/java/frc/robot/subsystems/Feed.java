package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Controls;
import frc.robot.Robot;
import frc.robot.mechanisms.Feeder;
import frc.robot.mechanisms.Hopper;
import frc.robot.mechanisms.Turret;

public class Feed extends Subsystem {

    Controls c;
    Hopper hopper;
    Feeder feeder;
    Turret turret;
    double timer;
    boolean go;

    public Feed() {
        c = Robot.controls;
        hopper = Robot.hopper;
        feeder = Robot.feeder;
        turret = Robot.turret;
        timer = 0;
        go = false;
    }

    @Override
    public void update() {
        
        if (c.getLaunch()) {

            hopper.setLeftCurrent(0.7);
            hopper.setRightCurrent(0.7);
            
            feeder.setKickerSpeed(Constants.FEEDER_DEFAULT_VELOCITY);

            if (turret.getLauncherSpeed() > turret.getDesiredLauncherSpeed() / Constants.LAUNCHER_VELOCITY_SCALE * 0.9) {

                feeder.setElevatorCurrent(1);

            } else if (!feeder.ballDetected()) {
                feeder.setElevatorCurrent(0.7);
            } else {
                feeder.setElevatorCurrent(0);
            }

        } else if (c.getSafety()) {

            if (!feeder.ballDetected() && timer <= 50) {
                timer++;
                feeder.setElevatorCurrent(0.7);
            } else {
                timer = 0;
                feeder.setElevatorCurrent(0);
                hopper.setLeftCurrent(0.7);
                hopper.setRightCurrent(0.7);
            }
            feeder.setKickerCurrent(0);
            
        } else {
            feeder.setElevatorCurrent(0);
            hopper.setLeftCurrent(0);
            hopper.setRightCurrent(0);
            feeder.setKickerCurrent(0);
            go = false;
        }

    }    

}
