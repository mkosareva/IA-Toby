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
		//Capteurs test1=new Capteurs();
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
		String couleur = null;
		int nb1,nb2,nb3;
		//modifie le capteur de couleur pour lui faire capter le taux de rouge, bleu et vert présent dans la couleur détecté
		color.getRGBMode();
		color.setFloodlight(Color.WHITE);

		//initialise une couleur appelée "RGB"
		Color rgb;
		/*Le programme tournera jusqu'à ce qu'on appuye sur le bouton "echap" en haut à gauche de la brique.
		 * ou qu'il capte du blanc */
		while (Button.ESCAPE.isUp() || couleur != "blanc" ){
			test.avancer(150, 100);
			rgb = ((ColorDetector)color).getColor();
			nb1=rgb.getRed();
			nb2=rgb.getGreen();// 34
			nb3=rgb.getBlue(); //17
			if((38<=nb1 && nb1<=42) && (32<=nb2 && nb2<=36) && (15<=nb3 && nb3<=19)) {
				couleur="blanc";
			}
		}
		//test.orienter(90); //=> ok
		//test.avancer(100, 100); //=> ok 
		//test.reculer(100, 100); //=> ok 
		//test.fermerPinces();
		//System.out.println("salut"); 
	}
}
