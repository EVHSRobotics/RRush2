package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain.DriveConfig;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurn extends Command {
	private boolean isLeft;

	public DriveTurn(boolean aIsLeft) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		isLeft = aIsLeft;

		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drive.resetEncoderL();
		Robot.drive.resetEncoderR();
		if (isLeft) {
			Robot.drive.tankDrive(0, DriveConfig.TURN_SPEED);
		} else {
			Robot.drive.tankDrive(DriveConfig.TURN_SPEED, 0);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (isLeft) {
			return DriveConfig.TURN90 - Robot.drive.getEncoderL() > DriveConfig.DRIVE_TOLERANCE;
		} else {
			return DriveConfig.TURN90 - Robot.drive.getEncoderR() > DriveConfig.DRIVE_TOLERANCE;
		}
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
