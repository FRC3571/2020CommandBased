package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.shooter.Shoot;

public class Auto extends SequentialCommandGroup {
    public Auto(){
        addCommands(
        new Shoot()
        );
    }
}