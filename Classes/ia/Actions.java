package ia;


import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
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

	private boolean pincesOuverte =true;

	public Actions (Port portA, Port portB, Port portC) {

		moteurGauche = new EV3LargeRegulatedMotor(portB);
		moteurPince = new EV3MediumRegulatedMotor(portC);
		moteurDroit = new EV3LargeRegulatedMotor(portA);

		Wheel wheelG = WheeledChassis.modelWheel(moteurGauche, 56).offset(-60);
		Wheel wheelD = WheeledChassis.modelWheel(moteurDroit,  56).offset( 60);
		chassis = new WheeledChassis(new Wheel[] {wheelG, wheelD}, WheeledChassis.TYPE_DIFFERENTIAL);
		mp = new MovePilot(chassis);

		
	} 

	/**
	 * Mйthode qui permet d'avancer en activant les deux servomoteurs
	 * La vitesse doit кtre supйrieur а 0
	 * Si la distance est positive, le robot avance, si elle est nйgative
	 * le robot recule
	 */
	public void avancer(int vitesse, int distance) {
		mp.setLinearAcceleration(vitesse);
		mp.setLinearSpeed(vitesse);
		mp.travel(distance);
	}

	/**
	 * Mйthode qui permet de reculer en activant les deux servomoteurs
	 * et en faisant appel а la mйthode avancer()
	 */
	public void reculer (int vitesse, int distance) { 
		avancer(vitesse, -distance);
	}

	/*
	 * Mйthode qui permet au robot de s'orienter diffйrement selon l'angle
	 * rentrй en paramиtre
	 * Si angle est positif, le robot tourne vers la droite
	 * Si angle est nйgatif, le robot tourne vers la gauche
	 */
	public void orienter (int angle) {
		mp.setAngularAcceleration(100);
		mp.setAngularSpeed(100);
		mp.rotate(angle);
	}

	public void arreter () {
		mp.stop();
	}


	public boolean ouvrirPinces () {
		moteurPince.rotate((int)moteurPince.getMaxSpeed());
		pincesOuverte=true;
		return pincesOuverte; 
	}

	public boolean fermerPinces () {
		moteurPince.rotate(-1000);
		pincesOuverte=false;
		return pincesOuverte;
	}

}