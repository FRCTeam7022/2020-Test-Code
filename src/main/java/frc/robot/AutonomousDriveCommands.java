/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class AutonomousDriveCommands {
    private CANSparkMax leftMotors, rightMotors;
    private CANEncoder encoder_LeftMotors, encoder_RightMotors;
    private CANPIDController PID_LeftMotors, PID_RightMotors;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;
    int autonomous_movement, movement = 0;

    public AutonomousDriveCommands(CANSparkMax leftMotors, CANSparkMax rightMotors){
        this.leftMotors = leftMotors;
        this.rightMotors = rightMotors;

        encoder_LeftMotors = leftMotors.getEncoder();
        //encoder_LeftMotors.setInverted(true);
        encoder_RightMotors = rightMotors.getEncoder();

        PID_LeftMotors = leftMotors.getPIDController();
        PID_RightMotors = rightMotors.getPIDController();

          // PID coefficients
        kP = 5e-5; 
        kI = 1e-6;
        kD = 0; 
        kIz = 0; 
        kFF = 0.000156; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        maxRPM = 5700;

        // Smart Motion Coefficients
        maxVel = 2000; // rpm
        maxAcc = 1500;

        int smartMotionSlot = 0;

        PID_LeftMotors.setP(kP);
        PID_LeftMotors.setI(kI);
        PID_LeftMotors.setD(kD);
        PID_LeftMotors.setIZone(kIz);
        PID_LeftMotors.setFF(kFF);
        PID_LeftMotors.setOutputRange(kMinOutput, kMaxOutput);

        PID_LeftMotors.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
        PID_LeftMotors.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
        PID_LeftMotors.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
        PID_LeftMotors.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);

        PID_RightMotors.setP(kP);
        PID_RightMotors.setI(kI);
        PID_RightMotors.setD(kD);
        PID_RightMotors.setIZone(kIz);
        PID_RightMotors.setFF(kFF);
        PID_RightMotors.setOutputRange(kMinOutput, kMaxOutput);

        PID_RightMotors.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
        PID_RightMotors.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
        PID_RightMotors.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
        PID_RightMotors.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);
        }

    public void Drive(double counts){
        ResetEncoders();
        
        while(encoder_LeftMotors.getPosition()<=counts-0.1 || encoder_RightMotors.getPosition()>=-1*counts+0.1){
            PrintEncoderValues();
            //System.out.println("Left encoder: "+ encoder_LeftMotors.getPosition());
            //System.out.println("Right encoder: "+ encoder_RightMotors.getPosition());
            PID_RightMotors.setReference(-1*counts, ControlType.kSmartMotion);
            PID_LeftMotors.setReference(counts, ControlType.kSmartMotion);}
    }

    public void ResetEncoders(){
        encoder_RightMotors.setPosition(0);
        encoder_LeftMotors.setPosition(0);
    }

    public void PrintEncoderValues(){
        SmartDashboard.putNumber("Right Encoder: ", encoder_RightMotors.getPosition());
        SmartDashboard.putNumber("Left Encoder: ", encoder_LeftMotors.getPosition());
    }
}
