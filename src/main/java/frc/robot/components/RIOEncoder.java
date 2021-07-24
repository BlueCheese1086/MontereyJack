package frc.robot.components;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class RIOEncoder {
    
    DutyCycleEncoder encoder;
    double outputScale;
    double zero;

    /**
     * @param id
     * @param outputScale
     */
    public RIOEncoder(int id, double outputScale) {
        encoder = new DutyCycleEncoder(id);
        this.outputScale = outputScale;
        zero = 0;
    }

    /**
     * @param id
     * @param outputScale
     * @param zero
     */
    public RIOEncoder(int id, double outputScale, double zero) {
        encoder = new DutyCycleEncoder(id);
        this.outputScale = outputScale;
        this.zero = zero;
    }

    /**
     * @return rotations away from zero times outputScale (generally degrees)
     */
    public double get() {
        return encoder.get() * outputScale - zero;
    }

    /**
     * Sets zero
     * @param zero
     */
    public void setZero(double zero) {
        this.zero = zero;
    }

    /**
     * Sets outputScale to scale
     * @param scale
     */
    public void setOutputScale(double scale) {
        outputScale = scale;
    }

}
