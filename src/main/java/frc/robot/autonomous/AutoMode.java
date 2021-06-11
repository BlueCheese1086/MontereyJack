package frc.robot.autonomous;

import java.util.ArrayList;

import frc.robot.autonomous.sections.AutoSection;

public class AutoMode {

    ArrayList<AutoThread> threads;
    String name;
    
    public AutoMode(String name) {

        this.name = name;
        threads = new ArrayList<AutoThread>();

    }

    public void addSection(AutoSection s, int thread) {
        try {
            threads.get(thread).addSection(s);
        } catch (Exception e) {
            threads.set(thread, new AutoThread());
            addSection(s, thread);
        }
    }

    public void update() {

        for (AutoThread t : threads) t.update();
    }

    public String getName() {
        return name;
    }
}
