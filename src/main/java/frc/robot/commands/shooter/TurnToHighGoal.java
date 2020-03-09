package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.components.Vision;
import frc.robot.subsystems.DriveTrain;

public class TurnToHighGoal extends PIDCommand {

    private static final double kP = 0.015;
    private static final double kI = 0.000;
    private static final double kD = 0.00;

    private static final double kTolerance = 0.05;
    
    public TurnToHighGoal(final DriveTrain driveTrain, final Vision vision) {
        super(new PIDController(kP, kI, kD), vision::getHighTargetX, 0, output -> driveTrain.arcadeDrive(0, output, false),
                driveTrain);
        getController().setTolerance(kTolerance);
    }
}