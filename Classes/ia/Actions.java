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
	MovePilot mp;
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
	 * Méthode qui permet d'avancer en activant les deux servomoteurs
	 * La vitesse doit être supérieur à 0
	 * Si la distance est positive, le robot avance, si elle est négative
	 * le robot recule
	 */
	public void avancer(int vitesse, int distance) {
		mp.setLinearAcceleration(vitesse);
		mp.setLinearSpeed(vitesse);
		mp.travel(distance);
	}

	/**
	 * Méthode qui permet de reculer en activant les deux servomoteurs
	 * et en faisant appel à la méthode avancer()
	 */
	public void reculer (int vitesse, int distance) { 
		avancer(vitesse, -distance);
	}
	
	/*
	 * Méthode qui permet au robot de s'orienter différement selon l'angle
	 * rentré en paramètre
	 * Si angle est positif, le robot tourne vers la droite
	 * Si angle est négatif, le robot tourne vers la gauche
	 */
	public void orienter (int angle) {
		mp.setAngularAcceleration(100);
		mp.setAngularSpeed(100);
		mp.rotate(angle);
	}

	public void arreter () {
		mp.stop();
	}

	public void esquiverRobot (){

	}

	public void reconnaitre () {
		
	}

	public void ouvrirPinces () {
		moteurPince.rotate(1000);
	}

	public void fermerPinces () {
		moteurPince.rotate(-1000);
	}
	
	public void recupererPalet () {

	}

	public void trouverLigne () {

	}

	public void test () {

	}



}