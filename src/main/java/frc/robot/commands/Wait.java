package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class Wait extends InstantCommand {
    private double time;

    public Wait(double time){
        this.time = time;
    }

    @Override
    public void execute() {
        Timer.delay(time);
    }
}