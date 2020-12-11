package ia;

import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class Strategies {

	Actions action=new Actions (MotorPort.A, MotorPort.B, MotorPort.C);
	Capteurs capteur=new Capteurs(SensorPort.S2,SensorPort.S4,SensorPort.S3);

	private boolean pincesOuverte =true;
	private boolean perdu=false;

	public Strategies() {
		// TODO Auto-generated constructor stub
	}

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

	public void deposerPalet () {
		while(Button.ESCAPE.isUp() || capteur.getDistance()<0.2) {
			action.avancer(400,100);
		}
		action.ouvrirPinces();
		action.orienter(180);
	}

	public boolean getPincesOuverte() {
		return pincesOuverte;
	}

	public void setPincesOuverte(boolean b) {
		pincesOuverte=b;
	}

	public boolean getPerdu() {
		return perdu;
	}

	public void setPerdu(boolean b) {
		perdu=b;
	}

	/**
	 * 
	 * @param i l'angle duquel le robot tourne
	 * @param j
	 */
	public void tactiqueNormale(int i, int j, int k) {
		action.avancer(400,700);
		action.arreter();
		action.fermerPinces();
		//if(capteur.isPressed()) {
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
		//if(capteur.isPressed()) {
		action.orienter(-j);
		action.avancer(300,700);
		action.ouvrirPinces();
		action.avancer(300,-100);
		action.orienter(180);
		action.avancer(300, 1400);
		action.fermerPinces();
		action.orienter(180);
		action.avancer(300, 1500);
		action.ouvrirPinces();
		action.avancer(300, -100);
		action.orienter(k);
		action.avancer(300, 800);
		action.avancer(300, 800);
		action.fermerPinces();
		action.orienter(-129);
		action.avancer(300, 1750);
		action.ouvrirPinces();
		action.orienter(90);
		action.avancer(400, 1700);
		action.orienter(90);
		action.avancer(300, 750);
		action.orienter(180);
		action.avancer(300, 900);
		action.ouvrirPinces();
		action.orienter(180);
		if(capteur.getDistance()<0.5) {
			action.avancer(400, 390);
			action.fermerPinces();
			action.orienter(180);
		}
		else {
			for(int n=2;n<10;n++) {
				rechercher();
				deposerPalet();
			}
		}
		for(int n=2;n<10;n++) {
			rechercher();
			deposerPalet();
		}


		/*action.ouvrirPinces();
		setPerdu(true);
		rechercher();//placer le recherche dans le main plutôt;
		 */
	}

	public void tournerAngle(int angle) {
		action.orienter(angle);
		action.avancer(400,650);
		action.fermerPinces();
		action.orienter(-angle);
		action.avancer(400, 600);
	}

	public void s1_debutG () {
		action.ouvrirPinces();
		tactiqueNormale(-90, 157, 129);
	}

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

	public void s3_debutD () {
		action.ouvrirPinces();
		tactiqueNormale(90, -157, -129);
	}

	public void stop() {
		action.arreter();
	}

}
