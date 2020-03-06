package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.util.XboxController;
import frc.robot.commands.auto.Auto;
import frc.robot.commands.controlpanel.PositionControl;
import frc.robot.commands.controlpanel.RotationControl;
import frc.robot.commands.drivetrain.ChangeGear;
import frc.robot.commands.drivetrain.DriveStraight;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.intake.ToggleIntake;
import frc.robot.commands.shooter.ChangeShooterBottomSpeed;
import frc.robot.commands.shooter.ChangeShooterSpeedRatio;
import frc.robot.commands.Shoot;
import frc.robot.components.NAVX;
import frc.robot.components.Pneumatics;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Serializer;
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
  // Constants used in this class
  public static final class Constants {
    public static final int kOperatorController = 1;
    public static final int kDriverController = 0;
  }

  // Initalizing Controllers
  public final static XboxController driverController = new XboxController(Constants.kDriverController);
  public final static XboxController operatorController = new XboxController(Constants.kOperatorController);

  // Initializing Other Components
  public final static NAVX navx = new NAVX();

  // Initializing Subsystems
  public final static DriveTrain driveTrain = new DriveTrain();
  public final static Intake intake = new Intake();
  public final static Shooter shooter = new Shooter();
  public final static ControlPanel controlPanel = new ControlPanel();
  public final static Serializer serializer = new Serializer();
  public final static Pneumatics pneumatics = new Pneumatics();

  // Initializing Auto Command
  public final Command autoCommand = new Auto();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
  }

  public void log(){
    driveTrain.log();
    shooter.log();
  }

  public void refresh(){
    controlPanel.refresh();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Shooter
    operatorController.dPad.up.whenPressed(new ChangeShooterSpeedRatio(shooter, true));
    operatorController.dPad.down.whenPressed(new ChangeShooterSpeedRatio(shooter, false));
    operatorController.dPad.right.whenPressed(new ChangeShooterBottomSpeed(shooter, true));
    operatorController.dPad.left.whenPressed(new ChangeShooterBottomSpeed(shooter, false));
    operatorController.A.toggleWhenPressed(new Shoot(shooter, serializer));

    // Intake
    operatorController.B.toggleWhenPressed(new RunIntake(intake));
    operatorController.Y.whenPressed(new ToggleIntake(intake));

    // Climber

    // ControlPanel
    //operatorController.X.toggleWhenPressed(new RotationControl(controlPanel));
    //operatorController.Y.toggleWhenPressed(new PositionControl(controlPanel, operatorController));

    // Drive
    driverController.RT.whenPressed(new ChangeGear(driveTrain, false));
    driverController.RB.whenPressed(new ChangeGear(driveTrain, true));
    driverController.LT.whileActiveContinuous(new DriveStraight(driveTrain, driverController));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    return autoCommand;
  }
}
