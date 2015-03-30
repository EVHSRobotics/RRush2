package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.Elevation;
import org.usfirst.frc.team2854.robot.subsystems.Elevation.ElevationConfig;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevationMove extends Command {
	private int joystickId;
	private boolean isAxis;
	
	private int axisId;
	
	private int buttonUpId;
	private int buttonDownId;

    public ElevationMove(int aJoystickId, int aAxisId) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	joystickId = aJoystickId;
    	axisId = aAxisId;
    	isAxis = true;
    	
    	requires(Robot.elevator);
    }
    
    public ElevationMove(int aJoystickId, int aButtonUpId, int aButtonDownId) {
    	joystickId = aJoystickId;
    	buttonUpId = aButtonUpId;
    	buttonDownId = aButtonDownId;
    	isAxis = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isAxis) {
    		Robot.elevator.manualMove(Robot.oi.getAxis(joystickId, axisId));
    	} else {
    		if(Robot.oi.getButton(joystickId, buttonUpId)){
    			Robot.elevator.manualMove(ElevationConfig.ButtonMove.UP);
    		} else if(Robot.oi.getButton(joystickId, buttonDownId)) {
    			Robot.elevator.manualMove(ElevationConfig.ButtonMove.DOWN);
    		}
    	}    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(isAxis){
    		return Robot.oi.getAxis(joystickId, axisId) == 0;	
    	} else {
    		return !Robot.oi.getButton(joystickId, buttonUpId) && !Robot.oi.getButton(joystickId, buttonDownId);
    	}
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.manualMove(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.manualMove(0);
    }
}
