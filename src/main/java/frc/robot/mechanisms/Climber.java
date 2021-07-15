package frc.robot.mechanisms;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import frc.robot.components.Talon;
import frc.robot.components.TalonType;

public class Climber {
    
    Talon left, right;
    Solenoid leftLock, rightLock;
    boolean leftLockPosition, rightLockPosition;

    public Climber() {
        left = new Talon(RobotMap.LEFT_CLIMBER_MOTOR, TalonType.SRX);
        right = new Talon(RobotMap.RIGHT_CLIMBER_MOTOR, TalonType.SRX);
        leftLock = new Solenoid(RobotMap.LEFT_CLIMBER_LOCK);
        rightLock = new Solenoid(RobotMap.RIGHT_CLIMBER_LOCK);
        leftLockPosition = true;
        rightLockPosition = true;
    }

    /**
     * Locks or unlocks left climber lock
     * @param position
     */
    public void setLeftLock(boolean position) {
        leftLockPosition = position;
        leftLock.set(leftLockPosition);
    }

    /**
     * Inverts left climber lock
     */
    public void toggleLeftLock() {
        leftLockPosition = !leftLockPosition;
        leftLock.set(leftLockPosition);
    }

    /**
     * Locks or unlocks right climber lock
     * @param position
     */
    public void setRightLock(boolean position) {
        rightLockPosition = position;
        rightLock.set(rightLockPosition);
    }

    /**
     * Inverts right climber lock
     */
    public void toggleRightLock() {
        rightLockPosition = !rightLockPosition;
        rightLock.set(rightLockPosition);
    }

    /**
     * Sets the left climber motor power to current
     * Precondition: current is between -1 and 1
     * @param current
     */
    public void setLeftCurrent(double current) {
        left.setCurrent(current);
    }

    /**
     * Sets the right climber motor power to current
     * Precondition: current is between -1 and 1
     * @param current
     */
    public void setRightCurrent(double current) {
        right.setCurrent(current);
    }
}
