
package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//mport edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
  //private static final String kDefaultAuto = "Default";
  //private static final String kCustomAuto = "My Auto";
  //private String m_autoSelected;
  //private final SendableChooser<String> m_chooser = new SendableChooser<>();
  Joystick driverController;
  CANSparkMax leftMotor1, leftMotor2, rightMotor1, rightMotor2;
  DifferentialDrive drive;
  AutonomousDriveCommands autoDrive;

  @Override
  public void robotInit() {
    //m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    //m_chooser.addOption("My Auto", kCustomAuto);
    //SmartDashboard.putData("Auto choices", m_chooser);
    driverController= new Joystick(Constants.DRIVER_CONTROLLER_PORT);
    leftMotor1 = new CANSparkMax(Constants.L_MOTOR_1_ID, MotorType.kBrushless);
    leftMotor2 = new CANSparkMax(Constants.L_MOTOR_2_ID, MotorType.kBrushless);
    rightMotor1 = new CANSparkMax(Constants.R_MOTOR_1_ID, MotorType.kBrushless);
    rightMotor2 = new CANSparkMax(Constants.R_MOTOR_2_ID, MotorType.kBrushless);
    autoDrive = new AutonomousDriveCommands(leftMotor1, rightMotor1);

    leftMotor2.follow(leftMotor1);
    leftMotor1.setInverted(false);
    leftMotor2.setInverted(false);
    rightMotor1.setInverted(false);
    rightMotor2.setInverted(false);
    rightMotor2.follow(rightMotor1);

    drive = new DifferentialDrive(leftMotor1, rightMotor1);
    drive.setDeadband(0.05);
    }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    //AutonomousDriveCommands autoDrive = new AutonomousDriveCommands(leftMotor1, rightMotor1);
    autoDrive.Drive(5*Constants.FEET_TO_ENCODER_COUNT);
    //AutoDrive autonomousDrive = new AutoDrive(LeftMotor1, RightMotor1, )
    
    //autonomousDrive.Drive(5);
    //autonomousDrive.Turn("L");

  
  }

  
  @Override
  public void autonomousPeriodic() {
  
    }

   @Override
  public void teleopPeriodic() {
    
    double speed = -1*Math.pow(driverController.getY(), 3)*Constants.MAX_OUTPUT ;
    double turn = driverController.getX()*Constants.MAX_OUTPUT*Constants.TURN_SPEED_REDUCTION;
    drive.curvatureDrive(speed, turn, true);

    autoDrive.PrintEncoderValues();
    if (driverController.getRawButtonPressed(1)){
      autoDrive.ResetEncoders();
    }
    

    //autoDrive.Drive(5*Constants.FEET_TO_ENCODER_COUNT);
  }

  @Override
  public void testPeriodic() {
    autoDrive.Drive(5*Constants.FEET_TO_ENCODER_COUNT);



    //autoDrive.Drive(20*Constants.FEET_TO_ENCODER_COUNT);
  }
}
