package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain.DriveConfig;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveBackwardTime extends Command {
		double time;
    	public DriveBackwardTime(double aTime) {
    		// Use requires() here to declare subsystem dependencies
    		// eg. requires(chassis);
    		time = aTime;

    		requires(Robot.drive);
    	}

    	// Called just before this Command runs the first time
    	protected void initialize() {
    		Robot.drive.tankDrive(DriveConfig.FORWARD_SPEED,
    				DriveConfig.FORWARD_SPEED);
    		Timer.delay(time);
    	}

    	// Called repeatedly when this Command is scheduled to run
    	protected void execute() {
    	}

    	// Make this return true when this Command no longer needs to run execute()
    	protected boolean isFinished() {
    		return true;
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
