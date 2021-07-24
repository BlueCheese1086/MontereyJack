package frc.robot;

public class Constants {

    public static double G = 9.81;

    public static double FEEDER_FF = 1.0 / 11000;
    public static double FEEDER_KP = 0.0001;
    public static double FEEDER_KI = 0;
    public static double FEEDER_KD = 0;
    public static double FEEDER_DEFAULT_VELOCITY = 9000;

    public static double TURRET_SCALE = 360.0 / (217.0 / 18);
    public static double TURRET_ZERO = 12.6;

    public static double LAUNCHER_DEFAULT_VELOCITY = 9.2; // m/s
    public static double LAUNCHER_VELOCITY_SCALE = 1 / (2 * RobotDimensions.LAUNCHER_WHEEL_RADIUS * Math.PI) * 2048 / 10; // m / s -> enc units / 10th of a second
    public static double LAUNCHER_FF = 0.986 * 1023.0 / 20765;
    public static double LAUNCHER_KP = 1.0 / 200;
    public static double LAUNCHER_KI = 0;
    public static double LAUNCHER_KD = 0;

    public static double POWERCELL_KA = 0.0865 * 3;
    public static double POWERCELL_KB = 0.05 * 3;
    public static double POWERCELL_KC = 0;

    public static double TURRET_KP = 4.5 / 180;
    public static double TURRET_KI = 0;
    public static double TURRET_KD = 0;

    public static double TURRET_MAX_POSITION = 45;
    public static double TURRET_MIN_POSITION = -45;
    public static double TURRET_POSITION_TOLERANCE = 1;

    public static double HOOD_KP = 1 / 4.0;
    public static double HOOD_KI = 0;
    public static double HOOD_KD = 0;

    public static double HOOD_MAX_POSITION = (90 - 22) / 180.0 * Math.PI;
    public static double HOOD_MIN_POSITION = HOOD_MAX_POSITION - 0.6;
    public static double HOOD_POSITION_SCALE = 37.22 / (2 * Math.PI);

    public static double DRIVETRAIN_POSITION_SCALE = (8.98) / (2 * RobotDimensions.DRIVETRAIN_WHEEL_RADIUS * Math.PI); // m -> rotation
    public static double DRIVETRAIN_VELOCITY_SCALE = (8.98) * 60 / (2 * RobotDimensions.DRIVETRAIN_WHEEL_RADIUS * Math.PI); // m / s -> RPM

    public static double MP_DRIVE_FF = 1.0 / 5676;
    public static double MP_DRIVE_KP = 1.0 / 1000;
    public static double MP_DRIVE_KI = 0;
    public static double MP_DRIVE_KD = 0;

    public static double MP_MAX_VELOCITY = 3; // m/s
    public static double MP_MAX_ACCELERATION = 1.5; // m/s^2
    public static double MP_KS = 0.246;
    public static double MP_KV = 2.49;
    public static double MP_KA = 0.553;

    public static double RAMSETE_B = 2;
    public static double RAMSETE_ZETA = 0.7;

}
