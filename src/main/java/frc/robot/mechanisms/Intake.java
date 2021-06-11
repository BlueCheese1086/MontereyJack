package frc.robot.mechanisms;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import frc.robot.components.Talon;
import frc.robot.components.TalonType;

public class Intake {
    
    Solenoid setter;
    Talon driver;
    boolean setterPosition;

    public Intake() {
        setter = new Solenoid(RobotMap.INTAKE_SOLENOID);
        driver = new Talon(RobotMap.INTAKE_MOTOR, TalonType.SRX);
        setterPosition = false;
    }

    public void setPosition(boolean position) {
        setterPosition = position;
        setter.set(setterPosition);
    }

    public void togglePosition() {
        setterPosition = !setterPosition;
        setter.set(setterPosition);
    }

    public void setCurrent(double current) {
        driver.setCurrent(current);
    }
}
