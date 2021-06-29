package frc.robot.components;

public abstract class MotorController {
    
    /**
     * Set motor control percent to current
     * Precondition: current is between -1 and 1
     * @param current
     */
    public abstract void setCurrent(double current);

    /**
     * Sets speed
     * @param speed
     */
    public abstract void setSpeed(double speed);
    
    /**
     * Sets position
     * @param position
     */
    public abstract void setPosition(double position);

    /**
     * 
     * @return speed
     */
    public abstract double getSpeed();

    /**
     * 
     * @return position
     */
    public abstract double getPosition();

    /**
     * Sets encoder position to 0
     * 
     */
    public abstract void resetPosition();

    /**
     * initializes PID controller
     * @param ff feed forward
     * @param kp proportion
     * @param ki integral
     * @param kd derivative
     */
    public abstract void initPID(double ff, double kp, double ki, double kd);

    /**
     * Set continuous PID
     * @param min
     * @param max
     */
    public abstract void setContinuousPID(double min, double max);

    /**
     * Inverts motor direction
     * @param inverted 
     */
    public abstract void setInverted(boolean inverted);

    /**
     * 
     * @param tolerance difference allowed
     * @return true if the encoder is in the right position and false otherwise
     */
    public abstract boolean atTargetPosition(double tolerance);

}
