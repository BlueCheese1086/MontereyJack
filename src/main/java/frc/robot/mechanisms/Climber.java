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

    public void setLeftLock(boolean position) {
        leftLockPosition = position;
        leftLock.set(leftLockPosition);
    }

    public void toggleLeftLock() {
        leftLockPosition = !leftLockPosition;
        leftLock.set(leftLockPosition);
    }

    public void setRightLock(boolean position) {
        rightLockPosition = position;
        rightLock.set(rightLockPosition);
    }

    public void toggleRightLock() {
        rightLockPosition = !rightLockPosition;
        rightLock.set(rightLockPosition);
    }

    public void setLeftCurrent(double current) {
        left.setCurrent(current);
    }

    public void setRightCurrent(double current) {
        right.setCurrent(current);
    }
}
