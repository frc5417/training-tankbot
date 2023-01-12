// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private static CANSparkMax leftDrive1;
  private static CANSparkMax leftDrive2;
  private static CANSparkMax rightDrive1;
  private static CANSparkMax rightDrive2;
  

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    // define motor controllers - uses port value from Constants file.
    leftDrive1 = new CANSparkMax(Constants.leftDrive1Port, MotorType.kBrushless);
    leftDrive2 = new CANSparkMax(Constants.leftDrive2Port, MotorType.kBrushless);
    rightDrive1 = new CANSparkMax(Constants.rightDrive1Port, MotorType.kBrushless);
    rightDrive2 = new CANSparkMax(Constants.rightDrive2Port, MotorType.kBrushless);

    // sets the right side to inverted, so we don't have to worry about converting it when we set the percentage.
    rightDrive1.setInverted(true);
    rightDrive2.setInverted(true);

    // set the 2nd motor controllers on each side to follow the first, so we only have to activate one and the other will mimick it.
    leftDrive2.follow(leftDrive1);
    rightDrive2.follow(rightDrive1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double leftJoystick, double rightJoystick) {
    // set a deadband (motors will not run between the negative and positive value of it)
    if (Math.abs(leftJoystick) < Constants.joytickDeadBand) {
      leftJoystick = 0;
    }

    if (Math.abs(rightJoystick) < Constants.joytickDeadBand) {
      rightJoystick = 0;
    }

    leftDrive1.set(leftJoystick);
    rightDrive1.set(rightJoystick);
  }
}
