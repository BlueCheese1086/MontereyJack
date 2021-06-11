package frc.robot.components;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class RIOEncoder {
    
    DutyCycleEncoder encoder;
    double outputScale;
    double zero;

    public RIOEncoder(int id, double outputScale) {
        encoder = new DutyCycleEncoder(id);
        this.outputScale = outputScale;
        zero = 0;
    }

    public RIOEncoder(int id, double outputScale, double zero) {
        encoder = new DutyCycleEncoder(id);
        this.outputScale = outputScale;
        this.zero = zero;
    }

    public double get() {
        return (encoder.get() - zero) * outputScale;
    }

    public void setZero(double zero) {
        this.zero = zero;
    }

    public void setOutputScale(double scale) {
        outputScale = scale;
    }

}
