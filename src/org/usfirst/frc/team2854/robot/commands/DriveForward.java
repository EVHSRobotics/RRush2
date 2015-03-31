package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain.DriveConfig;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {
	private double distance;

	public DriveForward(double aDistance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		distance = aDistance;

		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drive.resetEncoderL();
		Robot.drive.resetEncoderR();
		Robot.drive.tankDrive(DriveConfig.FORWARD_SPEED,
				DriveConfig.FORWARD_SPEED);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return distance - Robot.drive.getEncoderL() < DriveConfig.DRIVE_TOLERANCE
				|| distance - Robot.drive.getEncoderR() < DriveConfig.DRIVE_TOLERANCE;
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
