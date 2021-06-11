package frc.robot.components;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Talon extends MotorController {

    TalonSRX srxController;
    TalonFX fxController;
    TalonType type;

    public Talon(int id, TalonType type) {
        this.type = type;
        switch(type) {
            case SRX:
                srxController = new TalonSRX(id);
                break;
            case FX:
                fxController = new TalonFX(id);
                break;
        }
    }

    @Override
    public void setCurrent(double current) {
        switch(type) {
            case SRX:
                srxController.set(TalonSRXControlMode.PercentOutput, current);
                break;
            case FX:
                fxController.set(TalonFXControlMode.PercentOutput, current);
                break;
        }
    }

    public void setFollow(int id) {
        switch(type) {
            case SRX:
                srxController.set(TalonSRXControlMode.Follower, id);
                break;
            case FX:
                fxController.set(TalonFXControlMode.Follower, id);
                break;
        }
    }

    @Override
    public void setSpeed(double speed) {
        switch(type) {
            case SRX:
                srxController.set(TalonSRXControlMode.Velocity, speed);
                break;
            case FX:
                fxController.set(TalonFXControlMode.Velocity, speed);
                break;
        }
    }

    @Override
    public void setPosition(double position) {
        switch(type) {
            case SRX:
                srxController.set(TalonSRXControlMode.Position, position);
                break;
            case FX:
                fxController.set(TalonFXControlMode.Position, position);
                break;
        }
    }

    @Override
    public double getSpeed() {
        switch(type) {
            case SRX:
                return srxController.getSelectedSensorVelocity();
            case FX:
                return fxController.getSelectedSensorVelocity();
            default:
                return 0;
        }
    }

    @Override
    public double getPosition() {
        switch(type) {
            case SRX:
                return srxController.getSelectedSensorPosition();
            case FX:
                return fxController.getSelectedSensorPosition();
            default:
                return 0;
        }
    }

    @Override
    public void resetPosition() {
        switch(type) {
            case SRX:
                srxController.setSelectedSensorPosition(0);
                break;
            case FX:
                fxController.setSelectedSensorPosition(0);
                break;
        }
    }

    @Override
    public void initPID(double ff, double kp, double ki, double kd) {
        switch(type) {
            case SRX:
                srxController.config_kP(0, kp);
                srxController.config_kI(0, ki);
                srxController.config_kD(0, kd);
                srxController.config_kF(0, ff);
                break;
            case FX:
                fxController.config_kP(0, kp);
                fxController.config_kI(0, ki);
                fxController.config_kD(0, kd);
                fxController.config_kF(0, ff);
                break;
        }
    }

    @Override
    public void setContinuousPID(double min, double max) {
        System.out.println("ERROR: Talon internal PID loops are never continuous");
    }

    @Override
    public void setInverted(boolean inverted) {
        switch(type) {
            case SRX:
                srxController.setInverted(inverted);
                break;
            case FX:
                fxController.setInverted(inverted);
                break;
        }
    }

    public TalonSRX getInternalSRXController() {
        return srxController;
    }

    public TalonFX getInternalFXController() {
        return fxController;
    }

    @Override
    public boolean atTargetPosition(double tolerance) {
        switch(type) {
            case SRX:
                return Math.abs(srxController.getClosedLoopError(0)) < tolerance;
            case FX:
                return Math.abs(fxController.getClosedLoopError(0)) < tolerance;
            default:
                return false;
        }
    }
    
}
