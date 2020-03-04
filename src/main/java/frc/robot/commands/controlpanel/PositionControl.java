package frc.robot.commands.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.ControlPanel.Constants.CPColor;

public class PositionControl extends CommandBase {
    
    private ControlPanel controlPanel;
    private CPColor target, first, second, third;
    private double timeSpentOnColor, firstTime, secondTime, thirdTime, averageTime, targetTime;
    private double motorSpeed;
    private boolean onTarget, passedTarget, onColor;

    public PositionControl(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        addRequirements(controlPanel);
    }

    @Override
    public void initialize() {
        onTarget = false;
        passedTarget = false;
        onColor = false;
        firstTime = 0;
        secondTime = 0;
        thirdTime = 0;
        averageTime = 0;
        targetTime = 0;
        timeSpentOnColor = 0;
        motorSpeed = 1;

        switch (controlPanel.getColorAssignment()) {
        case BLUE:
            target = CPColor.RED;
            first = CPColor.GREEN;
            second = CPColor.BLUE;
            third = CPColor.YELLOW;
            break;
        case RED:
            target = CPColor.BLUE;
            first = CPColor.YELLOW;
            second = CPColor.RED;
            third = CPColor.GREEN;
            break;
        case YELLOW:
            target = CPColor.GREEN;
            first = CPColor.BLUE;
            second = CPColor.YELLOW;
            third = CPColor.RED;
            break;
        case GREEN:
            target = CPColor.YELLOW;
            first = CPColor.RED;
            second = CPColor.GREEN;
            third = CPColor.BLUE;
            break;
        case NONE:
            this.cancel();
        default:
            break;
        }
    }

    @Override
    public void execute() {
        if (!onTarget) {
            controlPanel.setMotor(motorSpeed);
            if (!onColor) {
                if (controlPanel.findColor() == target)
                    passedTarget = true;
                if (passedTarget) {
                    if (controlPanel.findColor() == first)
                        firstTime += 20;
                    else if (controlPanel.findColor() == second)
                        secondTime += 20;
                    else if (controlPanel.findColor() == third)
                        thirdTime += 20;
                    if (thirdTime != 0 && controlPanel.findColor() == target)
                        onColor = true;
                    averageTime = (firstTime + secondTime + thirdTime) / 3;
                }
            } else {
                targetTime += 20;
                if (targetTime >= (averageTime / 2)) {
                    onTarget = true;
                    controlPanel.setMotor(0);
                }
            }
        }

        else
            timeSpentOnColor += 20;
    }

    @Override
    public boolean isFinished() {
        return timeSpentOnColor > 5250;
    }
}