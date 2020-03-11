package frc.robot.commands.drivetrain;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriveTrain.Constants.Gear;
import frc.robot.util.RobotMath;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveStraightDistance extends CommandBase {
    private double targetDistance;
    private final CANPIDController leftPID, rightPID;
    private final double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxVel;
    private double minVel;
    private final double maxAcc;
    private final double allowedErr;
    private double error;
    private final DriveTrain driveTrain;
    private AHRS ahrs;
    private Gear prevGear;

    public DriveStraightDistance(final DriveTrain driveTrain, final double d) {
        this.driveTrain = driveTrain;
        targetDistance = d;
        leftPID = driveTrain.getLeftFrontMotor().getPIDController();
        rightPID = driveTrain.getRightFrontMotor().getPIDController();

        // PID coefficients
        kP = 5e-5;
        kI = 1e-6;
        kD = 0;
        kIz = 0;
        kFF = 0.000156; // 0.0003
        kMaxOutput = 1;
        kMinOutput = -1;

        // Smart Motion Coefficients
        maxVel = 2000; // rpm
        maxAcc = 1500;
        allowedErr = 0.001;

        // set PID coefficients
        leftPID.setP(kP);
        leftPID.setI(kI);
        leftPID.setD(kD);
        leftPID.setIZone(kIz);
        leftPID.setFF(kFF);
        leftPID.setOutputRange(kMinOutput, kMaxOutput);

        rightPID.setP(kP);
        rightPID.setI(kI);
        rightPID.setD(kD);
        rightPID.setIZone(kIz);
        rightPID.setFF(kFF);
        rightPID.setOutputRange(kMinOutput, kMaxOutput);

        final int smartMotionSlot = 0;
        leftPID.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
        leftPID.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
        leftPID.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
        leftPID.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);

        rightPID.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
        rightPID.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
        rightPID.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
        rightPID.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);

        addRequirements(driveTrain);
    }

    public DriveStraightDistance(final DriveTrain driveTrain, final AHRS ahrs, final double x, final double y) {
        this.driveTrain = driveTrain;
        this.ahrs = ahrs;
        final double xTarget = x;
        final double yTarget = y;

        targetDistance = RobotMath.getDistanceFromPoint(xTarget, yTarget);

        if (Math.abs(RobotMath.getAngleFromPoint(xTarget, yTarget) - ahrs.getYaw()) > 90) {
            targetDistance *= -1;
        }

        leftPID = driveTrain.getLeftFrontMotor().getPIDController();
        rightPID = driveTrain.getRightFrontMotor().getPIDController();

        // PID coefficients
        kP = 5e-5;
        kI = 1e-6;
        kD = 0;
        kIz = 0;
        kFF = 0.000156; // 0.0003
        kMaxOutput = 1;
        kMinOutput = -1;

        // Smart Motion Coefficients
        maxVel = 2000; // rpm
        maxAcc = 1500;
        allowedErr = 0.001;

        // set PID coefficients
        leftPID.setP(kP);
        leftPID.setI(kI);
        leftPID.setD(kD);
        leftPID.setIZone(kIz);
        leftPID.setFF(kFF);
        leftPID.setOutputRange(kMinOutput, kMaxOutput);

        rightPID.setP(kP);
        rightPID.setI(kI);
        rightPID.setD(kD);
        rightPID.setIZone(kIz);
        rightPID.setFF(kFF);
        rightPID.setOutputRange(kMinOutput, kMaxOutput);

        final int smartMotionSlot = 0;
        leftPID.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
        leftPID.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
        leftPID.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
        leftPID.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);

        rightPID.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
        rightPID.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
        rightPID.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
        rightPID.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);

        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.resetEncoders();
        prevGear = driveTrain.getChosenGear();
        driveTrain.setChosenGear(DriveTrain.Constants.Gear.FOURTH);
    }

    @Override
    public void execute() {
        leftPID.setReference(targetDistance, ControlType.kSmartMotion);
        rightPID.setReference(targetDistance, ControlType.kSmartMotion);

        error = Math.abs(targetDistance - driveTrain.getDistance());
    }

    @Override
    public boolean isFinished() {
        return error < 0.001;
    }

    @Override
    public void end(final boolean interrupted) {
        driveTrain.setChosenGear(prevGear);
    }
}
