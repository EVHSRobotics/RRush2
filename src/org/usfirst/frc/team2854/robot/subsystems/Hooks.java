package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hooks extends Subsystem{
	public static class HooksConfig{
		public static final double UPSPEED =  1;
		public static final double DOWNSPEED = -1;
	}
	private Victor hookMotor;
	
	public Hooks() {
		hookMotor = new Victor(RobotMap.aHook.aVictor.A);
	}
	
	public void move(double val){
		hookMotor.set(val);
	}
	
	public void initDefaultCommand() {
		
	}
}
