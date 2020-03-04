package frc.robot.commands.drivetrain;

import frc.robot.subsystems.DriveTrain;
import frc.robot.util.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveJoystick extends CommandBase {
  private final DriveTrain driveTrain;
  private final XboxController controller;

  public DriveJoystick(DriveTrain driveTrain, XboxController controller) {
    this.driveTrain = driveTrain;
    this.controller = controller;
    addRequirements(driveTrain);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (driveTrain.getChosenDrive()) {
    case AONEJOY:
      driveTrain.arcadeDrive(controller.leftStick.getY(), controller.leftStick.getX(), true);
      break;
    case ATWOJOY:
      driveTrain.arcadeDrive(controller.leftStick.getY(), controller.rightStick.getX(), true);
      break;
    case TANK:
      driveTrain.tankdrive(controller.leftStick.getY(), controller.rightStick.getY(), true);
      break;
    default:
      driveTrain.arcadeDrive(controller.leftStick.getY(), -controller.rightStick.getX(), true);
      break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.arcadeDrive(0, 0, false);
  }
}
