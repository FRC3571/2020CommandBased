package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {

    private Shooter shooter;

    public Shoot() {
        this.shooter = Robot.getRobotContainer().getShooter();
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.setMotors(shooter.getTopSpeed(), shooter.getBottomSpeed());
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setMotors(0, 0);
    }
}