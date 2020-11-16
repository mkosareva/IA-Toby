package ia;


import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import lejos.hardware.Button;
/**
 * @author Utilisateur
 *
 */
public class Actions {

	private Chassis chassis;
	private MovePilot mp;
	private EV3LargeRegulatedMotor moteurGauche;
	private EV3LargeRegulatedMotor moteurDroit;
	private EV3MediumRegulatedMotor moteurPince;
	String AttRobot="Attention Robot !";
	String AttPalet="Attention Palet !";
	String AttMur="Attention Mur !";
	
	public Actions (Port portA, Port portB, Port portC) {
		moteurGauche = new EV3LargeRegulatedMotor(portB);
		moteurPince = new EV3MediumRegulatedMotor(portC);
		moteurDroit = new EV3LargeRegulatedMotor(portA);
		Wheel wheel1 = WheeledChassis.modelWheel(moteurGauche, 56).offset(-60);
		Wheel wheel2 = WheeledChassis.modelWheel(moteurDroit,  56).offset( 60);
		chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		mp = new MovePilot(chassis);
	} 

	/**
	 * M�thode qui permet d'avancer en activant les deux servomoteurs
	 */
	public void avancer() {
		mp.forward();
		Delay.msDelay(2000);
	}

	public void reculer () {
		mp.backward();
		Delay.msDelay(2000);
	}

	public void demiTour () {
		
		Delay.msDelay(500);//500 est � remplacer par une constante � d�finir en test
		arreter();
	}

	public void tourner90 (char gd) {//tourne � gauche
		if(gd=='d') {
			moteurGauche.forward();
			moteurDroit.stop();
			Delay.msDelay(1000);//500 est � remplacer par une valeur � d�finir en test
		}
		else if(gd=='g') {
			
			Delay.msDelay(500);//500 est � remplacer par une valeur � d�finir en test
			arreter();
		}
	}

	/**
	 * M�thode qui doit tourner le robot d'un angle d�fini � gauche ou � droite
	 * @param gd d�signe la gauche ou la droite
	 * @param angle est l'angle dont le robot doit tourner, l'angle est d�j� converti en temps
	 */
	public void tourner (char gd,int angle) {
		//avancerUneRoue(gd,angle);
		//check le SonicSensor pour savoir si on peut avancer
		//check le ColorSensor pour savoir o� on est � peu pr�s
	}

	public void arreter () {
		
	}

	public void esquiverRobot (){

	}

	public void reconnaitre () {
		//if()
	}

	public void ouvrirPinces () {
		
	}

	public void fermerPinces () {
		
	}
	public void recupererPalet () {

	}

	public void trouverLigne () {

	}

	public void test () {

	}



}