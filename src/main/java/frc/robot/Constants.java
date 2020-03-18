/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public final class Constants {
     //CANBUS PINS Based on REV Client # Designation or Pheonix Tuner App
	public static final int L_MOTOR_1_ID = 1;
	public static final int L_MOTOR_2_ID = 2;
	public static final int R_MOTOR_1_ID = 3;
    public static final int R_MOTOR_2_ID = 4;
    
    //Driver's Controller Constants
    public static final int DRIVER_CONTROLLER_PORT = 0;
	public static final double TURN_SPEED_REDUCTION = 0.9;
	public static final double MAX_OUTPUT = 0.3;

	//Feet to DriveBase Motor Rotations for encoder. 1 rotation of wheel = 10.72 rotations of motor (should be 10.75)
	public static final double FEET_TO_ENCODER_COUNT = 10.72*12/(12*Math.PI);
    


}
