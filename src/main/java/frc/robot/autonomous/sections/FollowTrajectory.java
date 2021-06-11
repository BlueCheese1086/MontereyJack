package frc.robot.autonomous.sections;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.mechanisms.Drivetrain;
import frc.robot.sensors.Gyro;

public class FollowTrajectory extends AutoSection {

    Trajectory trajectory;
    Drivetrain drivetrain;
    RamseteController controller;
    Pose2d savedPose;
    Gyro gyro;

    public FollowTrajectory(Trajectory trajectory) {
        super(trajectory.getTotalTimeSeconds());
        this.trajectory = trajectory;
        this.gyro = Robot.gyro;
        drivetrain = Robot.drivetrain;
        controller = new RamseteController(Constants.RAMSETE_B, Constants.RAMSETE_ZETA);
    }

    @Override
    public void init() {
        super.init();
        this.savedPose = drivetrain.getCoordinates();
        drivetrain.setOdometry(new Pose2d(0, 0, new Rotation2d()), Rotation2d.fromDegrees(gyro.getYaw()));
    }

    @Override
    public void update() {
        double time = (System.currentTimeMillis() - startTime) / 1000.0;
        Trajectory.State goal = trajectory.sample(time);
        Pose2d pose = drivetrain.getCoordinates().transformBy(new Transform2d(new Translation2d(), Rotation2d.fromDegrees(gyro.getYaw())));
        ChassisSpeeds speeds = controller.calculate(pose, goal);
        DifferentialDriveWheelSpeeds wheelSpeeds = drivetrain.getKinematics().toWheelSpeeds(speeds);
        drivetrain.setSpeeds(wheelSpeeds);
    }

    @Override
    public void disabled() {
        drivetrain.setSpeeds(new DifferentialDriveWheelSpeeds(0, 0));
        drivetrain.setOdometry(savedPose.transformBy(new Transform2d(new Translation2d(drivetrain.getCoordinates().getX(), drivetrain.getCoordinates().getY()), new Rotation2d())), Rotation2d.fromDegrees(gyro.getYaw()));
    }
    
}
