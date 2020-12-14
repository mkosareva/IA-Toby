import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;

/**
 * Classe qui sert à définir plusieurs stratégies et lors de la compétition.
 * @author Toby's group
 *
 */
public class Strategies {

	/**
	 * Instances des classes capteurs et actions qui permettent à l'agent d'évoluer dans
	 * son environnement les stratégies choisies.
	 */
	Actions action=new Actions (MotorPort.A, MotorPort.B, MotorPort.C);
	Capteurs capteur=new Capteurs();
	//Environnement env=new Environnement(1,3);

	private boolean pincesOuverte =true;

	/**
	 * Méthode qui sert à rechercher des palets sur la plateau.
	 */
	public void rechercher () {
		int angle=0;
		while(pincesOuverte!=true) {
			/*
			 * Tant que la distance est inferieur à 1,200m 
			 * et supérieure à 0,325m, ET que le capteur 
			 * toucher n'est pas pressé, on avance
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
					 * Si deviens superieur on a affaire à un palet
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
	 * Méthode qui sert à aller déposer un palet une fois qu'il est collecté.
	 */
	public void deposerPalet () {
		while(Button.ESCAPE.isUp() || capteur.getDistance()<0.2) {
			action.avancer(400,100);
		}
		action.ouvrirPinces();
		action.orienter(180);
	}

	/**
	 * Méthode de code brut qui sert à récupérer les trois premiers palets au début de la
	 * compétition et les 5 premiers palets à la fin de la compétition. Les paramètres sont
	 * des angles qui servent à orienter le robot. Tant que le code brut fonctionne le robot
	 * suit ce code, sinon la méthode rechercher intervient.
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
			//appel à une autre méthode ?
			action.orienter(j);
			action.avancer(300,700);
			action.fermerPinces();
			if(capteur.isPressed()) {
				//deuxième palet
				action.orienter(-j);
				action.avancer(300,700);
				action.ouvrirPinces();
				action.avancer(300,-100);
				action.orienter(180);
				action.avancer(300, 1400);
				if(capteur.isPressed()) {
					//troisième palet
					action.fermerPinces();
					action.orienter(180);
					action.avancer(300, 1500);
					action.ouvrirPinces();
					action.avancer(300, -100);
					action.orienter(k);
					action.avancer(300, 800);
					if(capteur.isPressed()) {
						//quatrième palet
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
			//boucle for qui tourne tant que le robot n'a pas ramener 9 palets même si c'est
			//plus possible en théorie qu'en pratique
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
	 * Méthode définit comme la stratégie 1 : notre agent commence à gauche sur le terrain
	 * Explication des angles (signe positif = le robot tourne vers la droite vs signe
	 * négativ le robot tourne vers la gauche) :
	 * 90 : angle définit par rappot au terrain, les cases sont toutes tes rectangles
	 * 157 : angle définit par rapport à la position du robot une fois qu'il a déposé
	 * palet et qu'il s'est retournée. Il se trouve environ à la moitié de la largeur 
	 * du rectangle auquel il fait face. On définit donc un triangle rectangle de côté
	 * adjacent 650 mm et de côté opposé 250 mm. Grâce au théorème de Pythagore on trouve
	 * l'hypothénuse pour connaitre la distance que doit parcourir le robot pour arriver
	 * au prochain palet. Une fois les trois distance du tiangle connu on utilise une
	 * formule de trigonométrie pour trouver l'angle duquel le robot doit tourner pour
	 * être face au palet. On utilise 157 car lorsque le robot vient déposer le premier
	 * on le fait se tourner de 90+67=157 degrés directement (optimisation d'appel de
	 * méthode).
	 * 129 : sensiblement la même méthode que pour trouver l'angle 157 degré sauf que
	 * la position du robot par rapport au palet à récupérer est différente
	 */
	public void s1_debutG () {
		action.ouvrirPinces();
		tactiqueNormale(-90, 157, 129);
	}

	/**
	 * Méthode définit comme la stratégie 2 : notre agent commence au milieu sur le terrain.
	 * Cette méthode est moins développée car c'est une stratégie à laquelle nous ne voulions
	 * pas spécialement recourir mais plutot l'utiliser pour se défendre.
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
	 * Cette méthode est symétrique sur le plateau à s1_debutG, seuls les signes des angles
	 * changent mais tout se déroule de la même manière à l'exception que notre robot démarre
	 * le jeu à droite du plateau.
	 * @see s1_debutG
	 */
	public void s3_debutD () {
		action.ouvrirPinces();
		tactiqueNormale(90, -157, -129);
	}

	/**
	 * Méthode qui permet à l'agent de s'arrêter. Redéfinie ici car utilisée dans les méthodes
	 * permettant de lancer le programme embarqué.
	 * @see DebutG DebutM DebutD
	 */
	public void stop() {
		action.arreter();
	}

}
