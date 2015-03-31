package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveMove extends Command {

	private int joystickId;
	private int axisIdL;
	private int axisIdR;

	public DriveMove(int aJoystickId, int aAxisIdL, int aAxisIdR) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		joystickId = aJoystickId;
		axisIdL = aAxisIdL;
		axisIdR = aAxisIdR;

		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drive.tankDrive(Robot.oi.getAxis(joystickId, axisIdL), Robot.oi.getAxis(joystickId, axisIdR));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.oi.getAxis(joystickId, axisIdL) == 0
				&& Robot.oi.getAxis(joystickId, axisIdR) == 0;
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
