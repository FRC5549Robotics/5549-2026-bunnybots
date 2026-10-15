// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.elevator;
import frc.robot.subsystems.groundIntake;
import frc.robot.subsystems.EndEffector;

import java.time.Instant;

import com.fasterxml.jackson.databind.util.Named;
import com.pathplanner.lib.auto.NamedCommands;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final elevator m_elevator = new elevator();
  private final groundIntake m_groundIntake = new groundIntake();
  private final EndEffector m_endEffector = new EndEffector();
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController = new CommandXboxController(OperatorConstants.kOperatorControllerPort);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    NamedCommands.registerCommand("Level1", new InstantCommand(m_elevator::level1));
    NamedCommands.registerCommand("Level2", new InstantCommand(m_elevator::level2));
    NamedCommands.registerCommand("Level3", new InstantCommand(m_elevator::level3));
    NamedCommands.registerCommand("LevelIntake", new InstantCommand(m_elevator::levelIntake));
    NamedCommands.registerCommand("IntakeEE", new InstantCommand(m_endEffector::intake));
    NamedCommands.registerCommand("OuttakeEE", new InstantCommand(m_endEffector::outake));
    NamedCommands.registerCommand("PivotUp", new InstantCommand(m_groundIntake::PivotUp));
    NamedCommands.registerCommand("PivotDown", new InstantCommand(m_groundIntake::PivotDown));
    NamedCommands.registerCommand("OvenPosition", new InstantCommand(m_groundIntake::GoToOven));
    NamedCommands.registerCommand("IntakeG", new InstantCommand(m_groundIntake::Intake));




  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    /*new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    */

    // This is moving the elevator
    // Right joystick : Elevator Up
    m_operatorController.axisGreaterThan(5, -OperatorConstants.JOYSTICK_DEADBAND).onTrue(new InstantCommand(m_elevator::goUp)).onFalse(new InstantCommand(m_elevator::stop));
    // Right joystick : Elevator Down
    m_operatorController.axisLessThan(5, OperatorConstants.JOYSTICK_DEADBAND).onTrue(new InstantCommand(m_elevator::goDown)).onFalse(new InstantCommand(m_elevator::stop));

    // This is moving the elevator
    // Button-A : Elevator Level 1
    m_operatorController.button(0).onTrue(new InstantCommand(m_elevator::level1)).onFalse(new InstantCommand(m_elevator::stop));
    // Button-B : Elevator Level 2
    m_operatorController.button(1).onTrue(new InstantCommand(m_elevator::level2)).onFalse(new InstantCommand(m_elevator::stop));
    // Button-X : Elevator Level 3
    m_operatorController.button(2).onTrue(new InstantCommand(m_elevator::level3)).onFalse(new InstantCommand(m_elevator::stop));
    // Button-Y : Elevator Intake
    m_operatorController.button(3).onTrue(new InstantCommand(m_elevator::levelIntake)).onFalse(new InstantCommand(m_elevator::stop));

    // This is moving the ground intake
    // Left bumper : Ground Intake
    m_operatorController.leftBumper().onTrue(new InstantCommand(m_groundIntake::Intake)).onFalse(new InstantCommand(m_groundIntake::Off));
    // Right bumper : Ground Intake Go to oven position
    m_operatorController.rightBumper().onTrue(new InstantCommand(m_groundIntake::GoToOven)).onFalse(new InstantCommand(m_groundIntake::Off));

    // Left joystick : Ground Intake Pivot Up
    m_operatorController.axisGreaterThan(1, -OperatorConstants.JOYSTICK_DEADBAND).onTrue(new InstantCommand(m_groundIntake::PivotUp)).onFalse(new InstantCommand(m_groundIntake::Off));
    // Left joystick : Ground Intake Pivot Down
    m_operatorController.axisLessThan(1, OperatorConstants.JOYSTICK_DEADBAND).onTrue(new InstantCommand(m_groundIntake::PivotDown)).onFalse(new InstantCommand(m_groundIntake::Off));
    

    // This is moving the end effector
    // Left trigger : End Effector Intake
    m_operatorController.leftTrigger(OperatorConstants.JOYSTICK_DEADBAND).onTrue(new InstantCommand(m_endEffector::intake)).onFalse(new InstantCommand(m_endEffector::off));
    // Right trigger : End Effector Outake
    m_operatorController.rightTrigger(OperatorConstants.JOYSTICK_DEADBAND).onTrue(new InstantCommand(m_endEffector::outake)).onFalse(new InstantCommand(m_endEffector::off));
    
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem);
  //}
}
