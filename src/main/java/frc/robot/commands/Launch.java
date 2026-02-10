// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Launch extends Command {
  /** Creates a new Intake. */


  CANFuelSubsystem fuelSubsystem;
  CommandXboxController controller;

  public Launch(CANFuelSubsystem fuelSystem, CommandXboxController driverController) {
    addRequirements(fuelSystem);
    this.fuelSubsystem = fuelSystem;
     controller = driverController;
  }

  // Called when the command is initially scheduled. Set the rollers to the
  // appropriate values for intaking
  @Override
  public void initialize() {
double launcher_voltage_use = 0;
double launcher_feeder_vUse = 0;
    if (controller.getHID().getXButton()){
       launcher_voltage_use = 12;
       launcher_feeder_vUse = 12;
    }else{
         launcher_voltage_use = LAUNCHING_LAUNCHER_VOLTAGE;
          launcher_feeder_vUse = LAUNCHING_FEEDER_VOLTAGE;
  }

    fuelSubsystem
        .setIntakeLauncherRoller(
            SmartDashboard.getNumber("Launching launcher roller value", launcher_voltage_use));
    fuelSubsystem.setFeederRoller(SmartDashboard.getNumber("Launching feeder roller value", launcher_feeder_vUse));
  }

  // Called every time the scheduler runs while the command is scheduled. This
  // command doesn't require updating any values while running
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted. Stop the rollers
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
