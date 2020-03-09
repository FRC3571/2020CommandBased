package frc.robot.commands.serializer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Serializer;

public class RunSerializer extends CommandBase {
    private final double outerSpeed = 0.3;
    private final double innerSpeed = 0.9;

    private final Serializer serializer;

    public RunSerializer(final Serializer serializer) {
        this.serializer = serializer;
        addRequirements(serializer);
    }

    @Override
    public void initialize() {
        SmartDashboard.putBoolean("Serializer/Running", true);
    }

    @Override
    public void execute() {
        serializer.setMotors(innerSpeed, outerSpeed);
    }

    @Override
    public void end(final boolean interrupted) {
        serializer.setMotors(0, 0);
        SmartDashboard.putBoolean("Serializer/Running", false);
    }
}