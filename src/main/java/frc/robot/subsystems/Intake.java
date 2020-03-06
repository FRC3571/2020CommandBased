package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
    // Constants used in this class
    public static final class Constants {
        private static final int kMotorID = 1;

        private static final class LeftSolenoid {
            private static final int kSolenoidPort = 1;
            private static final int kFirstID = 1;
            private static final int kSecondID = 6;
        }

        private static final class RightSolenoid {
            private static final int kSolenoidPort = 2;
            private static final int kFirstID = 1;
            private static final int kSecondID = 6;
        }
    }

    private final VictorSPX motor;
    private boolean openState;

    public Intake() {
        motor = new VictorSPX(Constants.kMotorID);
        openState = false;
        RobotContainer.pneumatics.createSolenoid(Constants.LeftSolenoid.kSolenoidPort, Constants.LeftSolenoid.kFirstID,
                Constants.LeftSolenoid.kSecondID);
        RobotContainer.pneumatics.createSolenoid(Constants.RightSolenoid.kSolenoidPort,
                Constants.RightSolenoid.kFirstID, Constants.RightSolenoid.kSecondID);

        setSolenoids(openState);

    }

    public void setMotor(final double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }

    public void setSolenoids(final boolean openState) {
        if (openState) {
            RobotContainer.pneumatics.solenoidForward(Constants.LeftSolenoid.kSolenoidPort);
            RobotContainer.pneumatics.solenoidForward(Constants.RightSolenoid.kSolenoidPort);
        } else {
            RobotContainer.pneumatics.solenoidReverse(Constants.LeftSolenoid.kSolenoidPort);
            RobotContainer.pneumatics.solenoidReverse(Constants.RightSolenoid.kSolenoidPort);
        }
    }

    public boolean getOpenState(){
        return openState;
    }
}