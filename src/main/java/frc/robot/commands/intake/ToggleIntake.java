package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends InstantCommand {
    private final Intake intake;
    private final boolean openState;

    public ToggleIntake(final Intake intake, final boolean openState) {
        this.intake = intake;
        this.openState = openState;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setSolenoids(openState);
    }
}