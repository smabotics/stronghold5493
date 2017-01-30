

package org.usfirst.frc.team5493.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/*
 * Tank drive using Logitech Dual Action
 * and 4 drive motors connected through Victor 888 Controllers as follows:
 *     - PWM 2 - Connected to front left drive motor
 *     - PWM 3 - Connected to rear left drive motor
 *     - PWM 4 - Connected to front right drive motor
 *     - PWM 5 - Connected to rear right drive motor
 */

public class Robot extends SampleRobot{
	private static final int DRIVERIGHT = 3; //right joystick on logiTech
	private static final int DRIVELEFT = 1; //left joystick on logiTech
	// Create a robot drive object using PWMs 1, 3, 4, 10
	CANTalon driveLeftFront = new CANTalon(10);// PWM port 10
	CANTalon driveLeftRear = new CANTalon(4);// PWM port 4
	CANTalon driveRightFront = new CANTalon(1);// PWM port 1
	CANTalon driveRightRear = new CANTalon(3);// PWM port 3
	RobotDrive tank_drive = new RobotDrive(driveLeftFront, driveLeftRear, driveRightFront, driveRightRear);
	
	// Define xbox joystick being used at USB port 0 on the Driver Station
	Joystick logiTech = new Joystick(0);
	AnalogInput rSensor = new AnalogInput(1);
	AnalogInput lSensor = new AnalogInput(2);
	
	//Define Talons for launcher
	Talon l_launch = new Talon(0);
	Talon r_launch = new Talon(2);
		
	// Define compressor and doublesolenoid
	Compressor compressor = new Compressor(0);
	DoubleSolenoid doublesolenoid = new DoubleSolenoid(7,4);
	private DriverStation station = DriverStation.getInstance();
	
	//buttons on the joystick
	private final int forwardWheels = 6;
	private final int placeBoulder = 2;
	private final int angle = 1;
	private final int shootBoulder = 8;
	private final int backwardWheels = 5;
	
	
	
	public void autonomous(){
		while(true && isOperatorControl() && isEnabled()){
			launcher();
			break;
		}
	}
	
	private void launcher(){
		station = DriverStation.getInstance();
		compressor.setClosedLoopControl(true);
		for(int i = 0; i < 1; i++){
			//launch boulder with specific position, speed of wheels, and angle
			//need to find this when programming parts of the robot
		}
	}
	
	public void operatorControl(){
		while (true && isOperatorControl() && isEnabled()) {
			
			//initialize tankDrive (inversed)
			tank_drive.tankDrive(logiTech.getRawAxis(DRIVELEFT) * -1, logiTech.getRawAxis(DRIVERIGHT) * -1);
			
			//launching boulders
			if(logiTech.getRawButton(forwardWheels)){
				DriverStation.getInstance();
				DriverStation.reportError("Starting wheels", false);
				//start wheels .set(speed);
				Timer.delay(2.00); //calculate # of seconds it takes for the wheels to be at specified speed
			}
			if(logiTech.getRawButton(placeBoulder)){
				DriverStation.getInstance();
				DriverStation.reportError("Placing boulder", false);
				//boulder is in position to launch
			}
			if(logiTech.getRawButton(angle)){
				DriverStation.getInstance();
				DriverStation.reportError("Finding angle", false);
				//calculate precise angle from input of the angle motor
			}
			if(logiTech.getRawButton(shootBoulder)){
				//code to shoot boulder by releasing the place boulder
			}
			
			//boulder intake
			if(logiTech.getRawButton(backwardWheels)){
				//pick up boulder with the opposite code of forwardWheels
			}
		}
	}
}