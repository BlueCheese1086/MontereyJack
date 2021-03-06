package frc.robot.subsystems;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.Controls;
import frc.robot.FieldMap;
import frc.robot.Robot;
import frc.robot.mechanisms.Drivetrain;
import frc.robot.mechanisms.Turret;
import frc.robot.sensors.Gyro;
import frc.robot.sensors.Limelight;

public class Odometry extends Subsystem {

    Drivetrain drivetrain;
    Turret turret;
    Gyro gyro;
    Controls c;
    Limelight camera;

    public Odometry(Pose2d pose) {
        c = Robot.controls;
        drivetrain = Robot.drivetrain;
        turret = Robot.turret;
        gyro = Robot.gyro;
        camera = Robot.camera;
        drivetrain.setOdometry(pose, new Rotation2d(-gyro.getYaw()));
    }

    @Override
    public void update() {

        drivetrain.updateOdometry(drivetrain.getLeftPos(), drivetrain.getRightPos(), new Rotation2d(-gyro.getYaw()));
        
    }
    
}
