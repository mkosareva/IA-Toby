import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;

/**
 * Classe qui sert � d�finir plusieurs strat�gies et lors de la comp�tition.
 * @author Toby's group
 *
 */
public class Strategies {

	/**
	 * Instances des classes capteurs et actions qui permettent � l'agent d'�voluer dans
	 * son environnement les strat�gies choisies.
	 */
	Actions action=new Actions (MotorPort.A, MotorPort.B, MotorPort.C);
	Capteurs capteur=new Capteurs();
	//Environnement env=new Environnement(1,3);

	private boolean pincesOuverte =true;

	/**
	 * M�thode qui sert � rechercher des palets sur la plateau.
	 */
	public void rechercher () {
		int angle=0;
		while(pincesOuverte!=true) {
			/*
			 * Tant que la distance est inferieur � 1,200m 
			 * et sup�rieure � 0,325m, ET que le capteur 
			 * toucher n'est pas press�, on avance
			 */
			while((capteur.getDistance()<0.600 && capteur.getDistance()> 0.325) && !capteur.isPressed()) { 
				// il faudra remettre 1.300 pour le vrai au lieu de 0.600
				action.avancer(400,50);

				/*
				 * si le capteur distance indique 0,326m on avance encore un peu
				 * afin de savoir si palet ou pas
				 */
				if(capteur.getDistance()<=0.376) {
					action.avancer(400,80);
					/*
					 * Si deviens superieur on a affaire � un palet
					 */
					if(capteur.getDistance()>0.326) {
						action.avancer(400,300);
						if(pincesOuverte) action.fermerPinces();
					}
				}
			}

			if(capteur.getDistance()<0.326 && !capteur.isPressed()) {
				/*
				 * ici le cas ou on a pas affaire a un palet
				 */
				while((capteur.getDistance()>0.600 || capteur.getDistance()< 0.325) && !capteur.isPressed()) {
					/*
					 * on tourne doucement jusqu'a trouver un
					 * objet (techniquement palet) proche +
					 * de nous
					 */
					action.orienter(20);
					angle=angle+20;
				}
			}

			else if(capteur.getDistance()>0.600) {
				/*
				 * tant qu'on ne detecte rien
				 * on tourne 
				 */
				action.orienter(20);
				angle=angle+20;
			}
		}
		if(capteur.isPressed()) {
			if(pincesOuverte) action.fermerPinces();
		}
		action.orienter(180-angle);
	}

	/**
	 * M�thode qui sert � aller d�poser un palet une fois qu'il est collect�.
	 */
	public void deposerPalet () {
		while(Button.ESCAPE.isUp() || capteur.getDistance()<0.2) {
			action.avancer(400,100);
		}
		action.ouvrirPinces();
		action.orienter(180);
	}

	/**
	 * M�thode de code brut qui sert � r�cup�rer les trois premiers palets au d�but de la
	 * comp�tition et les 5 premiers palets � la fin de la comp�tition. Les param�tres sont
	 * des angles qui servent � orienter le robot. Tant que le code brut fonctionne le robot
	 * suit ce code, sinon la m�thode rechercher intervient.
	 * @param i l'angle duquel le robot tourne
	 * @param j l'angle duquel le robot tourne
	 * @param k l'angle duquel le robot tourne
	 */
	public void tactiqueNormale(int i, int j, int k) {
		action.avancer(400,700);
		action.arreter();
		action.fermerPinces();
		if(capteur.isPressed()) {
			//premier palet
			action.orienter(i);
			action.avancer(400,250);
			action.arreter();
			action.orienter(-i);
			action.avancer(300,1800);
			action.arreter();
			action.ouvrirPinces();
			action.avancer(300,-100);
			//appel � une autre m�thode ?
			action.orienter(j);
			action.avancer(300,700);
			action.fermerPinces();
			if(capteur.isPressed()) {
				//deuxi�me palet
				action.orienter(-j);
				action.avancer(300,700);
				action.ouvrirPinces();
				action.avancer(300,-100);
				action.orienter(180);
				action.avancer(300, 1400);
				if(capteur.isPressed()) {
					//troisi�me palet
					action.fermerPinces();
					action.orienter(180);
					action.avancer(300, 1500);
					action.ouvrirPinces();
					action.avancer(300, -100);
					action.orienter(k);
					action.avancer(300, 800);
					if(capteur.isPressed()) {
						//quatri�me palet
						action.fermerPinces();
						action.orienter(-k);
						action.avancer(300, 1750);
						action.ouvrirPinces();
						action.avancer(300, -100);
						action.orienter(180);
					}
				}
			}
		}
		if(pincesOuverte==true) {
			//boucle for qui tourne tant que le robot n'a pas ramener 9 palets m�me si c'est
			//plus possible en th�orie qu'en pratique
			for(int n=2;n<10;n++) {
				rechercher();
				deposerPalet();
			}
		}
		else {
			for(int n=2;n<10;n++) {
				rechercher();
				deposerPalet();
			}
		}
	}


