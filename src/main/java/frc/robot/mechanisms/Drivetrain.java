package frc.robot.mechanisms;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
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
        leftMain.setInverted(true);

        leftMain.initPID(Constants.MP_DRIVE_FF, Constants.MP_DRIVE_KP, Constants.MP_DRIVE_KI, Constants.MP_DRIVE_KD);
        rightMain.initPID(Constants.MP_DRIVE_FF, Constants.MP_DRIVE_KP, Constants.MP_DRIVE_KI, Constants.MP_DRIVE_KD);

        kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(RobotDimensions.ROBOT_WIDTH));
    }

    /**
     * 
     * @return the left position of the robot (in meters)
     */
    public double getLeftPos() {
        return leftMain.getPosition() * Constants.DRIVETRAIN_POSITION_SCALE;
    }

    /**
     * 
     * @return the right position of the robot (in meters)
     */
    public double getRightPos() {
        return rightMain.getPosition() * Constants.DRIVETRAIN_POSITION_SCALE;
    }

    /**
     * 
     * @return the left motor velocity in m/s
     */
    public double getLeftVel() {
        return leftMain.getSpeed() * Constants.DRIVETRAIN_VELOCITY_SCALE;
    }

    /**
     * 
     * @return the right motor velocity in m/s
     */
    public double getRightVel() {
        return rightMain.getSpeed() * Constants.DRIVETRAIN_VELOCITY_SCALE;
    }

    /**
     * Sets left and right encoder positions to 0
     */
    public void resetPositions() {
        leftMain.resetPosition();
        rightMain.resetPosition();
    }

    /**
     * makes robot go vrooommm
     * @param forward forward current
     * @param turn turn current
     */
    public void drive(double forward, double turn) {
        setCurrents(forward + turn, forward - turn);
    }

    /**
     * Sets the speeds of both sides of the robot in m/s
     * @param speeds 
     */
    public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {
        leftMain.setSpeed(speeds.leftMetersPerSecond);
        rightMain.setSpeed(speeds.rightMetersPerSecond);
    }

    /**
     * Sets the motor power to both sides of the robot
     * Precondition: left and right are between -1 and 1
     * @param left left motor percent current
     * @param right right motor percent current
     */
    public void setCurrents(double left, double right) {
        leftMain.setCurrent(left);
        rightMain.setCurrent(right);
    }

    /**
     * Sets position and angle encoders to 0 and sets odometry x, y and rotation to the location of the robot relative to the field
     * @param position postion to set the odometry to
     * @param angle to set the odometry to
     */
    public void setOdometry(Pose2d position, Rotation2d angle) {
        try {
            odometry.resetPosition(position, angle);
        } catch (Exception e) {
            odometry = new DifferentialDriveOdometry(angle, position);
        }
        resetPositions();
    }

    /**
     * 
     * @param leftPos left position to set the odometry to
     * @param rightPos right position to set the odometry to
     * @param angle angle to set the odometry to
     */
    public void updateOdometry(double leftPos, double rightPos, Rotation2d angle) {
        odometry.update(angle, leftPos, rightPos);
    }

    /**
     * 
     * @return odometry position (in meters)
     */
    public Pose2d getCoordinates() {
        return odometry.getPoseMeters();
    }

    /**
     * 
     * @return kinematics (wheel base width)
     */
    public DifferentialDriveKinematics getKinematics() {
        return kinematics;
    }

}
