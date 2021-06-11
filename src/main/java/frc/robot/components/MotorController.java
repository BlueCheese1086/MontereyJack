package frc.robot.components;

public abstract class MotorController {
    
    public abstract void setCurrent(double current);

    public abstract void setSpeed(double speed);
    
    public abstract void setPosition(double position);

    public abstract double getSpeed();

    public abstract double getPosition();

    public abstract void resetPosition();

    public abstract void initPID(double ff, double kp, double ki, double kd);

    public abstract void setContinuousPID(double min, double max);

    public abstract void setInverted(boolean inverted);

    public abstract boolean atTargetPosition(double tolerance);

}
