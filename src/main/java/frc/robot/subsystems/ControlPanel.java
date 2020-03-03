package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.ControlPanel.Constants.CPColor;

public class ControlPanel extends SubsystemBase {
    // Make the operator controller rumble when game data is received

    public static final class Constants {
        private static final int motorID = 8; //random number for now

        public enum CPColor {
            RED, YELLOW, GREEN, BLUE, NONE
        }
    }

    private VictorSPX motor;
    private final I2C.Port i2cPort;
    private final ColorSensorV3 m_colorSensor;
    private final ColorMatch m_colorMatcher;
    private final Color kBlue, kGreen, kRed, kYellow;
    private String gameData;
    private CPColor colorAssignment;

    public ControlPanel() {
        i2cPort = I2C.Port.kOnboard;
        m_colorSensor = new ColorSensorV3(i2cPort);
        m_colorMatcher = new ColorMatch();

        kBlue = ColorMatch.makeColor(0.148, 0.436, 0.420);
        kGreen = ColorMatch.makeColor(0.202, 0.547, 0.250);
        kRed = ColorMatch.makeColor(0.450, 0.385, 0.162);
        kYellow = ColorMatch.makeColor(0.323, 0.540, 0.138);

        m_colorMatcher.addColorMatch(kBlue);
        m_colorMatcher.addColorMatch(kGreen);
        m_colorMatcher.addColorMatch(kRed);
        m_colorMatcher.addColorMatch(kYellow);

        motor = new VictorSPX(Constants.motorID);
    }

    public CPColor findColor() {
        Color detectedColor = m_colorSensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
        CPColor result = CPColor.NONE;

        if (match.color == kBlue) {
            result = CPColor.BLUE;
        } else if (match.color == kRed) {
            result = CPColor.RED;
        } else if (match.color == kGreen) {
            result = CPColor.GREEN;
        } else if (match.color == kYellow) {
            result = CPColor.YELLOW;
        }

        SmartDashboard.putString("Detected Color", result.toString());
        return result;
    }

    private void detectColorAssignment() {
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData.length() > 0) {
            switch (gameData.charAt(0)) {
            case 'B':
            colorAssignment = CPColor.BLUE;
                break;
            case 'G':
                colorAssignment = CPColor.GREEN;
                break;
            case 'R':
                colorAssignment = CPColor.RED;
                break;
            case 'Y':
                colorAssignment = CPColor.YELLOW;
                break;
            default:
                // This is corrupt data
                colorAssignment = CPColor.NONE;
                break;
            }
        }
    }

    public void setMotor(double speed){
        motor.set(ControlMode.PercentOutput, speed);
    }

    public void refresh(){
        detectColorAssignment();
    }

    public CPColor getColorAssignment(){
        return colorAssignment;
    }

}