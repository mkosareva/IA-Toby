package ia;


import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
/**
 * Cette classe contient toutes les actions que le robot peut réaliser au sens
 * physique. On utilise des instances de Chassis et MovePilot pour permettre au 
 * Robot d'effectuer des actions de manière synchronisée par rapport à ses 
 * différents moteurs. On instancie aussi deux EV3LargeRegulatedMotor, qui désigne
 * les moteurs des roues gauche et droite et un EV3MediumRegulatedMotor, moteur
 * des pinces.
 * @author Toby's group 
 *
 */
public class Actions {

	private Chassis chassis;
	private MovePilot mp;

	private EV3LargeRegulatedMotor moteurGauche;
	private EV3LargeRegulatedMotor moteurDroit;
	private EV3MediumRegulatedMotor moteurPince;

	/*
	 * Les trois chaines de caractères ci-dessous sont des constantes
	 * que le Robot doit retourner lorsqu'il détecte l'un des objets
	 * de son environnement.
	 */
	private final String AttRobot="Attention Robot !";
	private final String AttPalet="Attention Palet !";
	private final String AttMur="Attention Mur !";

	/*
	 * Cet attribut permet de savoir si les pinces sont ouvertes ou fermées.
	 */
	private boolean pincesOuverte =false;
	int points=0;

	
	/**
	 * Constructeur de la classe Action qui initialise les moteurs et les
	 * synchronise avec les ports correspondants. Créé aussi la roue droite
	 * et gauche pour le chassis. La taille des roues est prise en compte pour
	 * les intégrer au chassis. On se réfère au centre de ces derniers
	 * pour une synchronisation optimale.
	 * @param portA pour connecter le moteur droit à la brique EV3
	 * @param portB pour connecter le moteur gauche  à la brique EV3
	 * @param portC pour connecter le moteur des pinces à la brique EV3
	 */
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
	 * Méthode qui permet de faire avancer le Robot à une vitesse
	 * et pendant une distance données. La vitesse est en mm/sec²
	 * car le diamètre des roues est donneé en mm
	 * @param vitesse donée en mm/sec²
	 * @param distance parcourue en avant si positif et en arrière si négatif
	 */
	public void avancer(int vitesse, int distance) {
		mp.setLinearAcceleration(vitesse);
		mp.setLinearSpeed(vitesse);
		mp.travel(distance);
	}

	/**
	 * Méthode similaire à  avancer(int vitesse, int distance) qui fait
	 * d'ailleurs appel à cette dernière avec une valeur distance négative
	 * de façon à faire reculer le robot.
	 * @param vitesse
	 * @param distance
	 */
	public void reculer (int vitesse, int distance) { 
		avancer(vitesse, -distance);
	}

	/**
	 * Méthode qui permet au robot de tourner. On imagine que les pinces
	 * désignent l'avant du robot et donc les degrés 0 / 360. Il est
	 * optimal d'utiliser des angles de 0 à 180 degrés car si le degré
	 * est positif, le robot tourne vers la droite, et si le degré est 
	 * négatif le robot tourne vers la gauche.
	 * @param angle pour définir le degré duquel le robot tourne
	 */
	public void orienter (int angle) {
		mp.setAngularAcceleration(100);
		mp.setAngularSpeed(100);
		mp.rotate(angle);
	}

	/**
	 * Cette méthode permet au robot d'arrêter les actions en cours.
	 */
	public void arreter () {
		mp.stop();
	}

	/**
	 * Cette méthode permet au Robot d'ouvrir ses pinces et de définir l'attribut
	 * @see pincesOuverte 
	 * @return l'état des pinces, ouvertes ici
	 */
	public boolean ouvrirPinces () {
		moteurPince.rotate((int)moteurPince.getMaxSpeed());
		pincesOuverte=true;
		return pincesOuverte; 
	}

	/**
	 * Cette méthode permet au Robot de fermer ses pinces et de définir l'attribut
	 * @see pincesOuverte 
	 * @return l'état des pinces, fermées ici
	 */
	public boolean fermerPinces () {
		moteurPince.rotate(-1000);
		pincesOuverte=false;
		return pincesOuverte;
	}
	
	/**
	 * Méthode qui compte le nombre de palet déposé par notre robot. Cette méthode sera
	 * appelée par un autre programme sous certaines conditions. Si ce chiffre vaut 9 
	 * une autre méthode est appelée.
	 * @return points le nomnbre de palets déposés.
	 */
	public int compteurPalet () {
		points+=points;
		if(points==9) this.fin();
		return points;
	}
	
	public void fin() {
		this.orienter(1800);
	}

}