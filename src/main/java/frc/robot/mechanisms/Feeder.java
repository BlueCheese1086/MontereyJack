package frc.robot.mechanisms;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.components.SparkMAX;
import frc.robot.components.Talon;
import frc.robot.components.TalonType;

public class Feeder {
    
    Talon elevator;
    SparkMAX kicker;
    DigitalInput beambreak;

    public Feeder() {
        elevator = new Talon(RobotMap.ELEVATOR_MOTOR, TalonType.SRX);
        kicker = new SparkMAX(RobotMap.KICKER);
        kicker.initPID(Constants.FEEDER_FF, Constants.FEEDER_KP, Constants.FEEDER_KI, Constants.FEEDER_KD);
        beambreak = new DigitalInput(RobotMap.BEAM_BREAK);
    }

    /**
     * 
     * @param current percent current to set to elevator motor
     */
    public void setElevatorCurrent(double current) {
        elevator.setCurrent(current);
    }

    /**
     * Sets kicker speed (in RPM)
     * @param speed 
     */
    public void setKickerSpeed(double speed) {
        kicker.setSpeed(speed);
    }

    /**
     * 
     * @param current percent current to set to the kicker motor
     */
    public void setKickerCurrent(double current) {
        kicker.setCurrent(current);
    }


    /**
     * 
     * @return true if a ball is in the feeder and false otherwise
     */
    public boolean ballDetected() {
        return !beambreak.get();
    }
}
