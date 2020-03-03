/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.robot.util.XboxController;
import frc.robot.commands.auto.Auto;
import frc.robot.components.NAVX;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public static final class Constants {
    public static final int kOperatorController = 1;
    public static final int kDriverController = 0;

    public enum ColorAssignment {
      RED, YELLOW, GREEN, BLUE, NONE
    }
  }

  private final XboxController driverController = new XboxController(Constants.kDriverController);
  private final XboxController operatorController = new XboxController(Constants.kOperatorController);

  private final PowerDistributionPanel powerDistrubitionPanel = new PowerDistributionPanel();

  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain = new DriveTrain();
  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  private final NAVX navx = new NAVX();

  private final Command autoCommand = new Auto();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() { // An ExampleCommand will run in autonomous
    return autoCommand;
  }

  // Setters

  // Getters
  public XboxController getDriverController() {
    return driverController;
  }

  public DriveTrain getDriveTrain() {
    return driveTrain;
  }

  public Intake getIntake() {
    return intake;
  }

  public Shooter getShooter() {
    return shooter;
  }

  public NAVX getNAVX() {
    return navx;
  }

}
