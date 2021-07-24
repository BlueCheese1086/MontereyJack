package frc.robot.subsystems;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import frc.robot.Controls;
import frc.robot.Robot;
import frc.robot.mechanisms.Drivetrain;

public class Drive extends Subsystem {

    Controls c;
    Drivetrain drivetrain;

    public Drive() {
        c = Robot.controls;
        drivetrain = Robot.drivetrain;
        drivetrain.setOdometry(new Pose2d(0, 0, new Rotation2d()), new Rotation2d(Robot.gyro.getYaw()));
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
