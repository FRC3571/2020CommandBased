package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.DriveStraightManual;
import frc.robot.commands.intake.ToggleIntake;

public class Auto extends SequentialCommandGroup {
    public Auto() {
        addCommands(new WaitCommand(10), new ToggleIntake(RobotContainer.intake),
                new DriveStraightManual(RobotContainer.driveTrain));
    }
}