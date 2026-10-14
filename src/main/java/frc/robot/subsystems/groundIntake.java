package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.ctre.phoenix6.hardware.CANrange;
import com.ctre.phoenix6.configs.CANrangeConfiguration;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class groundIntake extends SubsystemBase {
    private final TalonFX intakeMotor;
    private final TalonFXConfiguration intakeMotorConfig;
    private final TalonFX rotateMotor;
    private final TalonFXConfiguration rotateMotorConfig;

    private final DutyCycleEncoder rotatorMotorEncoder;
    private final CANrange range;

    private int Carrots;

    public groundIntake() {
        intakeMotor = new TalonFX(Constants.OperatorConstants.INTAKE_MOTOR_ID);
        intakeMotorConfig = new TalonFXConfiguration();
        intakeMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        intakeMotor.getConfigurator().apply(intakeMotorConfig);
        rotateMotor = new TalonFX(Constants.OperatorConstants.ROTATE_MOTOR_ID);
        rotateMotorConfig = new TalonFXConfiguration();
        rotateMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        rotateMotor.getConfigurator().apply(rotateMotorConfig);

        rotatorMotorEncoder = new DutyCycleEncoder(Constants.OperatorConstants.ROTATOR_MOTOR_ENCODER_ID);

        range = new CANrange(Constants.OperatorConstants.CANRANGE_ID);

        CANrangeConfiguration CANrangeConfig = new CANrangeConfiguration();
        range.getConfigurator().apply(CANrangeConfig);
       
    }

    public double GetPosition(){
        return rotatorMotorEncoder.get();
    }

    public void PivotUp(){
        if(GetPosition() < 150){
            rotateMotor.set(0.3);
        }
        else{
            rotateMotor.set(0.0);
        }
    }

    public void PivotDown(){
        if(GetPosition() > 0){
            rotateMotor.set(-0.3);
        }
        else{
            rotateMotor.set(0.0);
        }
    }

    public void Intake(){
        if(Carrots < 3){
            intakeMotor.set(0.2);
        }
    }

    public void Off(){
        intakeMotor.set(0.0);
        rotateMotor.set(0.0);
    }

    public void GoToOven(){
        while(GetPosition() > 15 ){
            rotateMotor.set(-0.2);
            if(GetPosition() < 15.1 && GetPosition() > 14.9){
                break;
            }
        }
        while(GetPosition() <15){
            rotateMotor.set(0.2);
            if(GetPosition() < 15.1 && GetPosition() > 14.9){
                break;
            }
        }
    }

    public void CountCarrots(){
        if(range.getDistance().getValueAsDouble() < 1){
            Carrots++;
        }
        if(Carrots >= 3){
            intakeMotor.set(0.0);
        }
    }
}