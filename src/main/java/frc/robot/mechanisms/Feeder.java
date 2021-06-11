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

    public void setElevatorCurrent(double current) {
        elevator.setCurrent(current);
    }

    public void setKickerSpeed(double speed) {
        kicker.setSpeed(speed * Constants.FEEDER_VELOCITY_SCALE);
    }

    public void setKickerCurrent(double current) {
        kicker.setCurrent(current);
    }

    public boolean ballDetected() {
        return !beambreak.get();
    }
}
