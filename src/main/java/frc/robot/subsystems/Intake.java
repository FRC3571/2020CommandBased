package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
    // Constants used in this class
    public static final class Constants {
        private static final int kMotorID = 3;

        private static final class LeftSolenoid {
            private static final int kSolenoidPort = 0;
            private static final int kFirstID = 2;
            private static final int kSecondID = 5;
        }

        private static final class RightSolenoid {
            private static final int kSolenoidPort = 1;
            private static final int kFirstID = 3;
            private static final int kSecondID = 4;
        }
    }

    private final TalonSRX motor;
    private boolean openState;

    public Intake() {
        motor = new TalonSRX(Constants.kMotorID);
        openState = false;
        RobotContainer.pneumatics.createSolenoid(Constants.LeftSolenoid.kSolenoidPort, Constants.LeftSolenoid.kFirstID,
                Constants.LeftSolenoid.kSecondID);
        RobotContainer.pneumatics.createSolenoid(Constants.RightSolenoid.kSolenoidPort,
                Constants.RightSolenoid.kFirstID, Constants.RightSolenoid.kSecondID);

        setSolenoids(openState);

    }

    public void log(){
        SmartDashboard.putBoolean("IntakeState", openState);
    }

    public void setMotor(final double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }

    public void setSolenoids(final boolean openState) {
        if (openState) {
            RobotContainer.pneumatics.solenoidReverse(Constants.LeftSolenoid.kSolenoidPort);
            RobotContainer.pneumatics.solenoidReverse(Constants.RightSolenoid.kSolenoidPort);
        } else {
            RobotContainer.pneumatics.solenoidForward(Constants.LeftSolenoid.kSolenoidPort);
            RobotContainer.pneumatics.solenoidForward(Constants.RightSolenoid.kSolenoidPort);
        }
        this.openState = openState;
    }

    public boolean getOpenState(){
        return openState;
    }
}