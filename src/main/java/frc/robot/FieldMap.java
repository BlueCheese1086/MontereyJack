package frc.robot;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;

public class FieldMap {

    public static final double FIELD_LENGTH = Units.inchesToMeters(649);
    public static final double FIELD_WIDTH = Units.inchesToMeters(319);
    public static final Pose2d OPPOSING_ALLIANCE_POWERPORT = new Pose2d(FIELD_WIDTH - Units.inchesToMeters(94.66), FIELD_LENGTH, new Rotation2d());
    public static final double POWERPORT_TARGET_HEIGHT = Units.inchesToMeters(98.25);
    public static final double POWERPORT_CENTER_HEIGHT = Units.inchesToMeters(0);
}
