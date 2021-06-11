package frc.robot.autonomous.sections;

import frc.robot.Robot;
import frc.robot.mechanisms.Intake;

public class RunIntake extends AutoSection {

    Intake intake;

    public RunIntake(double length) {
        super(length);
        this.intake = Robot.intake;
    }

    @Override
    public void init() {
        intake.setPosition(true);
    }

    @Override
    public void update() {
        intake.setCurrent(1);
    }

    @Override
    public void disabled() {
        intake.setCurrent(0);
        intake.setPosition(false);
    }
    
}
