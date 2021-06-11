package frc.robot.sensors;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.RobotDimensions;

public class Limelight {

    NetworkTable limelight;

    public Limelight() {
        limelight = NetworkTableInstance.getDefault().getTable("limelight");
    }

    /**
     * @return horizontal angle of the current target in degrees
     */
    public double getXAngle() {
        return limelight.getEntry("tx").getDouble(0);
    }

    /**
     * @return vertical angle of the current target in radians
     */
    public double getYAngle() {
        return Units.degreesToRadians(limelight.getEntry("ty").getDouble(0));
    }

    public void setLights(int val) {
        limelight.getEntry("ledMode").setNumber(val);
    }

    public void setCameraMode(int val) {
        limelight.getEntry("camMode").setNumber(val);
    }

    public double getGroundDistance(double targetHeightMeters) {
        return (targetHeightMeters - (Units.inchesToMeters(RobotDimensions.CAMERA_HEIGHT))) / Math.tan(Units.degreesToRadians(RobotDimensions.CAMERA_ANGLE) + getYAngle());
    }
    
}