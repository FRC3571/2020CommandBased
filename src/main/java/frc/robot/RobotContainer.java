package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.util.XboxController;
import frc.robot.commands.auto.Auto;
import frc.robot.commands.controlpanel.PositionControl;
import frc.robot.commands.controlpanel.RotationControl;
import frc.robot.commands.drivetrain.ChangeGear;
import frc.robot.commands.drivetrain.DriveStraight;
import frc.robot.commands.drivetrain.DriveStraightDistance;
import frc.robot.commands.drivetrain.DriveStraightManual;
import frc.robot.commands.intake.RunIntake;
import frc.robot.commands.intake.ToggleIntake;
import frc.robot.commands.serializer.RunSerializer;
import frc.robot.commands.shooter.ChangeShooterBottomSpeed;
import frc.robot.commands.shooter.ChangeShooterSpeedRatio;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.RobotContainer.Constants.AutoMode;
import frc.robot.commands.Shoot;
import frc.robot.components.NAVX;
import frc.robot.components.Pneumatics;
import frc.robot.components.Vision;
import frc.robot.subsystems.Climber;
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

    public enum AutoMode {
      DEFAULT, NONE
    }
  }

  // Initalizing Controllers
  public final static XboxController driverController = new XboxController(Constants.kDriverController);
  public final static XboxController operatorController = new XboxController(Constants.kOperatorController);

  // Initializing Other Components
  public final static NAVX navx = new NAVX();
  public final static Vision vision = new Vision();

  // Initializing Subsystems
  public final static Pneumatics pneumatics = new Pneumatics();
  public final static DriveTrain driveTrain = new DriveTrain();
  public final static Intake intake = new Intake();
  public final static Shooter shooter = new Shooter();
  public final static ControlPanel controlPanel = new ControlPanel();
  private final static Climber climber = new Climber();
  public final static Serializer serializer = new Serializer();

  // Initializing Auto Command and Chooser
  private final SendableChooser autoChooser;
  public Command autoCommand;
  private AutoMode chosenAuto;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();

    autoChooser = new SendableChooser<>();
    autoChooser.setDefaultOption("Default", AutoMode.DEFAULT);
    autoChooser.addOption("None", AutoMode.NONE);
  }

  public void log() {
    driveTrain.log();
    shooter.log();
    intake.log();
  }

  public void refresh() {
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
    operatorController.A.toggleWhenPressed(new RunShooter(shooter));
    operatorController.X.toggleWhenPressed(new RunSerializer(serializer));

    // Intake
    operatorController.B.toggleWhenPressed(new RunIntake(intake));
    operatorController.Y.whenPressed(new ToggleIntake(intake));

    // Climber

    // ControlPanel
    // operatorController.X.toggleWhenPressed(new RotationControl(controlPanel));
    // operatorController.Y.toggleWhenPressed(new PositionControl(controlPanel,
    // operatorController));

    // Drive
    driverController.RT.whenPressed(new ChangeGear(driveTrain, false));
    driverController.RB.whenPressed(new ChangeGear(driveTrain, true));
    // driverController.LT.whileActiveContinuous(new DriveStraight(driveTrain,
    // driverController));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    chosenAuto = (AutoMode) autoChooser.getSelected();
    switch (chosenAuto) {
    case DEFAULT:
      autoCommand = new Auto();
      break;

    case NONE:
      autoCommand = null;
      break;

    default:
      break;

    }

    return autoCommand;
  }
}
