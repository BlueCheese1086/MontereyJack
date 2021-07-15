package frc.robot.mechanisms;

import frc.robot.RobotMap;
import frc.robot.components.Talon;
import frc.robot.components.TalonType;

public class Hopper {
    
    Talon left, right;

    public Hopper() {
        left = new Talon(RobotMap.LEFT_HOPPER_MOTOR, TalonType.SRX);
        right = new Talon(RobotMap.RIGHT_HOPPER_MOTOR, TalonType.SRX);
    }

    /**
     * 
     * @param current percent current to set the left side of hopper to
     */
    public void setLeftCurrent(double current) {
        left.setCurrent(current);
    }

    /**
     * 
     * @param current percent current to set the right side of hopper to
     */
    public void setRightCurrent(double current) {
        right.setCurrent(current);
    }
}
