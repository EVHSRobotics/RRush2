package org.usfirst.frc.team2854.robot;

import org.usfirst.frc.team2854.robot.OI.OIMap;
import org.usfirst.frc.team2854.robot.commands.DoNothing;
import org.usfirst.frc.team2854.robot.commands.DriveForward;
import org.usfirst.frc.team2854.robot.commands.DriveMove;
import org.usfirst.frc.team2854.robot.commands.ElevationMove;
import org.usfirst.frc.team2854.robot.commands.ElevationMoveTo;
import org.usfirst.frc.team2854.robot.commands.FullAuto;
import org.usfirst.frc.team2854.robot.commands.Intake;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2854.robot.subsystems.Elevation;
import org.usfirst.frc.team2854.robot.subsystems.Elevation.ElevationConfig;
import org.usfirst.frc.team2854.robot.subsystems.PickUp;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// public static final ExampleSubsystem exampleSubsystem = new
	// ExampleSubsystem();

	public static final Elevation elevator = new Elevation();
	public static final DriveTrain drive = new DriveTrain();
	public static final PickUp pickup = new PickUp();
	
	//AND THIS
	CameraServer server;

	SendableChooser autoChooser;

	public static OI oi;

	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		
		//MAKE SURE TO ADD THIS
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
        
		// instantiate the command used for the autonomous period
		// autonomousCommand = new ExampleCommand();
		dashboard();
		System.out.println("HELLO");
	}

	private void dashboard() {
		// TODO Auto-generated method stub
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Do Nothing", new DoNothing());
		autoChooser.addObject("Forward", new DriveForward(4096));
		autoChooser.addObject("Full Auto", new FullAuto());
		SmartDashboard.putData("Autonomous Mode Choooser", autoChooser);
		SmartDashboard.putNumber("PID",0);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
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
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		System.out.println("AJLFD");
		// elevation
		System.out.println("R:"+elevator.getCurrentCommand().getName());
		if (Robot.oi.getButton(OIMap.JoystickId.JOY2, OIMap.Button.LB)) {
			System.out.println("1");
			Scheduler.getInstance().add(
					new ElevationMoveTo(ElevationConfig.Setpoint.BOT));
		} else if (Robot.oi.getButton(OIMap.JoystickId.JOY2, OIMap.Button.RB)) {
			System.out.println("2");
			Scheduler.getInstance().add(
					new ElevationMoveTo(ElevationConfig.Setpoint.TOP));
		} else if (!elevator.getCurrentCommand().getName()
				.equals("ElevationMove")) {
			System.out.println("3");
			Scheduler.getInstance().add(
					new ElevationMove(OIMap.JoystickId.JOY2, OIMap.Axis.LY));
		}
		System.out.println("BCDSF");
		// drive
		if (!drive.getCurrentCommand().getName().equals("DriveMove")) {
			System.out.println("4");
			Scheduler.getInstance().add(
					new DriveMove(OIMap.JoystickId.JOY1, OIMap.Axis.LY,
							OIMap.Axis.RY));
		}
		// pickup
		if (!pickup.getCurrentCommand().getName().equals("Intake")) {
			System.out.println("5");
			Scheduler.getInstance().add(new Intake(OIMap.JoystickId.JOY1, OIMap.Button.RB, OIMap.Button.LB));
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
