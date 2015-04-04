package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopIntake extends Command {
	boolean movingForward;
	boolean rightMoving = true, leftMoving = true;
    public StopIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		if(Robot.pickup.pickupL.get() > 0){
			movingForward = true;
		}else{
			movingForward = false;
		}
		Robot.pickup.slow();
		setTimeout(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if(Robot.pickup.leftLimit.get() && leftMoving){
			System.out.println("HIT");			
			Robot.pickup.pickupL.set(0);
			leftMoving = false;
		}
		if(Robot.pickup.rightLimit.get() && rightMoving){
			System.out.println("HIT2");
			Robot.pickup.pickupR.set(0);
			rightMoving = false;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (isTimedOut()) ||  (!leftMoving && !rightMoving);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pickup.intake(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pickup.intake(0);
    }
}
