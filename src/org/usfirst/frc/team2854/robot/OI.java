package org.usfirst.frc.team2854.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

import org.usfirst.frc.team2854.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static class OIConfig {
		public static final double DEADBAND = .15;
	}

	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public Joystick controller1;
	public Joystick controller2;

	public OI() {
		controller1 = new Joystick(0);
		controller2 = new Joystick(1);
	}

	public static class OIMap {
		public static class Axis {
			public static final int LX = 0; // axis numbers zero based, 2013 has
											// 1 based
			public static final int LY = 1;
			public static final int RX = 4;
			public static final int RY = 5;
			public static final int LT = 2;
			public static final int RT = 3;
		}

		public static class Button {
			public static final int A = 1;
			public static final int B = 2;
			public static final int X = 3;
			public static final int Y = 4;
			public static final int LB = 5;
			public static final int RB = 6;
			public static final int BACK = 7;
			public static final int START = 8;
		}
		
		public static class JoystickId {
			public static final int JOY1 = 0;
			public static final int JOY2 = 1;
		}
		
		public static class InputType {
			public static final int AXIS = 1;
			public static final int BUTTON = 2;
		}
	}
	
	public Joystick getJoystick(int joystickId) {
		switch (joystickId) {
		case OIMap.JoystickId.JOY1:
			return controller1;
		case OIMap.JoystickId.JOY2:
			return controller2;
		default:
			return controller1;
		}
	}

	public boolean getButton(int joyid, int b) {
		return getJoystick(joyid).getRawButton(b);
	}

	public double getAxis(int joyid, int a) {
		//System.out.println("JOY VAL:"+getJoystick(joyid).getRawAxis(a));
		return fixDeadBand(getJoystick(joyid).getRawAxis(a));
	}
	
	public boolean isAxisInput(int inputType) {
		return inputType == OIMap.InputType.AXIS;
	} 
	public boolean isButtonInput(int inputType) {
		return inputType == OIMap.InputType.BUTTON;
	}
	
	// 2013 numbers (may have changed)
	// Axis indexes:
	// 1 - LeftX
	// 2 - LeftY
	// 3 - Triggers (Each trigger = 0 to 1, axis value = right - left)
	// 4 - RightX
	// 5 - RightY
	// 6 - DPad Left/Right
	// double axisValue = mXboxController.getRawAxis(2); // Where "2" is the
	// index of the Y axis on the left stick (see above)

	// Button indexes:
	// 1 A
	// 2 B
	// 3 X
	// 4 Y
	// 5 LB
	// 6 RB
	// 7 back
	// 8 start
	// 9 left stick press
	// 10 right stick press
	//
	// Move Robot
	// X-Movement - Axis 1
	// Y-Movement - Axis 2
	// Twist - Axis 3
	//
	// Shooter & Arm
	// Trigger - RB (6)
	//
	// Move Arm
	// Picking up - A (1)
	// Passing - B (2)
	// Shooting - X (3)
	// 2nd angle shooting? - Y (4)
	//
	// Safety
	// Reset gyro (or disable) - Back
	// Start Position - Start

	public static double fixDeadBand(double val) {
		return (Math.abs(val) > OIConfig.DEADBAND ? val : 0.0);
	}
}
