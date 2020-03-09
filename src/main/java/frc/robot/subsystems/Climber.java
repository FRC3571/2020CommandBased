package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Climber extends SubsystemBase {
    public static final class Constants {
        private static final int kSolenoidPort = 2;
        private static final int kFirstID = 1;
        private static final int kSecondID = 6;
    }

    public Climber() {
        RobotContainer.pneumatics.createSolenoid(Constants.kSolenoidPort, Constants.kFirstID, Constants.kSecondID);
    }

    public void setSolenoid(boolean openState){

    }

}