package frc.robot.commands.controlpanel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.ControlPanel.Constants.CPColor;

public class PositionControl extends CommandBase {
    private ControlPanel controlPanel;
    private CPColor target;
    private double timeSpentOnColor;
    private double motorSpeed;

    public PositionControl(){
        this.controlPanel = Robot.getRobotContainer().getControlPanel();
        addRequirements(controlPanel);
    }

    @Override
    public void initialize() {
        switch (controlPanel.getColorAssignment()) {
        case BLUE:
            target = CPColor.RED;
            break;
        case RED:
            target = CPColor.BLUE;
            break;
        case YELLOW:
            target = CPColor.GREEN;
            break;
        case GREEN:
            target = CPColor.YELLOW;
            break;
        case NONE:
            target = CPColor.NONE;
        default:
            break;
        }
    }

    @Override
    public void execute() {
        if (target != controlPanel.findColor()) controlPanel.setMotor(motorSpeed);
        else timeSpentOnColor += 20;
    }

    @Override
    public boolean isFinished() {
        return timeSpentOnColor > 5250;
    }


}