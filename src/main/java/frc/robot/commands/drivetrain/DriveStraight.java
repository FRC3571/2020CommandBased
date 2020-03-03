package frc.robot.commands.drivetrain;

import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.util.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class DriveStraight extends PIDCommand {

    private static final double kP = 0.5;
    private static final double kI = 0.000;
    private static final double kD = 0.00;

    private final static XboxController controller = Robot.getRobotContainer().getDriverController();
    private final static DriveTrain driveTrain = Robot.getRobotContainer().getDriveTrain();

    public DriveStraight() {
        super(new PIDController(kP, kI, kD), driveTrain::getDriveStraightError, 0, output -> 
        driveTrain.arcadeDrive(controller.LT.getX(), output, false), driveTrain);
        driveTrain.resetEncoders();
    }
}
