package ia;


import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
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
	public  EV3UltrasonicSensor distance ;
	private SampleProvider ecart = distance.getMode("Distance");
	private float[] tab;
	private boolean pincesOuverte =true;
	Capteurs c= new Capteurs();

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
	 * M–πthode qui permet d'avancer en activant les deux servomoteurs
	 * La vitesse doit –∫tre sup–πrieur –∞ 0
	 * Si la distance est positive, le robot avance, si elle est n–πgative
	 * le robot recule
	 */
	public void avancer(int vitesse, int distance) {
		mp.setLinearAcceleration(vitesse);
		mp.setLinearSpeed(vitesse);
		mp.travel(distance);
	}

	/**
	 * M–πthode qui permet de reculer en activant les deux servomoteurs
	 * et en faisant appel –∞ la m–πthode avancer()
	 */
	public void reculer (int vitesse, int distance) { 
		avancer(vitesse, -distance);
	}

	/*
	 * M–πthode qui permet au robot de s'orienter diff–πrement selon l'angle
	 * rentr–π en param–∏tre
	 * Si angle est positif, le robot tourne vers la droite
	 * Si angle est n–πgatif, le robot tourne vers la gauche
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
		/* 
		 * valeur a mesurer 
		 * mise au pif ici ATTENTION 
		 */
		while(pincesOuverte) {
			if((getDistance()<200 && getDistance()> 20) && !c.isPressed()) {
				avancer(400,10);
			}

			if(getDistance()<20 && ! c.isPressed()) {
				/* 
				 * on avance encore un peu si jamais 
				 * le bouton est presser comme ca 
				 */
				avancer(200,5); 
				while(getDistance()<200 && getDistance()> 20) { 
					/* 
					 * on tourne doucement jusqu'a trouver un 
					 * object (techniquement palet) proche 
					 * de nous 
					 */
					orienter(10);
				}
			}

			if(c.isPressed()) {
				fermerPinces();
			}
		}
	}
	private float getDistance() {
		ecart = distance.getDistanceMode();
		ecart.fetchSample(tab, 0);
		return tab[0];
	}


	public boolean ouvrirPinces () {
		moteurPince.rotate((int)moteurPince.getMaxSpeed());
		pincesOuverte=true;
		return pincesOuverte; 
	}

	public boolean fermerPinces () {
		moteurPince.rotate(-(int)moteurPince.getMaxSpeed());
		pincesOuverte=false;
		return pincesOuverte;
	}

	public void recupererPalet () {
		for(int i = 15; i > 10; i --) { //15 et 10 sont √† remplacer par une valeur √† d√©finir en test
			if(getDistance() == i) {
				avancer();
				if(c.isPressed()) {
					fermerPinces();
				}
			}
			else {
				tourner('g', 10);
				if(getDistance() == i) {
					avancer();
					if(c.isPressed()) {
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
		if(c.CapteurCouleur() == "blanc") {
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
		//‡ partir de l‡ on getDeuxiËme palet
		orienter(135);//p.Í on peut dire -45 et aller cherche le 2eme palet directement ici //en rÈalitÈ il faut qu'on s'aide du capteur ultrasonic ici
		avancer(400,500);//en rÈalitÈ on veut avancer tant que capteur toucher pas enfoncÈ
		fermerPinces();
		orienter(-135);
		avancer(400,600);
		ouvrirPinces();
		avancer(300,-100);
		orienter(180);
	}

}