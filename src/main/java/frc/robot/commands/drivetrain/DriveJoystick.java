/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.util.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveJoystick extends CommandBase {
  private final DriveTrain driveTrain;
  private final XboxController controller;

  public DriveJoystick() {
    this.driveTrain = Robot.getRobotContainer().getDriveTrain();
    this.controller = Robot.getRobotContainer().getDriverController();
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
