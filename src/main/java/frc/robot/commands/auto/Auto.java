package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.Wait;
import frc.robot.commands.drivetrain.DriveStraightDistance;
import frc.robot.commands.drivetrain.DriveStraightManual;
import frc.robot.commands.intake.ToggleIntake;

public class Auto extends SequentialCommandGroup {
    public Auto(){
        addCommands(
        new ToggleIntake(RobotContainer.intake),
        new DriveStraightManual(RobotContainer.driveTrain)
        );
    }
}