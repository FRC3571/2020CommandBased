package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.serializer.RunSerializer;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.subsystems.Serializer;
import frc.robot.subsystems.Shooter;

public class Shoot extends ParallelCommandGroup {
    public Shoot(Shooter shooter, Serializer serializer){
        addCommands(
            new RunShooter(shooter),
            new RunSerializer(serializer)
        );
    }
}