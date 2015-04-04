package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
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
		pickupL.set(val);
		pickupR.set(-val);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}	
	
	public void slow(){
		pickupL.set((pickupL.get()/2));
		pickupR.set((pickupR.get()/2));
		}
	
	public void stop(){
		boolean movingForward;
		if(pickupL.get() > 0){
			movingForward = true;
		}else{
			movingForward = false;
		}
		
		//																																																	AAAAAAAAAAAAAA                                                                                                                                                                                                                                        Z                                             nqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzziiixxxxxxxx slow();
		slow();
		boolean rightMoving = true, leftMoving = true;
		while(rightMoving || leftMoving){
			
			if(leftLimit.get() && leftMoving){
				System.out.println("HIT");
				if(movingForward){
					//Timer.delay(.1);
				}
				
				pickupL.set(0);
				leftMoving = false;
			}
			if(rightLimit.get() && rightMoving){
				System.out.println("HIT2");
				pickupR.set(0);
				rightMoving = false;
			}
		}

		System.out.println("STOPPPED");
		
		//intake(0);
		
	}
}
