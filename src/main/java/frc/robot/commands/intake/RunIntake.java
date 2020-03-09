package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
    private final Intake intake;

    private static final double kSpeed = 0.5;

    public RunIntake(final Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        SmartDashboard.putBoolean("Intake/Running", true);
    }

    @Override
    public void execute() {
        intake.setMotor(kSpeed);
    }

    @Override
    public void end(final boolean interrupted) {
        intake.setMotor(0);
        SmartDashboard.putBoolean("Intake/Running", false);
    }
}