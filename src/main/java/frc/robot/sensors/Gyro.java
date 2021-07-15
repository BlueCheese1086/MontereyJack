package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class Gyro {
    
    AHRS gyro;

    public Gyro() {
        gyro = new AHRS(SPI.Port.kMXP);
    }

    /**
     * Returns yaw value
     * @return yaw
     */
    public double getYaw() {
        return gyro.getAngle();
    }

    /**
     * Sets current yaw to given value
     * @param yaw
     */
    public void setYaw(double yaw) {
        gyro.reset();
        gyro.setAngleAdjustment(yaw);
    }
}
