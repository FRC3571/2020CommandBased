package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Shooter;

public class ChangeShooterSpeedRatio extends InstantCommand {

    private final Shooter shooter;
    private final boolean increase;
    private double amount;

    public ChangeShooterSpeedRatio(final Shooter shooter, final boolean increase) {
        this.shooter = shooter;
        this.increase = increase;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        amount = 0.05;
        if (!increase)
            amount *= -1;
    }

    @Override
    public void execute() {
        shooter.setTopBottomRatio(shooter.getTopBottomRatio() + amount);
    }
}