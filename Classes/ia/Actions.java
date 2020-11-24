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
		Wheel wheelG = WheeledChassis.modelWheel(moteurGauche, 56).offset(-60);
		Wheel wheelD = WheeledChassis.modelWheel(moteurDroit,  56).offset( 60);
		chassis = new WheeledChassis(new Wheel[] { wheelG, wheelD }, WheeledChassis.TYPE_DIFFERENTIAL);
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

	public void esquiverRobot (){
		
	}

	public void reconnaitre () {
		
	}

	public void ouvrirPinces () {
		moteurPince.rotate((int)moteurPince.getMaxSpeed());
	}

	public void fermerPinces () {
		moteurPince.rotate(-(int)moteurPince.getMaxSpeed());
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
					orienter(180);
				}
			}

	
	public void get2PremierPaletPourUnAvantageDeFou() {

		/**
		if(etatPinces==false) {
			ouvrirPinces();
		}

		while(!port.isPressed()) {
			avancer(100,100);
		}
		*/
		avancer(400,1000);
		arreter();
		fermerPinces();
		orienter(45);
		avancer(400,200);
		arreter();
		orienter(-45);
		avancer(400,1000);
		/*while(couleur!="blanc") {
			avancer();
		}
		*/
		arreter();
		ouvrirPinces();
		avancer(300,-200);
		//� partir de l� on getDeuxi�me palet
		orienter(135);//p.� on peut dire -45 et aller cherche le 2eme palet directement ici //en r�alit� il faut qu'on s'aide du capteur ultrasonic ici
		avancer(400,500);//en r�alit� on veut avancer tant que capteur toucher pas enfonc�
		fermerPinces();
		orienter(-135);
		avancer(400,600);
		ouvrirPinces();
		avancer(300,-100);
		orienter(180);
	}

}