package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

    public static final class Constants {
        private static final int kMotorID = 1;
    }

    private VictorSPX motor;

    public Intake() {
        motor = new VictorSPX(Constants.kMotorID);
    }

    public void setMotor(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }
}