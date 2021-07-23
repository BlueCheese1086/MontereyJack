package frc.robot.autonomous;

import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryParameterizer;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.autonomous.sections.*;
import frc.robot.mechanisms.Drivetrain;

public class AutoManager {

    SendableChooser autoChooser;
    AutoMode auto1, auto2, auto3;
    Drivetrain drivetrain;
    
    public AutoManager() {

        autoChooser = new SendableChooser<AutoMode>();
        drivetrain = Robot.drivetrain;

        TrajectoryConfig config = new TrajectoryConfig(Constants.MP_MAX_VELOCITY, Constants.MP_MAX_ACCELERATION).setKinematics(drivetrain.getKinematics());

        auto1 = new AutoMode("3ball");
        auto1.addSection(new Drive(0.4, 0.15), 0);
        auto1.addSection(new Fire(3), 0);

        auto2 = new AutoMode("trench");
        auto2.addSection(new Fire(3), 0);
        auto2.addSection(new FollowTrajectory(TrajectoryGenerator.generateTrajectory(new Pose2d(0, 0, new Rotation2d()), Arrays.asList(
            new Translation2d(2.5, 2)
        ), new Pose2d(2.5, 3.5, new Rotation2d()), config)), 0);
        auto2.addSection(new Wait(4), 1);
        auto2.addSection(new RunIntake(5), 1);

        auto3 = new AutoMode("test");
        auto2.addSection(new FollowTrajectory(TrajectoryGenerator.generateTrajectory(new Pose2d(0, 0, new Rotation2d()), Arrays.asList(

        ), new Pose2d(0, 2, new Rotation2d()), config)), 0);
 

        autoChooser.setDefaultOption(auto1.getName(), auto1);
        autoChooser.addOption(auto2.getName(), auto2);
        autoChooser.addOption(auto3.getName(), auto3);

        SmartDashboard.putData("Auto Mode Selector", autoChooser);

    }

    public AutoMode getAuto() {
        return (AutoMode)autoChooser.getSelected();
    }
}
