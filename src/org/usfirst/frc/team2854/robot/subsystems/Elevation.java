package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevation extends PIDSubsystem {
	public static class ElevationConfig {
		public static class Setpoint {
			public static final int BOT = 0;
			public static final int TOP = 3050;
		}

		public static final int TOLERANCE = 50;
		
		public static class ButtonMove {
			public static final double UP = 0.5;
			public static final double DOWN = -0.5;
		}
	}

	private Talon elevationTalon1;
	private Talon elevationTalon2;

	private DigitalInput botLimit;
	private DigitalInput topLimit;

	private Encoder elevationEncoder;

	// Initialize your subsystem here
	public Elevation() {
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
		super(0.001, 0.001, 0.0);

		elevationTalon1 = new Talon(RobotMap.aElevation.aTalon.A);
		elevationTalon2 = new Talon(RobotMap.aElevation.aTalon.B);

		botLimit = new DigitalInput(RobotMap.aElevation.aSensor.Limit.BOT);
		topLimit = new DigitalInput(RobotMap.aElevation.aSensor.Limit.TOP);

		elevationEncoder = new Encoder(
				RobotMap.aElevation.aSensor.Encoder.A.Channel.A,
				RobotMap.aElevation.aSensor.Encoder.A.Channel.B, true,
				EncodingType.k4X);

		setAbsoluteTolerance(ElevationConfig.TOLERANCE);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return elevationEncoder.getDistance();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		move(output);
	}

	public void resetEncoder() {
		elevationEncoder.reset();
	}

	public void manualMove(double val) {
		move(val);
	}

	private void move(double val) {
		if (botLimit.get() && val < 0 || topLimit.get() && val > 0) {
			elevationTalon1.set(0);
			elevationTalon2.set(0);
		} else {
			elevationTalon1.set(val);
			elevationTalon2.set(val);
		}
	}

	public void disablePID() {
		disable();
	}

	public void enablePID() {
		enable();
	}
}
