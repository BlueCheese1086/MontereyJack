package frc.robot.components;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class SparkMAX extends MotorController {

    CANSparkMax controller;
    CANPIDController pid;
    double targetPosition;
    double targetSpeed;

    public SparkMAX(int id, MotorType type) {
        controller = new CANSparkMax(id, type);
    }

    public SparkMAX(int id) {
        controller = new CANSparkMax(id, MotorType.kBrushless);
    }

    @Override
    public void setCurrent(double current) {
        controller.set(current);
    }

    @Override
    public void setSpeed(double speed) {
        targetSpeed = speed;
        pid.setReference(speed, ControlType.kVelocity);
    }

    @Override
    public void setPosition(double position) {
        targetPosition = position;
        pid.setReference(position, ControlType.kPosition);
    }

    @Override
    public double getSpeed() {
        return controller.getEncoder().getVelocity();
    }

    @Override
    public double getPosition() {
        return controller.getEncoder().getPosition();
    }

    @Override
    public void resetPosition() {
        controller.getEncoder().setPosition(0);
    }

    @Override
    public void initPID(double ff, double kp, double ki, double kd) {
        pid = controller.getPIDController();
        pid.setFF(ff);
        pid.setP(kp);
        pid.setI(ki);
        pid.setD(kd);
    }

    public CANSparkMax getInternalController() {
        return controller;
    }

    public void follow(SparkMAX controller) {
        this.controller.follow(controller.getInternalController());
    }

    @Override
    public void setContinuousPID(double min, double max) {
        System.out.println("ERROR: SparkMAX internal PID loops are never continuous");
    }

    @Override
    public void setInverted(boolean inverted) {
        controller.setInverted(inverted);
    }

    @Override
    public boolean atTargetPosition(double tolerance) {
        return Math.abs(this.getPosition() - targetPosition) < tolerance;
    }
    
}
