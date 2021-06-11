package frc.robot.mechanisms;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.Constants;
import frc.robot.RobotDimensions;
import frc.robot.RobotMap;
import frc.robot.components.SparkMAX;

public class Drivetrain {
    
    SparkMAX leftMain, rightMain, leftFollow, rightFollow;
    DifferentialDriveKinematics kinematics;
    DifferentialDriveOdometry odometry;

    public Drivetrain() {

        leftMain = new SparkMAX(RobotMap.LEFT_DRIVE_MAIN);
        rightMain = new SparkMAX(RobotMap.RIGHT_DRIVE_MAIN);
        leftFollow = new SparkMAX(RobotMap.LEFT_DRIVE_FOLLOW);
        rightFollow = new SparkMAX(RobotMap.RIGHT_DRIVE_FOLLOW);

        leftFollow.follow(leftMain);
        rightFollow.follow(rightMain);

        kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(RobotDimensions.ROBOT_WIDTH));
    }

    public double getLeftPos() {
        return leftMain.getPosition() * Constants.DRIVETRAIN_POSITION_SCALE;
    }

    public double getRightPos() {
        return rightMain.getPosition() * Constants.DRIVETRAIN_POSITION_SCALE;
    }

    public double getLeftVel() {
        return leftMain.getSpeed() * Constants.DRIVETRAIN_VELOCITY_SCALE;
    }

    public double getRightVel() {
        return rightMain.getSpeed() * Constants.DRIVETRAIN_VELOCITY_SCALE;
    }

    public void resetPositions() {
        leftMain.resetPosition();
        rightMain.resetPosition();
    }

    public void translate(double forward, double turn) {
        ChassisSpeeds speeds = new ChassisSpeeds(forward, 0, turn);
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(speeds);
        setSpeeds(wheelSpeeds);
    }

    public void drive(double forward, double turn) {
        setCurrents(forward + turn, forward - turn);
    }

    public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {
        leftMain.setSpeed(speeds.leftMetersPerSecond);
        rightMain.setSpeed(speeds.rightMetersPerSecond);
    }

    public void setCurrents(double left, double right) {
        leftMain.setCurrent(left);
        rightMain.setCurrent(right);
    }

    public void setOdometry(Pose2d position, Rotation2d angle) {
        try {
            odometry.resetPosition(position, angle);
        } catch (Exception e) {
            odometry = new DifferentialDriveOdometry(angle, position);
        }
        resetPositions();
    }

    public void updateOdometry(double leftPos, double rightPos, Rotation2d angle) {
        odometry.update(angle, leftPos, rightPos);
    }

    public Pose2d getCoordinates() {
        return odometry.getPoseMeters();
    }

    public DifferentialDriveKinematics getKinematics() {
        return kinematics;
    }

}
