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
        return rightStick.getRawButton(2);
    }
    
    public boolean getStartLaunch() {
        return rightStick.getRawButtonPressed(2);
    }

    public double getLeftClimber() {
        return controller.getRawAxis(1);
    }

    public double getRightClimber() {
        return controller.getRawAxis(3);
    }

    public boolean getToggleIntake() {
        return controller.getRawButtonPressed(1);
    }

    public boolean getIntake() {
        return controller.getPOV() == 180;
    }

    public boolean getOuttake() {
        return controller.getPOV() == 0;
    }

    public boolean revLauncher() {
        return controller.getRawButton(7);
    }

    public boolean getHopperOut() {
        return controller.getRawButton(8);
    }

    public boolean getHopperIn() {
        return controller.getRawButton(6);
    }
}
