package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.Elevation.ElevationConfig;
import org.usfirst.frc.team2854.robot.subsystems.PickUp.PickUpConfig;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Intake extends Command {
	private int joystickId;
	private int buttonInId;
	private int buttonOutId;
	private int currentButton;

	public Intake(int aJoystickId, int aButtonInId, int aButtonOutId) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		joystickId = aJoystickId;
		buttonInId = aButtonInId;
		buttonOutId = aButtonOutId;

		requires(Robot.pickup);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.getButton(joystickId, buttonInId)) {
			Robot.pickup.intake(PickUpConfig.SPEED_IN);
			currentButton = buttonInId;
		} else if (Robot.oi.getButton(joystickId, buttonOutId)) {
			Robot.pickup.intake(PickUpConfig.SPEED_OUT);
			currentButton = buttonOutId;
		}else{
			Robot.pickup.stop();
			currentButton = 0;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (currentButton == 0) || !Robot.oi.getButton(joystickId,  currentButton);
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("finishing");
		Robot.pickup.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.pickup.stop();
	}
}
