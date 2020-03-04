package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.robot.util.XboxController;
import frc.robot.commands.auto.Auto;
import frc.robot.commands.controlpanel.PositionControl;
import frc.robot.commands.controlpanel.RotationControl;
import frc.robot.commands.drivetrain.ChangeGear;
import frc.robot.commands.drivetrain.DriveStraight;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.shooter.ChangeShooterPower;
import frc.robot.commands.shooter.Shoot;
import frc.robot.components.NAVX;
import frc.robot.subsystems.ControlPanel;
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
  // Constants used in this class
  public static final class Constants {
    public static final int kOperatorController = 1;
    public static final int kDriverController = 0;

    public enum ColorAssignment {
      RED, YELLOW, GREEN, BLUE, NONE
    }
  }

  // Initalizing Controllers
  private final XboxController driverController = new XboxController(Constants.kDriverController);
  private final XboxController operatorController = new XboxController(Constants.kOperatorController);

  // Initializing Other Components
  private final PowerDistributionPanel powerDistrubitionPanel = new PowerDistributionPanel();
  private final NAVX navx = new NAVX();

  // Initializing Subsystems
  private final DriveTrain driveTrain = new DriveTrain();
  private final Intake intake = new Intake();
  private final Shooter shooter = new Shooter();
  private final ControlPanel controlPanel = new ControlPanel();

  // Initializing Auto Command
  private final Command autoCommand = new Auto();

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

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Shooter
    operatorController.dPad.up.whenPressed(new ChangeShooterPower(shooter, true));
    operatorController.dPad.down.whenPressed(new ChangeShooterPower(shooter, false));
    operatorController.A.toggleWhenPressed(new Shoot(shooter));

    // Intake
    operatorController.B.toggleWhenPressed(new RunIntake(intake));

    // Climber

    // ControlPanel
    operatorController.X.toggleWhenPressed(new RotationControl(controlPanel));
    operatorController.Y.toggleWhenPressed(new PositionControl(controlPanel));

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

  public ControlPanel getControlPanel(){
    return controlPanel;
  }

  public NAVX getNAVX() {
    return navx;
  }

}
