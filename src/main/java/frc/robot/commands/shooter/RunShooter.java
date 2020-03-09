package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase {

    private final Shooter shooter;

    public RunShooter(final Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        SmartDashboard.putBoolean("Shooter/Running", true);
    }

    @Override
    public void execute() {
        shooter.setMotors(shooter.getTopSpeed(), shooter.getBottomSpeed());
    }

    @Override
    public void end(final boolean interrupted) {
        shooter.setMotors(0, 0);
        SmartDashboard.putBoolean("Shooter/Running", false);
    }
}