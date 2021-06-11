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
        return leftStick.getY();
    }

    public double getDriveTurn() {
        return rightStick.getX();
    }

    public boolean getSafety() {
        return leftStick.getTrigger();
    }

    public boolean getLaunch() {
        return controller.getRawButton(0);
    }

    public double getLeftClimber() {
        return controller.getRawAxis(0);
    }

    public double getRightClimber() {
        return controller.getRawAxis(0);
    }

    public boolean getToggleIntake() {
        return controller.getRawButtonPressed(0);
    }

    public boolean getIntake() {
        return controller.getRawButton(0);
    }

    public boolean getOuttake() {
        return controller.getRawButton(0);
    }

}
