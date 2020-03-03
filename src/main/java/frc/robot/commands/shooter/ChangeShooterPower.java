package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class ChangeShooterPower extends InstantCommand {

    private final Shooter shooter;
    private boolean increase;
    private double amount;

    public ChangeShooterPower(boolean increase) {
        this.shooter = Robot.getRobotContainer().getShooter();
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
        shooter.setSpeed(shooter.getBottomSpeed() + amount);
    }
}