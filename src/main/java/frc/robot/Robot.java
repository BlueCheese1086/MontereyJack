// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.sensors.*;
import frc.robot.subsystems.*;
import frc.robot.autonomous.AutoManager;
import frc.robot.autonomous.AutoMode;
import frc.robot.mechanisms.*;

public class Robot extends TimedRobot {

  public static Controls controls;
  public static Gyro gyro;
  public static Limelight camera;
  public static Climber climber;
  public static Drivetrain drivetrain;
  public static Feeder feeder;
  public static Hopper hopper;
  public static Intake intake;
  public static Turret turret;
  Compressor compressor;
  ArrayList<Subsystem> subsystems;
  AutoManager autonomousManager;
  AutoMode autoMode;

  @Override
  public void robotInit() {
    controls = new Controls();
    gyro = new Gyro();
    camera = new Limelight();
    climber = new Climber();
    drivetrain = new Drivetrain();
    feeder = new Feeder();
    hopper = new Hopper();
    intake = new Intake();
    turret = new Turret();
    compressor = new Compressor(0);
    compressor.setClosedLoopControl(true);
    subsystems = new ArrayList<Subsystem>();
    autonomousManager = new AutoManager();
    subsystems.addAll(Arrays.asList(new AimAndFire(), new Feed(), new Peripheral(), new Drive()));
    autoMode = autonomousManager.getAuto();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    autoMode.update();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    for (Subsystem s : subsystems) s.update();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }
}
