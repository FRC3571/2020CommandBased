package frc.robot.commands.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.ControlPanel.Constants.CPColor;

public class RotationControl extends CommandBase {

    private final ControlPanel controlPanel;
    private int spinCount;
    private double motorSpeed;
    private CPColor startingColor;
    private boolean countedThisTime;

    public RotationControl(final ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        addRequirements(controlPanel);
    }

    @Override
    public void initialize() {
        startingColor = controlPanel.findColor();
        motorSpeed = 1;
        countedThisTime = true;
    }

    @Override
    public void execute() {
        controlPanel.setMotor(motorSpeed);

        if (controlPanel.findColor() == startingColor && countedThisTime == false) {
            spinCount++;
            countedThisTime = true;
        } else if (controlPanel.findColor() != startingColor && countedThisTime == true) {
            countedThisTime = false;
        }
    }

    @Override
    public boolean isFinished() {
        return spinCount >= 7;
    }

    @Override
    public void end(final boolean interrupted) {
        controlPanel.setMotor(0);
    }

}