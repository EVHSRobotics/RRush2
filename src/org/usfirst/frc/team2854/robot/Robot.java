
package org.usfirst.frc.team2854.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2854.robot.OI.OIMap;
import org.usfirst.frc.team2854.robot.commands.ElevationMove;
import org.usfirst.frc.team2854.robot.commands.ElevationMoveTo;
import org.usfirst.frc.team2854.robot.commands.ExampleCommand;
import org.usfirst.frc.team2854.robot.subsystems.Elevation;
import org.usfirst.frc.team2854.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team2854.robot.subsystems.Elevation.ElevationConfig;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	
	public static final Elevation elevator = new Elevation();
	
	public static OI oi;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new ExampleCommand();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	if(Robot.oi.getButton(OIMap.JoystickId.JOY2, OIMap.Button.LB)){
    		Scheduler.getInstance().add(new ElevationMoveTo(ElevationConfig.Setpoint.BOT));
    	}
    	if(Robot.oi.getButton(OIMap.JoystickId.JOY2, OIMap.Button.RB)){
    		Scheduler.getInstance().add(new ElevationMoveTo(ElevationConfig.Setpoint.TOP));
    	}
    	if(Robot.oi.getAxis(OIMap.JoystickId.JOY2, OIMap.Axis.LY) != 0){
    		Scheduler.getInstance().add(new ElevationMove(OIMap.JoystickId.JOY2, OIMap.Axis.LY));
    	}
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
