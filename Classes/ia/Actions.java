package ia;


import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
/**
 * Cette classe contient toutes les actions que le robot peut r�aliser au sens
 * physique. On utilise des instances de Chassis et MovePilot pour permettre au 
 * Robot d'effectuer des actions de mani�re synchronis�e par rapport � ses 
 * diff�rents moteurs. On instancie aussi deux EV3LargeRegulatedMotor, qui d�signe
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
	 * Les trois chaines de caract�res ci-dessous sont des constantes
	 * que le Robot doit retourner lorsqu'il d�tecte l'un des objets
	 * de son environnement.
	 */
	private final String AttRobot="Attention Robot !";
	private final String AttPalet="Attention Palet !";
	private final String AttMur="Attention Mur !";

	/*
	 * Cet attribut permet de savoir si les pinces sont ouvertes ou ferm�es.
	 */
	private boolean pincesOuverte =false;
	int points=0;

	
	/**
	 * Constructeur de la classe Action qui initialise les moteurs et les
	 * synchronise avec les ports correspondants. Cr�� aussi la roue droite
	 * et gauche pour le chassis. La taille des roues est prise en compte pour
	 * les int�grer au chassis. On se r�f�re au centre de ces derniers
	 * pour une synchronisation optimale.
	 * @param portA pour connecter le moteur droit � la brique EV3
	 * @param portB pour connecter le moteur gauche  � la brique EV3
	 * @param portC pour connecter le moteur des pinces � la brique EV3
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
	 * M�thode qui permet de faire avancer le Robot � une vitesse
	 * et pendant une distance donn�es. La vitesse est en mm/sec�
	 * car le diam�tre des roues est donne� en mm
	 * @param vitesse don�e en mm/sec�
	 * @param distance parcourue en avant si positif et en arri�re si n�gatif
	 */
	public void avancer(int vitesse, int distance) {
		mp.setLinearAcceleration(vitesse);
		mp.setLinearSpeed(vitesse);
		mp.travel(distance);
	}

	/**
	 * M�thode similaire �  avancer(int vitesse, int distance) qui fait
	 * d'ailleurs appel � cette derni�re avec une valeur distance n�gative
	 * de fa�on � faire reculer le robot.
	 * @param vitesse
	 * @param distance
	 */
	public void reculer (int vitesse, int distance) { 
		avancer(vitesse, -distance);
	}

	/**
	 * M�thode qui permet au robot de tourner. On imagine que les pinces
	 * d�signent l'avant du robot et donc les degr�s 0 / 360. Il est
	 * optimal d'utiliser des angles de 0 � 180 degr�s car si le degr�
	 * est positif, le robot tourne vers la droite, et si le degr� est 
	 * n�gatif le robot tourne vers la gauche.
	 * @param angle pour d�finir le degr� duquel le robot tourne
	 */
	public void orienter (int angle) {
		mp.setAngularAcceleration(100);
		mp.setAngularSpeed(100);
		mp.rotate(angle);
	}

	/**
	 * Cette m�thode permet au robot d'arr�ter les actions en cours.
	 */
	public void arreter () {
		mp.stop();
	}

	/**
	 * Cette m�thode permet au Robot d'ouvrir ses pinces et de d�finir l'attribut
	 * @see pincesOuverte 
	 * @return l'�tat des pinces, ouvertes ici
	 */
	public boolean ouvrirPinces () {
		moteurPince.rotate((int)moteurPince.getMaxSpeed());
		pincesOuverte=true;
		return pincesOuverte; 
	}

	/**
	 * Cette m�thode permet au Robot de fermer ses pinces et de d�finir l'attribut
	 * @see pincesOuverte 
	 * @return l'�tat des pinces, ferm�es ici
	 */
	public boolean fermerPinces () {
		moteurPince.rotate(-1000);
		pincesOuverte=false;
		return pincesOuverte;
	}
	
	/**
	 * M�thode qui compte le nombre de palet d�pos� par notre robot. Cette m�thode sera
	 * appel�e par un autre programme sous certaines conditions. Si ce chiffre vaut 9 
	 * une autre m�thode est appel�e.
	 * @return points le nomnbre de palets d�pos�s.
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