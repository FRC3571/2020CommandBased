package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveStraightManual extends CommandBase {

    private final DriveTrain drivetrain;
    private double time;

    public DriveStraightManual (DriveTrain driveTrain) {
        this.drivetrain = driveTrain;
        time = 0;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        time = 0;
    }

    @Override
    public void execute() {
        drivetrain.arcadeDrive(0.3, 0, false);
        time += 20;
    }

    @Override
    public boolean isFinished() {         boolean finished = time >= 1000;
        return finished;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0, 0, false);
    }


}