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
import frc.robot.subsystems.groundIntake;

// Start the main code
//Needs 2 motors
public class elevator extends SubsystemBase{
    //define the elevator motor
    private final TalonFX elevatorMotor;
    private final TalonFX elevatorMotor2;
    private final TalonFXConfiguration elevatorConfig;
    private Boolean isSafeGoDown;
  
    //define the elveator encoder
    private final DutyCycleEncoder elevatorEncoder;
    private final groundIntake groundIntake;

    public elevator() {
        //initialize the elevator motor and configurator
        elevatorMotor = new TalonFX(Constants.OperatorConstants.ELEVATOR_MOTOR_ID);
        elevatorMotor2 = new TalonFX(Constants.OperatorConstants.ELEVATOR_MOTOR_ID2);
        elevatorConfig = new TalonFXConfiguration();
        elevatorMotor.getConfigurator().apply(elevatorConfig);
        isSafeGoDown = false;
        groundIntake = new groundIntake();
        
        //initialize the elevatorencoder
        elevatorEncoder = new DutyCycleEncoder(Constants.OperatorConstants.ELEVATOR_ENCODER_ID);
    }

        // When we call go up:
        public void goUp() {
            // Set both elevator motors to a positive speed
            elevatorMotor.set(0.1);
            elevatorMotor2.set(0.1);
        }

        // When we call go down:
        public void goDown() {
            // Set both elevator motors to a negative speed
            elevatorMotor.set(-0.1);
            elevatorMotor2.set(-0.1);
        }

        // When we call stop:
        public void stop() {
            // Set both elevator motors to zero speed
            elevatorMotor.set(0);
            elevatorMotor2.set(0);
        }

        // Get the current position of the elevator
        public double getElevatorPosition() {
            return elevatorEncoder.get();
        }

        // Make a function called isSafe() and let it return a boolean
        public boolean isSafe() {
            // Picture the position is like a circle with 0 at the top and 150 on the left
            // The 0/top of that circle in the MAX position
            // The 150/Bottom of that circle is the MIN position
            // If the pivot is less than the safe position, it is safe to go down so set isSafe to true
            if (groundIntake.GetPosition() < Constants.OperatorConstants.PIVOT_SAFE_POSITION) {
                isSafeGoDown = true;
            } else {
                // If the pivot is above the safe position, it is unsafe so set isSafe to false
                isSafeGoDown = false;
            }
            return isSafeGoDown;
        }

        // make a function called levelIntake...
        public void levelIntake(){
            // if the pivot angle is within a safe range for the elevator to go down:
            if (isSafe()) {
                // If the ground intake is above or equal to the threshold
                if(elevatorEncoder.get() >= Constants.OperatorConstants.ELEVATOR_THRESHOLD) {
                    goDown();
                } else if(elevatorEncoder.get() < Constants.OperatorConstants.ELEVATOR_THRESHOLD) {
                    stop();
                }
            }

        }






        // Make a function called level1...
        public void level1() {
            // If the pivot is in the safe range then carry out the rest of the function
            if (isSafe()) {
                // If the elevators position is less than/lower than our set poistion in constants
                if (getElevatorPosition() < (Constants.OperatorConstants.LEVEL_1_POSITION)) {
                    // Call our previous function 
                    goUp();
                // If the elevators position is greater than/higher than our set poistion in constants
                } else if (getElevatorPosition() > (Constants.OperatorConstants.LEVEL_1_POSITION)) {
                    // Call our previous function
                    goDown();
                // If the position is neither higher nor lower it is at the correct position so...
                } else {
                    // Dont move
                    stop();
                }
            }
        }

        // Move the elevator to level 2
        public void level2() {
            if (isSafe()) {
                // If the elevators position is less than/lower than our set poistion in constants
                if (getElevatorPosition() < (Constants.OperatorConstants.LEVEL_2_POSITION)) {
                    // Call our previous function
                    goUp();
                // If the elevators position is greater than/higher than our set poistion in constants
                } else if (getElevatorPosition() > (Constants.OperatorConstants.LEVEL_2_POSITION)) {
                    // Call our previous function
                    goDown();
                // If the position is neither higher nor lower it is at the correct position so...
                } else {
                    // Dont move
                    stop();
                }
            }
        }

        // Move the elevator to level 3
        public void level3() {
            if (isSafe()) {
                // If the elevators position is less than/lower than our set poistion in constants
                if (getElevatorPosition() < (Constants.OperatorConstants.LEVEL_3_POSITION)) {
                    // Call our previous function
                    goUp();
                // If the elevators position is greater than/higher than our set poistion in constants
                } else if (getElevatorPosition() > (Constants.OperatorConstants.LEVEL_3_POSITION)) {
                    // Call our previous function
                    goDown();
                // If the position is neither higher nor lower it is at the correct position so...
                } else {
                    // Dont move
                    stop();
                }
            }
        }
    }
