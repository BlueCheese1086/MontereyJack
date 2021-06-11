package frc.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoManager {

    SendableChooser autoChooser;
    AutoMode auto1, auto2, auto3;
    
    public AutoManager() {

        autoChooser = new SendableChooser<AutoMode>();
        auto1 = new AutoMode("Test1");
        auto2 = new AutoMode("Test2");
        auto3 = new AutoMode("Test3");
        autoChooser.setDefaultOption(auto1.getName(), auto1);
        autoChooser.addOption(auto2.getName(), auto2);
        autoChooser.addOption(auto3.getName(), auto3);

    }

    public AutoMode getAuto() {
        return (AutoMode)autoChooser.getSelected();
    }
}
