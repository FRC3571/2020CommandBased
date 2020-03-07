package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Serializer extends SubsystemBase {

    // Constants used in this class
    public static final class Constants {
        private static final int kLeftMotorID = 1;
        private static final int kRightMotorID = 2;
        private static final int kTopMotorID = 4;
    }

    private TalonSRX leftMotor, rightMotor, topMotor;

    public Serializer() {
        leftMotor = new TalonSRX(Constants.kLeftMotorID);
        rightMotor = new TalonSRX(Constants.kRightMotorID);
        topMotor = new TalonSRX(Constants.kTopMotorID);

        leftMotor.setInverted(false);
        rightMotor.setInverted(true);
        topMotor.setInverted(false);;
    }

    public void setMotor(double speed) {
        leftMotor.set(ControlMode.PercentOutput, speed);
        rightMotor.set(ControlMode.PercentOutput, speed);
        topMotor.set(ControlMode.PercentOutput, speed);
    }
}