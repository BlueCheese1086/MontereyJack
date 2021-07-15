package frc.robot;

public class Constants {

    public static double G = 9.81;

    public static double FEEDER_FF = 0;
    public static double FEEDER_KP = 0;
    public static double FEEDER_KI = 0;
    public static double FEEDER_KD = 0;
    public static double FEEDER_DEFAULT_VELOCITY = 0;
    public static double FEEDER_VELOCITY_SCALE = 1 / (2 * RobotDimensions.FEEDER_ROLLER_RADIUS * Math.PI) * 60; // m / s -> RPM

    public static double TURRET_SCALE = 360;
    public static double TURRET_ZERO = 0;

    public static double LAUNCHER_DEFAULT_VELOCITY = 9.2; // m/s
    public static double LAUNCHER_VELOCITY_SCALE = 1 / (2 * RobotDimensions.LAUNCHER_WHEEL_RADIUS * Math.PI) * 2048 / 10; // m / s -> enc units / 10th of a second
    public static double LAUNCHER_FF = 1 / (30.5 * LAUNCHER_VELOCITY_SCALE);
    public static double LAUNCHER_KP = 0.3;
    public static double LAUNCHER_KI = 1.2;
    public static double LAUNCHER_KD = 0;

    public static double POWERCELL_KA = 0;
    public static double POWERCELL_KB = 0;
    public static double POWERCELL_KC = 0;

    public static double TURRET_KP = 0;
    public static double TURRET_KI = 0;
    public static double TURRET_KD = 0;

    public static double TURRET_MAX_POSITION = 0;
    public static double TURRET_MIN_POSITION = 0;
    public static double TURRET_POSITION_TOLERANCE = 1;

    public static double HOOD_KP = 0;
    public static double HOOD_KI = 0;
    public static double HOOD_KD = 0;

    public static double HOOD_MAX_POSITION = 0;
    public static double HOOD_MIN_POSITION = 0;
    public static double HOOD_POSITION_SCALE = 0;
    public static double HOOD_INCREMENTER = 0;

    public static double DRIVETRAIN_POSITION_SCALE = (8.98) / (2 * RobotDimensions.DRIVETRAIN_WHEEL_RADIUS * Math.PI); // m -> rotation
    public static double DRIVETRAIN_VELOCITY_SCALE = (8.98) * 60 / (2 * RobotDimensions.DRIVETRAIN_WHEEL_RADIUS * Math.PI); // m / s -> RPM

    public static double MP_DRIVE_FF = 0;
    public static double MP_DRIVE_KP = 0;
    public static double MP_DRIVE_KI = 0;
    public static double MP_DRIVE_KD = 0;

    public static double MP_KS = 0;
    public static double MP_KV = 0;
    public static double MP_KA = 0;
    public static double MP_MAX_VELOCITY = 10; // m/s
    public static double MP_MAX_ACCELERATION = 5; // m/s^2

    public static double RAMSETE_B = 2;
    public static double RAMSETE_ZETA = 0.7;

}
