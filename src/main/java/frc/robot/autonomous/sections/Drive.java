package frc.robot.autonomous.sections;

import frc.robot.Robot;
import frc.robot.mechanisms.Drivetrain;

public class Drive extends AutoSection {

    Drivetrain drivetrain;
    double current;

    public Drive(double length, double current) {
        super(length);
        drivetrain = Robot.drivetrain;
        this.current = current;
    }

    @Override
    public void update() {
        drivetrain.drive(current, 0);
    }

    @Override
    public void disabled() {
        drivetrain.drive(0,0);
    }
    
}
