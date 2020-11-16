import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import lejos.hardware.Button;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;
import lejos.hardware.Button;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
/**
 * @author Utilisateur
 *
 */
public class Actions extends Capteurs{
	
	private Chassis chassis;
	private MovePilot mp;
	private EV3LargeRegulatedMotor moteurGauche;
	private EV3LargeRegulatedMotor moteurDroit;
	private EV3MediumRegulatedMotor moteurPince;

		
			private static EV3UltrasonicSensor port2;
			Wheel wheel1 = WheeledChassis.modelWheel(moteurGauche, 56).offset(-60);
			Wheel wheel2 = WheeledChassis.modelWheel(moteurDroit,  56).offset( 60);
			chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
			mp = new MovePilot(chassis);
			String AttRobot="Attention Robot !";
			String AttPalet="Attention Palet !";
			String AttMur="Attention Mur !";
			// create two motor objects to control the motors.
			UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
			UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
			UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);
			RegulatedMotor motorAr = new EV3LargeRegulatedMotor(MotorPort.A);
			RegulatedMotor motorBr = new EV3LargeRegulatedMotor(MotorPort.B);

			public Actions() {
				super(null, port2);
				// TODO Auto-generated constructor stub
			}

			//final ? surcharge autorisée? 

			/**
			 * Méthode qui permet d'avancer en activant les deux servomoteurs
			 * pq la méthode s'arrête
			 */
			public void avancer() {
				mp.forward();
				Delay.msDelay(2000);
			}

			public void reculer () {//reculer
				mp.backward();
				Delay.msDelay(2000);
			}

			public void demiTour () {//faire demi-tour
				Delay.msDelay(500);//500 est à remplacer par une constante à définir en test
				arreter();
			}

			public void tourner90 (char gd) {//tourne à gauche
				if(gd=='d') {
					motorA.backward();
					motorB.forward();
					activerServomoteurs();
					Delay.msDelay(500);//500 est à remplacer par une valeur à définir en test
					arreter();
				}
				else if(gd=='g') {
					motorA.forward();
					motorB.backward();
					activerServomoteurs();
					Delay.msDelay(500);//500 est à remplacer par une valeur à définir en test
					arreter();
				}
			}

			/**
			 * Méthode qui doit tourner le robot d'un angle défini à gauche ou à droite
			 * @param gd désigne la gauche ou la droite
			 * @param angle est l'angle dont le robot doit tourner, l'angle est déjà converti en temps
			 */
			public void tourner (char gd,int angle) {
				if(gd=='d') {
					motorAr.startSynchronization();
					motorAr.rotate(angle);
					motorBr.rotate(angle);
					motorAr.endSynchronization();
					activerServomoteurs();
					Delay.msDelay(500);//500 est à remplacer par une valeur à définir en test
					arreter();
				}
				else if(gd=='g') {
					motorBr.startSynchronization();
					motorBr.rotate(angle);
					motorAr.rotate(angle);
					motorBr.endSynchronization();
					activerServomoteurs();
					Delay.msDelay(500);//500 est à remplacer par une valeur à définir en test
					arreter();
				}
				//avancerUneRoue(gd,angle);
				//check le SonicSensor pour savoir si on peut avancer
				//check le ColorSensor pour savoir où on est à peu près
			}

			public void arreter () {
				// stop motors with brakes on. 
				motorA.stop();
				motorB.stop();
			}

			public void activerServomoteurs () {
				// set motors to 50% power.
				motorA.setPower(50);
				motorB.setPower(50);
			}

			public void esquiverRobot (){

			}

			public void reconnaitre () {
				/*if(isPressed()) {
					System.out.println(AttPalet);
				}
				if(getDistance() < 5) {  //5 est à remplacer par une valeur à définir en test
					System.out.println(AttMur);
				}
				if(sensorSonor()){
					System.out.println(AttRobot); }*/
			}

			public void ouvrirPinces () {
				motorC.backward();
				motorC.setPower(100);
				Delay.msDelay(1000);
				motorC.close();
			}

			public void fermerPinces () {
				motorC.forward();
				motorC.setPower(100);
				Delay.msDelay(1000);
				motorC.close();
			}
			
			public void recupererPalet () {
				for(int i = 15; i > 10; i --) { //15 et 10 sont à remplacer par une valeur à définir en test
					if(getDistance() == i) {
						avancer();
						if(isPressed()) {
							fermerPinces();
						}
					}
					else {
						tourner('g', 10);
						if(getDistance() == i) {
							avancer();
							if(isPressed()) {
								fermerPinces();
							}
						}
						else {
							tourner('d', 20);
						}
					}
				}
			}
			
			public void deposerPalet () {
				if(CapteurCouleur() == "blanc") {
					ouvrirPinces();
					tourner('g', 180);
				}
			}

			public void test () {

			}

		

	}