	/**
	 * M�thode d�finit comme la strat�gie 1 : notre agent commence � gauche sur le terrain
	 * Explication des angles (signe positif = le robot tourne vers la droite vs signe
	 * n�gativ le robot tourne vers la gauche) :
	 * 90 : angle d�finit par rappot au terrain, les cases sont toutes tes rectangles
	 * 157 : angle d�finit par rapport � la position du robot une fois qu'il a d�pos�
	 * palet et qu'il s'est retourn�e. Il se trouve environ � la moiti� de la largeur 
	 * du rectangle auquel il fait face. On d�finit donc un triangle rectangle de c�t�
	 * adjacent 650 mm et de c�t� oppos� 250 mm. Gr�ce au th�or�me de Pythagore on trouve
	 * l'hypoth�nuse pour connaitre la distance que doit parcourir le robot pour arriver
	 * au prochain palet. Une fois les trois distance du tiangle connu on utilise une
	 * formule de trigonom�trie pour trouver l'angle duquel le robot doit tourner pour
	 * �tre face au palet. On utilise 157 car lorsque le robot vient d�poser le premier
	 * on le fait se tourner de 90+67=157 degr�s directement (optimisation d'appel de
	 * m�thode).
	 * 129 : sensiblement la m�me m�thode que pour trouver l'angle 157 degr� sauf que
	 * la position du robot par rapport au palet � r�cup�rer est diff�rente
	 */
	public void s1_debutG () {
		action.ouvrirPinces();
		tactiqueNormale(-90, 157, 129);
	}

	/**
	 * M�thode d�finit comme la strat�gie 2 : notre agent commence au milieu sur le terrain.
	 * Cette m�thode est moins d�velopp�e car c'est une strat�gie � laquelle nous ne voulions
	 * pas sp�cialement recourir mais plutot l'utiliser pour se d�fendre.
	 */
	public void s2_debutM () {
		action.avancer(400, 600);
		action.fermerPinces();	
		action.avancer(400,1000);
		action.orienter(90);
		action.avancer(400, 250);
		action.orienter(-90);
		action.avancer(400,300);
		action.ouvrirPinces();
		action.avancer(300, -100);
		action.orienter(180);
		for(int i=2;i<10;i++) {
			action.avancer(400, 800);
			rechercher();
			action.avancer(400, 900);
			if(capteur.getDistance()<20) {
				action.arreter();
				action.orienter(180);
			}
		}
	}

	/**
	 * Cette m�thode est sym�trique sur le plateau � s1_debutG, seuls les signes des angles
	 * changent mais tout se d�roule de la m�me mani�re � l'exception que notre robot d�marre
	 * le jeu � droite du plateau.
	 * @see s1_debutG
	 */
	public void s3_debutD () {
		action.ouvrirPinces();
		tactiqueNormale(90, -157, -129);
	}

	/**
	 * M�thode qui permet � l'agent de s'arr�ter. Red�finie ici car utilis�e dans les m�thodes
	 * permettant de lancer le programme embarqu�.
	 * @see DebutG DebutM DebutD
	 */
	public void stop() {
		action.arreter();
	}

}
