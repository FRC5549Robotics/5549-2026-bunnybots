// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    //Driver Constants
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;



    //Elevator Constants
    public static final int ELEVATOR_MOTOR_ID = 0;  
    public static final int ELEVATOR_MOTOR_ID2 = 0;  
    public static final int LEVEL_1_POSITION = 0;
    public static final int LEVEL_2_POSITION = 0;
    public static final int LEVEL_3_POSITION = 0;
    public static final int ELEVATOR_ENCODER_ID = 0;
    public static final double ELEVATOR_THRESHOLD = 0.0;
    
    //Ground Intake Constants
    public static final int PIVOT_ENCODER_ID = 0;
    public static final int PIVOT_MOTOR_ID = 0;
    public static final double PIVOT_SAFE_POSITION = 0.0;
    public static final int INTAKE_MOTOR_ID = 0;
    public static final int CANRANGE_ID = 0;
    public static final int ROTATE_MOTOR_ID = 0;
    public static final int ROTATOR_MOTOR_ENCODER_ID = 0;

    //End Effector Constants
    public static final int END_EFFECTOR_MOTOR_ID = 0;
    

  }
}
