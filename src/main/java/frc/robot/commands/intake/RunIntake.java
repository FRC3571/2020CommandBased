package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
    private final Intake intake;

    private static final double kSpeed = 0.5;

    public RunIntake() {
        this.intake = Robot.getRobotContainer().getIntake();
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setMotor(kSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setMotor(0);
    }
}