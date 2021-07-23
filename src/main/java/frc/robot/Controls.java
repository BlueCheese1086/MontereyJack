package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {
    
    Joystick leftStick, rightStick, controller;

    public Controls() {
        leftStick = new Joystick(0);
        rightStick = new Joystick(1);
        controller = new Joystick(5);
    }

    public double getDriveForward() {
        return -leftStick.getY() * Math.abs(leftStick.getY());
    }

    public double getDriveTurn() {
        return rightStick.getX() * Math.abs(rightStick.getX());
    }

    public boolean getSafety() {
        return leftStick.getTrigger();
    }

    public boolean getLaunch() {
        return controller.getRawButton(2);
    }
    
    public boolean getStartLaunch() {
        return controller.getRawButtonPressed(2);
    }

    public double getLeftClimber() {
        return controller.getRawAxis(1);
    }

    public double getRightClimber() {
        return controller.getRawAxis(3);
    }

    public boolean getToggleIntake() {
        return controller.getRawButtonPressed(3);
    }

    public boolean getIntake() {
        return controller.getRawButton(4);
    }

    public boolean getOuttake() {
        return controller.getRawButton(5);
    }

    public boolean revLauncher() {
        return controller.getRawButton(6);
    }

}
