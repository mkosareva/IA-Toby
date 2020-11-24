package ia;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Banane {
	
	public static void main(String []a){
		Actions test=new Actions (MotorPort.A, MotorPort.B, MotorPort.C);
		Capteurs capteur=new Capteurs();
		//Capteurs test1=new Capteurs();
		
		//test.orienter(90); //=> ok
		//test.avancer(100, 100); //=> ok 
		//test.reculer(100, 100); //=> ok 
		//test.fermerPinces();
		//test.ouvrirPinces();
		//System.out.println("salut");
		test.get2PremierPaletPourUnAvantageDeFou();
		/*if(!capteur.isPressed()) {
			test.avancer(400,1000);
		}
		*/
		//test.avancer(400, 1000);
		//test.orienter(90);
		//test.avancer(400, 2000);
	}
}
