package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	public static class DriveConfig {
		public static final double TURN90 = 1024;
		
		public static final double TURN_SPEED = 0.5;
		public static final double FORWARD_SPEED = 0.7;
		public static final double DRIVE_TUNING = .7;
		public static final double DRIVE_TOLERANCE = 50;
	}
	
	private Talon driveTalonLF;
	private Talon driveTalonLB;
	private Talon driveTalonRF;
	private Talon driveTalonRB;

	private Encoder driveEncoderL;
	private Encoder driveEncoderR;

	public DriveTrain() {
		driveTalonLF = new Talon(RobotMap.aDrive.aTalon.LF);
		driveTalonLB = new Talon(RobotMap.aDrive.aTalon.LB);
		driveTalonRF = new Talon(RobotMap.aDrive.aTalon.RF);
		driveTalonRB = new Talon(RobotMap.aDrive.aTalon.RB);

		
//		driveEncoderL = new Encoder(
//				RobotMap.aDrive.aSensor.aEncoder.L.Channel.A,
//				RobotMap.aDrive.aSensor.aEncoder.L.Channel.B, true,
//				EncodingType.k4X);
//		driveEncoderR = new Encoder(
//				RobotMap.aDrive.aSensor.aEncoder.R.Channel.A,
//				RobotMap.aDrive.aSensor.aEncoder.R.Channel.B, true,
//				EncodingType.k4X);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void tankDrive(double left, double right) {
		//System.out.println("L:"+left+" R:"+right);
		left*= DriveConfig.DRIVE_TUNING;
		right*= DriveConfig.DRIVE_TUNING;
		driveTalonLF.set(-left);
		driveTalonRF.set(right);
		driveTalonLB.set(-left);
		driveTalonRB.set(right);
	}
	
	public void resetEncoderR() {
		System.out.println("ENCODER R RESET");
		driveEncoderR.reset();
	}
	
	public void resetEncoderL() {
		System.out.println("ENCODER L RESET");
		driveEncoderL.reset();
	}
	
	public double getEncoderL() {
		System.out.println("DISTANCE L");
		return driveEncoderL.getDistance();
	}
	
	public double getEncoderR() {
		System.out.println("DISTANCE R");
		return driveEncoderR.getDistance();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
