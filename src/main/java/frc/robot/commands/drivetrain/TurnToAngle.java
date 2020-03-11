package frc.robot.commands.drivetrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.util.RobotMath;

public class TurnToAngle extends PIDCommand {

    private static final double kP = 1;
    private static final double kI = 0;
    private static final double kD = 0;

    private static final double kToleranceDegrees = 2;

    public TurnToAngle(final DriveTrain driveTrain, final AHRS ahrs, final double angle) {
        super(new PIDController(kP, kI, kD), ahrs::getYaw, angle, output -> driveTrain.arcadeDrive(0, output, false),
                driveTrain);
        getController().enableContinuousInput(-180, 180);
        getController().setTolerance(kToleranceDegrees);
    }

    public TurnToAngle(final DriveTrain driveTrain, final AHRS ahrs, final double x, final double y) {
        super(new PIDController(kP, kI, kD), ahrs::getYaw, RobotMath.fixAngle(RobotMath.getAngleFromPoint(x, y)),
                output -> driveTrain.arcadeDrive(0, output, false), driveTrain);
        getController().enableContinuousInput(-180, 180);
        getController().setTolerance(kToleranceDegrees);
    }

    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}