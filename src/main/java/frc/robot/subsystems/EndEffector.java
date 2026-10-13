package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class EndEffector extends SubsystemBase {
    // Define the end effector motor and configurator
    private final TalonFX endEffectorMotor;
    private final TalonFXConfiguration endEffectorConfig;

    public EndEffector() {
        // Initialize the end effector motor and configurator
        endEffectorMotor = new TalonFX(Constants.END_EFFECTOR_MOTOR_ID);
        endEffectorConfig = new TalonFXConfiguration();
        //applys the configurration 
        endEffectorMotor.getConfigurator().apply(endEffectorConfig);
    }
}