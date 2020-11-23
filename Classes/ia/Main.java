package ia;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Main {
	
	public static void main(String []a){
		Actions test=new Actions (MotorPort.A, MotorPort.B, MotorPort.C);
		//test.orienter(90); //=> ok
		//test.avancer(100, 100); //=> ok 
		//test.reculer(100, 100); //=> ok 
		//test.fermerPinces();

	}
}
