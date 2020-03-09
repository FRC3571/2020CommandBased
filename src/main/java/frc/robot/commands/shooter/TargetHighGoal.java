package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.components.Vision;
import frc.robot.subsystems.DriveTrain;

public class TargetHighGoal extends ParallelCommandGroup {
    public TargetHighGoal(DriveTrain drivetrain, Vision vision) {
        addCommands(
            new TurnToHighGoal(drivetrain, vision)
        );
    }
}