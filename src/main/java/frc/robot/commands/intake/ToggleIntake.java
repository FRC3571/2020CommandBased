package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends InstantCommand {
    private final Intake intake;
    private boolean openState;

    public ToggleIntake(final Intake intake) {
        this.intake = intake;
        this.openState = !intake.getOpenState();
        
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        this.openState = !intake.getOpenState();
    }

    @Override
    public void execute() {
        intake.setSolenoids(openState);
    }
}