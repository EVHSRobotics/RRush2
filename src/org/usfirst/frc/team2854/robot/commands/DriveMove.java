package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class DriveMove extends Command {

	private int joystickId;
	private int axisIdL;
	private int axisIdR;
	private int axisLT;
	private int axisRT;

	public DriveMove(int aJoystickId, int aAxisIdL, int aAxisIdR, int aAxisLT, int aAxisRT) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		joystickId = aJoystickId;
		axisIdL = aAxisIdL;
		axisIdR = aAxisIdR;	
		axisLT = aAxisLT;
		axisRT = aAxisRT;
		
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double leftVal = Robot.oi.getAxis(joystickId, axisIdL);
		double rightVal = Robot.oi.getAxis(joystickId, axisIdR);
		double leftTwist = OI.fixDeadBand(Robot.oi.getAxis(joystickId, axisLT));
		double rightTwist = OI.fixDeadBand(Robot.oi.getAxis(joystickId, axisRT));
		if(Math.abs(leftTwist) > 0){
			leftVal = leftTwist;
			rightVal = -leftTwist;
		}else if(Math.abs(rightTwist) > 0){
			leftVal = -rightTwist;
			rightVal = rightTwist;
		}
		/*
		if((Robot.scaling.getCurrentCommand() == null || !Robot.scaling.getCurrentCommand().getName().equals("ScaleDriving") ) && (Math.abs(leftVal)>0 || Math.abs(rightVal) >0)){
			System.out.println("SCALE DRIVING ADDED");
			if(Robot.drive.scalingEnabled == true){

				Scheduler.getInstance().add(new ScaleDriving());
			}

			System.out.println(Robot.scaling.getCurrentCommand().getName());
		}else if(leftVal == 0 && rightVal == 0){
			if(Robot.drive.scalingEnabled == true){
				Scheduler.getInstance().add(new ResetScaling());
			}
		}*/
		
		Robot.drive.tankDrive(OI.fixDeadBand(leftVal), OI.fixDeadBand(rightVal));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.tankDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drive.tankDrive(0, 0);
	}
}
