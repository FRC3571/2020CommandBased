package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.DriveStraightDistance;

public class Auto extends SequentialCommandGroup {
    public Auto(){
        addCommands(
        new DriveStraightDistance(RobotContainer.driveTrain, 5)
        );
    }
}