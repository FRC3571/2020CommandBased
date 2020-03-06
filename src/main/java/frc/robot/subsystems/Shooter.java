package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    // Constants used in this class
    public static final class Constants {
        private static final int kTopMotorID = 21;
        private static final int kBottomMotorID = 11;
    }

    private CANSparkMax topMotor, bottomMotor;

    private CANEncoder topEncoder, bottomEncoder;

    private double topSpeed, bottomSpeed, topBottomRatio;

    public Shooter() {
        topMotor = new CANSparkMax(Constants.kTopMotorID, MotorType.kBrushless);
        bottomMotor = new CANSparkMax(Constants.kBottomMotorID, MotorType.kBrushless);

        topMotor.restoreFactoryDefaults();
        bottomMotor.restoreFactoryDefaults();

        topEncoder = topMotor.getEncoder();
        bottomEncoder = bottomMotor.getEncoder();

        topMotor.setInverted(false);
        bottomMotor.setInverted(true);

        topBottomRatio = 0.85;
        bottomSpeed = 0.7;
        topSpeed = bottomSpeed * topBottomRatio;
    }

    public void log() {
        SmartDashboard.putNumber("Shooter/TopMotor/Speed", topSpeed);
        SmartDashboard.putNumber("Shooter/BottomMotor/Speed", bottomSpeed);
    }

    public void setMotors(double topSpeed, double bottomSpeed) {
        topMotor.set(topSpeed);
        bottomMotor.set(bottomSpeed);
    }

    public void setSpeed(double bottomSpeed) {
        this.bottomSpeed = Math.max(0, Math.min(bottomSpeed, 1));
        this.topSpeed = bottomSpeed *= topBottomRatio;
    }

    public double getBottomSpeed() {
        return bottomSpeed;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public double getTopBottomRatio(){
        return topBottomRatio;
    }

    public void setTopBottomRatio(double topBottomRatio){
        this.topBottomRatio = Math.max(0, Math.min(topBottomRatio, 1));
    }
}