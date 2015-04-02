package org.usfirst.frc.team2854.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	public static class aElevation {
		public static class aTalon {
			public static final int A = 2;
			public static final int B = 3;
		}

		public static class aSensor {
			public static class aEncoder {
				public static class A {
					public static class Channel {
						public static final int A = 0;
						public static final int B = 1;
					}
				}
			}

			public static class Limit {
				public static final int BOT = 8;
				public static final int TOP = 9;
			}
		}
	}

	public static class aDrive {
		public static class aTalon {
			public static final int LF = 0;
			public static final int LB = 1;
			public static final int RF = 5;
			public static final int RB = 4;
		}

		public static class aSensor {
			public static class aEncoder {
				public static class L {
					public static class Channel {
						public static final int A = 4;
						public static final int B = 5;
					}
				}

				public static class R {
					public static class Channel {
						public static final int A = 6;
						public static final int B = 7;
					}
				}
			}
		}
	}
	
	public static class aPickUp {
		public static class aTalon {
			public static final int L = 6;
			public static final int R = 7;
		}
	}
}
