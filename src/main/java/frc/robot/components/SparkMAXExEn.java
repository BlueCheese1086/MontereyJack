package frc.robot.components;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpiutil.math.MathUtil;

public class SparkMAXExEn extends SparkMAX {

    PIDController pid;
    double measurement;

    /**
     * 
     * @param id
     */
    public SparkMAXExEn(int id) {
        super(id);
    }

    /**
     * 
     * @param id
     * @param type
     */
    public SparkMAXExEn(int id, MotorType type) {
        super(id, type);
    }

    @Override
    public void setSpeed(double speed) {
        this.setCurrent(pid.calculate(this.measurement, speed));
    }

    @Override
    public void setPosition(double position) {
        this.setCurrent(MathUtil.clamp(pid.calculate(this.measurement, position), -0.4, 0.4));
    }

    @Override
    public double getSpeed() {
        return measurement;
    }

    @Override
    public double getPosition() {
        return measurement;
    }

    @Override
    public void resetPosition() {
        this.measurement = 0;
    }

    @Override
    public void initPID(double ff, double kp, double ki, double kd) {
        pid = new PIDController(kp, ki, kd);
        if (ff != 0) {
            System.out.println("ERROR: Feed-forward constant not available for external PID loops");
        }
    }

    /**
     * Sets measurment to the given mesurement (generally encoder's position)
     * @param measurement
     */
    public void updateMeasurement(double measurement) {
        this.measurement = measurement;
    }

    @Override
    public void setContinuousPID(double min, double max) {
        pid.enableContinuousInput(min, max);
    }

    @Override
    public boolean atTargetPosition(double tolerance) {
        return pid.getPositionError() < tolerance;
    }
}
