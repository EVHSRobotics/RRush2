package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevationMoveTo extends Command {

	private double setpoint;
	
    public ElevationMoveTo(double aSetpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	setpoint = aSetpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.setSetpoint(setpoint);
    	Robot.elevator.enablePID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.onTarget() || Robot.elevator.botLimit.get() || Robot.elevator.topLimit.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.disablePID();
    	Robot.elevator.manualMove(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.disablePID();
    	Robot.elevator.manualMove(0);
    }
}
