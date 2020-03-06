package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Serializer extends SubsystemBase {

    // Constants used in this class
    public static final class Constants {
        private static final int kMotorID = 1;
    }

    private VictorSPX motor;

    public Serializer() {
        motor = new VictorSPX(Constants.kMotorID);
    }

    public void setMotor(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }
}