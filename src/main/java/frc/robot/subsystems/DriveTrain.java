/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.DriveTrain.Constants.*;

public class DriveTrain extends SubsystemBase {

  public static final class Constants {
    private static final double kGearRatioLow = 4.6;
    private static final double kGearRatioHigh = 2.7;

    private static final int kLeftFrontID = 12;
    private static final int kLeftBackID = 13;
    private static final int kRightFrontID = 22;
    private static final int kRightBackID = 21;

    private static final double kGearRatioFirst = 0.3;
    private static final double kGearRatioSecond = 0.4;
    private static final double kGearRatioThird = 0.5;

    // Drive Modes
    public enum DriveMode {
      AONEJOY, ATWOJOY, TANK,
    }

    // Gears (Speeds)
    public enum Gear {
      FIRST, SECOND, THIRD, FOURTH,
    }
  }

  public DriveMode ChosenDrive;
  private Gear ChosenGear;
  private SendableChooser<DriveMode> DriveModeChooser;
  // SparkMax Objects
  private CANSparkMax leftFrontMotor, rightFrontMotor, leftBackMotor, rightBackMotor;

  // Speed Controller Groups
  SpeedControllerGroup leftMotors, rightMotors;

  // underlying mechanism
  private DifferentialDrive drive;

  // Distance Encoders
  private CANEncoder leftFrontEncoder, rightFrontEncoder, leftBackEncoder, rightBackEncoder;

  private double distance, leftDistance, rightDistance, xPos, yPos;

  public DriveTrain() {

    rightFrontMotor = new CANSparkMax(Constants.kRightFrontID, MotorType.kBrushless);
    leftFrontMotor = new CANSparkMax(Constants.kLeftFrontID, MotorType.kBrushless);
    rightBackMotor = new CANSparkMax(Constants.kRightBackID, MotorType.kBrushless);
    leftBackMotor = new CANSparkMax(Constants.kLeftBackID, MotorType.kBrushless);

    rightFrontMotor.restoreFactoryDefaults();
    leftFrontMotor.restoreFactoryDefaults();
    rightBackMotor.restoreFactoryDefaults();
    leftBackMotor.restoreFactoryDefaults();

    rightFrontMotor.setInverted(false);
    leftFrontMotor.setInverted(false);
    rightBackMotor.setInverted(false);
    leftBackMotor.setInverted(false);

    leftMotors = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
    rightMotors = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);

    drive = new DifferentialDrive(leftMotors, rightMotors);

    initEncoders();

    xPos = 0;
    yPos = 0;

    ChosenDrive = DriveMode.ATWOJOY;
    ChosenGear = Gear.THIRD;

    DriveModeChooser = new SendableChooser<>();
    DriveModeChooser.setDefaultOption("Arcade - One Joystick", DriveMode.AONEJOY);
    DriveModeChooser.addOption("Arcade - Two Joystick", DriveMode.ATWOJOY);
    DriveModeChooser.addOption("Tank", DriveMode.TANK);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private void initEncoders() {
  }

  public void arcadeDrive(double throttle, double rotate, boolean squaredRotation) {
    switch (ChosenGear) {
    case FIRST:
      throttle *= Constants.kGearRatioFirst;
      rotate *= Constants.kGearRatioFirst;
      break;
    case SECOND:
      throttle *= Constants.kGearRatioSecond;
      rotate *= Constants.kGearRatioSecond;
      break;
    case THIRD:
      throttle *= Constants.kGearRatioSecond;
      rotate *= Constants.kGearRatioSecond;
      break;
    default:
      break;
    }

    SmartDashboard.putNumber("DriveTrain/Drive/ArcadeDrive/Throttle", throttle);

    SmartDashboard.putNumber("DriveTrain/Drive/ArcadeDrive/Rotate", rotate);

    drive.arcadeDrive(throttle, rotate, squaredRotation);
  }

  public void tankdrive(double left, double right, boolean squaredRotation) {
    switch (ChosenGear) {
    case FIRST:
      left *= Constants.kGearRatioFirst;
      right *= Constants.kGearRatioFirst;
      break;
    case SECOND:
      left *= Constants.kGearRatioSecond;
      right *= Constants.kGearRatioSecond;
      break;
    case THIRD:
      left *= Constants.kGearRatioSecond;
      right *= Constants.kGearRatioSecond;
      break;
    default:
      break;
    }

    SmartDashboard.putNumber("DriveTrain/Drive/TankDrive/Left", left);

    SmartDashboard.putNumber("DriveTrain/Drive/TankDrive/Right", right);

    drive.tankDrive(left, right, squaredRotation);
  }

}
