package frc.robot.subsystems;

import frc.robot.Controls;
import frc.robot.Robot;
import frc.robot.mechanisms.Drivetrain;

public class Drive extends Subsystem {

    Controls c;
    Drivetrain drivetrain;

    public Drive() {
        c = Robot.controls;
        drivetrain = Robot.drivetrain;
    }

    @Override
    public void update() {

        if (c.getSafety()) {
            drivetrain.drive(c.getDriveForward(), c.getDriveTurn());
        } else {
            drivetrain.drive(0, 0);
        }

    }

}
