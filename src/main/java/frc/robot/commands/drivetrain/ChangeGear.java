package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.DriveTrain;

public class ChangeGear extends InstantCommand {
    private boolean up;
    private DriveTrain driveTrain;

    public ChangeGear(DriveTrain driveTrain, boolean up) {
        this.driveTrain = driveTrain;
        this.up = up;
    }

    @Override
    public void execute() {
        if (up) {
            switch (driveTrain.getChosenGear()) {
            case FIRST:
                driveTrain.setChosenGear(DriveTrain.Constants.Gear.SECOND);
                break;
            case SECOND:
                driveTrain.setChosenGear(DriveTrain.Constants.Gear.THIRD);
                break;
            case THIRD:
                driveTrain.setChosenGear(DriveTrain.Constants.Gear.FOURTH);
                break;
            case FOURTH:
                break;
            default:
                break;
            }
        } else {
            switch (driveTrain.getChosenGear()) {
            case FIRST:
                break;
            case SECOND:
                driveTrain.setChosenGear(DriveTrain.Constants.Gear.FIRST);
                break;
            case THIRD:
                driveTrain.setChosenGear(DriveTrain.Constants.Gear.SECOND);
                break;
            case FOURTH:
                driveTrain.setChosenGear(DriveTrain.Constants.Gear.THIRD);
                break;
            default:
                break;
            }
        }
    }
}