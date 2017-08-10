package examples;

import ev3dev.actuators.lego.motors.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

public class PilotConfig {

	DifferentialPilot pilot = null;

	public PilotConfig() {

		final double wheelDiameter = 8.2d;
		final double trackWidth = 13d;
		RegulatedMotor leftMotor = Motor.A;
		RegulatedMotor rightMotor = Motor.D;

		//Special Stop modes from EV3Dev
		leftMotor.brake();
		rightMotor.brake();

		pilot = new DifferentialPilot(
				wheelDiameter,
				trackWidth,
				leftMotor,
				rightMotor);

		//pilot.setAngularAcceleration();
		pilot.setAngularSpeed(100);
		//pilot.setLinearAcceleration();
		pilot.setLinearSpeed(100);
	}

	public DifferentialPilot getPilot(){
		return pilot;
	}

}