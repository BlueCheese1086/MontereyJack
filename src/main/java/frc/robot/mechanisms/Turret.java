package frc.robot.mechanisms;

import edu.wpi.first.wpilibj.util.Units;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.components.RIOEncoder;
import frc.robot.components.SparkMAX;
import frc.robot.components.SparkMAXExEn;
import frc.robot.components.Talon;
import frc.robot.components.TalonType;

public class Turret {
    
    Talon launcherMain, launcherFollow;
    SparkMAXExEn turret;
    SparkMAX hood;
    RIOEncoder turretEncoder;
    double desiredSpeed;

    public Turret() {
        desiredSpeed = 0;
        launcherMain = new Talon(RobotMap.LAUNCHER_MAIN_MOTOR, TalonType.FX);
        launcherFollow = new Talon(RobotMap.LAUNCHER_FOLLOW_MOTOR, TalonType.FX);
        turret = new SparkMAXExEn(RobotMap.TURRET_MOTOR);
        turretEncoder = new RIOEncoder(RobotMap.TURRET_ENCODER, Constants.TURRET_SCALE, Constants.TURRET_ZERO);
        hood = new SparkMAX(RobotMap.HOOD_MOTOR);
        hood.initPID(0, Constants.HOOD_KP, Constants.HOOD_KI, Constants.HOOD_KD);
        launcherMain.initPID(Constants.LAUNCHER_FF, Constants.LAUNCHER_KP, Constants.LAUNCHER_KI, Constants.LAUNCHER_KD);
        turret.initPID(0, Constants.TURRET_KP, Constants.TURRET_KI, Constants.TURRET_KD);
        launcherFollow.setInverted(true);
        hood.setInverted(true);
        turret.setInverted(true);
    }

    /**
     * Sets the launcher speed (in m/s)
     * @param speed speed to set to launcherMain and launcherFollow
     */
    public void setLauncherSpeed(double speed) {
        desiredSpeed = speed * Constants.LAUNCHER_VELOCITY_SCALE;
        launcherMain.setSpeed(desiredSpeed);
        launcherFollow.setFollow(RobotMap.LAUNCHER_MAIN_MOTOR);
        System.out.println(launcherMain.getInternalFXController().getClosedLoopError());
    }

    /**
     * 
     * @param current percent current to set to launcherMain and launcherFollow
     */
    public void setLauncherCurrent(double current) {
        launcherMain.setCurrent(current);
        launcherFollow.setFollow(RobotMap.LAUNCHER_MAIN_MOTOR);
    }

    /**
     * 
     * @return launcher speed (in m/s)
     */
    public double getLauncherSpeed() {
        return launcherMain.getSpeed() / Constants.LAUNCHER_VELOCITY_SCALE;
    }

    /**
     * 
     * @return desired launcher speed (in m/s)
     */
    public double getDesiredLauncherSpeed() {
        return desiredSpeed;
    }

    /**
     * 
     * @param position to set to turret (in degrees)
     * @param measurement to set to turret (in degrees)
     */
    public void setTurretPosition(double position, double measurement) {
        turret.updateMeasurement(measurement);
        double currentPos = turretEncoder.get();
        if ((currentPos >= Constants.TURRET_MIN_POSITION || measurement - position >= 0) && (currentPos <= Constants.TURRET_MAX_POSITION ||  measurement - position <= 0)) turret.setPosition(position);
    }

    /**
     * 
     * @param current percent current to set to the turret motor
     */
    public void setTurretCurrent(double current) {
        turret.setCurrent(current);
    }

    /**
     * 
     * @return turret position (in degrees)
     */
    public double getTurretPosition() {
        return turretEncoder.get();
    }

    /**
     * Sets hood position (in degrees)
     * @param position
     */
    public void setHoodPosition(double position) {
        hood.setPosition((Constants.HOOD_MAX_POSITION - position) * Constants.HOOD_POSITION_SCALE);
    }

    /**
     * 
     * @param current percent current to set the hood to
     */
    public void setHoodCurrent(double current) {
        hood.setCurrent(current);
    }

    /**
     * 
     * @return hood position (in degrees)
     */
    public double getHoodPosition() {
        return hood.getPosition() / Constants.HOOD_POSITION_SCALE;
    }

    /**
     * 
     * @return true if turret position is close enough to target position and false otherwise
     */
    public boolean atTargetPosition() {
        return turret.atTargetPosition(Constants.TURRET_POSITION_TOLERANCE);
    }

    /**
     * 
     * @param x
     * @param y
     * @param velocity
     * @return desired angle (in degrees)
     */
    public static double findDesiredAngle(double x, double y, double velocity) {
        double fc = Constants.G * Math.pow(x, 2) / 2 / Math.pow(velocity, 2);
        return Math.atan((-x + Math.sqrt(Math.pow(x, 2) - 4 * fc * (fc + y))) / (2 * -fc));
    }

    /**
     * 
     * @param x
     * @param y
     * @param angle
     * @return desired velocity (in m/s)
     */
    public static double findDesiredVelocity(double x, double y, double angle) {
        return Math.sqrt((Constants.G * Math.pow(x, 2)) / ((x * Math.tan(angle) - y) * (2 * Math.pow(Math.cos(angle), 2))));
    }

    /**
     * ???????
     * @param velocity
     * @return
     */
    public static double appliedVelocity(double velocity) {
        return Constants.POWERCELL_KA * Math.pow(velocity, 2) + Constants.POWERCELL_KB * velocity + Constants.POWERCELL_KC;
    }
}
