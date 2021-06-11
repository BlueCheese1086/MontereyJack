package frc.robot.mechanisms;

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
    }

    public void setLauncherSpeed(double speed) {
        desiredSpeed = speed * Constants.LAUNCHER_VELOCITY_SCALE;
        launcherMain.setSpeed(desiredSpeed);
        launcherFollow.setFollow(RobotMap.LAUNCHER_MAIN_MOTOR);
    }

    public void setLauncherCurrent(double current) {
        launcherMain.setCurrent(current);
        launcherFollow.setFollow(RobotMap.LAUNCHER_MAIN_MOTOR);
    }

    public double getLauncherSpeed() {
        return launcherMain.getSpeed() / Constants.LAUNCHER_VELOCITY_SCALE;
    }

    public double getDesiredLauncherSpeed() {
        return desiredSpeed;
    }

    public void setTurretPosition(double position, double measurement) {
        turret.updateMeasurement(measurement);
        double currentPos = turretEncoder.get();
        if ((currentPos >= Constants.TURRET_MIN_POSITION || measurement - position >= 0) && (currentPos <= Constants.TURRET_MAX_POSITION ||  measurement - position <= 0)) {
            turret.setPosition(position);
        }
    }

    public void setTurretCurrent(double current) {
        turret.setCurrent(current);
    }

    public double getTurretPosition() {
        return turretEncoder.get();
    }

    public void setHoodPosition(double position) {
        if (position <= Constants.HOOD_MAX_POSITION) hood.setPosition(position * Constants.HOOD_POSITION_SCALE);
    }

    public void setHoodCurrent(double current) {
        hood.setCurrent(current);
    }

    public double getHoodPosition() {
        return hood.getPosition() / Constants.HOOD_POSITION_SCALE;
    }

    public boolean atTargetPosition() {
        return turret.atTargetPosition(Constants.TURRET_POSITION_TOLERANCE);
    }

    public static double findDesiredAngle(double x, double y, double velocity) {
        double fc = Constants.G * Math.pow(x, 2) / 2 / Math.pow(velocity, 2);
        return Math.atan((-x + Math.sqrt(Math.pow(x, 2) - 4 * fc * (fc + y))) / (2 * -fc));
    }

    public static double findDesiredVelocity(double x, double y, double angle) {
        return Math.sqrt((Constants.G * Math.pow(x, 2)) / ((x * Math.tan(angle) - y) * (2 * Math.pow(Math.cos(angle), 2))));
    }

    public static double appliedVelocity(double velocity) {
        return Constants.POWERCELL_KA * Math.pow(velocity, 2) + Constants.POWERCELL_KB * velocity + Constants.POWERCELL_KC;
    }
}
