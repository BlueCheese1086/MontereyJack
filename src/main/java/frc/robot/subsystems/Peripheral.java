package frc.robot.subsystems;

import frc.robot.Controls;
import frc.robot.Robot;
import frc.robot.mechanisms.Climber;
import frc.robot.mechanisms.Intake;

public class Peripheral extends Subsystem {
    
    Climber climber;
    Intake intake;
    Controls c;

    public Peripheral() {

        climber = Robot.climber;
        intake = Robot.intake;
        c = Robot.controls;

    }

    @Override
    public void update() {
        
        if (c.getToggleIntake()) intake.togglePosition();
        if (c.getIntake()) {
            intake.setCurrent(0.8);
        } else if (c.getOuttake()) {
            intake.setCurrent(-0.8);
        }
        if (c.getLeftClimber() > 0.2) {
            climber.setLeftLock(true);
            climber.setLeftCurrent(c.getLeftClimber());
        } else {
            climber.setLeftLock(false);
        }
        if (c.getRightClimber() > 0.2) {
            climber.setRightLock(true);
            climber.setRightCurrent(c.getRightClimber());
        } else {
            climber.setRightLock(false);
        }

    }
    
}
