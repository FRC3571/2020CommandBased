package frc.robot.commands.serializer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Serializer;

public class RunSerializer extends CommandBase {
    private final double speed = 0.5;

    private final Serializer serializer;

    public RunSerializer(final Serializer serializer) {
        this.serializer = serializer;
        addRequirements(serializer);
    }

    @Override
    public void execute() {
        serializer.setMotor(speed);
    }

    @Override
    public void end(final boolean interrupted) {
        serializer.setMotor(0);
    }
}