package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    public static final class Constants {
        private static final int kTopMotorID = 11;
        private static final int kBottomMotorID = 21;
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

        topBottomRatio = 0.75;
        bottomSpeed = 1;
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

    public void setBottomSpeed(double bottomSpeed) {
        this.bottomSpeed = bottomSpeed;
    }

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed = topSpeed;
    }
}