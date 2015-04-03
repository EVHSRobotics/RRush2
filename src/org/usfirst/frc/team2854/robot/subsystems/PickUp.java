package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PickUp extends Subsystem {
	public static class PickUpConfig {
		public static final double SPEED_IN = 1;
		public static final double SPEED_OUT = -1;
	}
	
	private Talon pickupL;
	private Talon pickupR;
	public DigitalInput leftLimit;
	public DigitalInput rightLimit;
	
	public PickUp() {
		pickupL = new Talon(RobotMap.aPickUp.aTalon.L);
		pickupR = new Talon(RobotMap.aPickUp.aTalon.R);
		
		leftLimit = new DigitalInput(2);
		rightLimit = new DigitalInput(3);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void intake(double val) {
		//System.out.println("INTAKE:"+val);
		pickupL.set(-val);
		pickupR.set(val);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}	
	
	public void stop(){
		/*intake(.5);
		if(leftLimit.get()){
			System.out.println("HIT");
			pickupL.set(0);
		}
		if(rightLimit.get()){
			System.out.println("HIT2");
			pickupR.set(0);
		}*/
		intake(0);
		
	}
}
