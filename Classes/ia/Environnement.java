package ia;


/**
 * La classe Envorinnement permet de savoir où se trouve le robot et à detecter des palets
 * 
 * !! ATTENTION il faudra rajouter les couleurs aussi afin de pouvoir se reperer 
 */
public class Environnement {
	private String couleur = new String();
	private int angle;
	private int debutR,debutA;
	/*code ajouté de la deuxième classe Environnement
	*int nombrePalets=9;
	*int nbPoint=0;
	*final static String[] terrain = new String[] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R"};
	*String position;
	*String etat;
	*String couleur1,couleur2;
	*/
	
	/*
	 * Angle de départ
	 */
	public Environnement(int angle) {
		this.angle = angle;
	}

	/* 
	 * La position debutR, celle a partir de laquelle notre robot demarre
	 * La position debutA, celle à partir de laquelle l'autre robot demarre
	 */
	public Environnement(int debutA, int debutR) {
		this.debutR = debutR;
		this.debutA = debutA;
	}
	
	
	
	/**
	 * Donne la nouvelle couleur prélever
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	/*
	 * Retourne l'angle
	 */
	public int getAngle() {
		return this.angle;
	}
	
	/*
	 * Nouvelle valeur de la direction
	 */

	public void setAngle(int angle) {
		this.angle = angle;
	}

	/*
	 * Position de depart du robot Adverse 
	 */
	public int getDebutA() {
		return debutA;
	}
	/*
	 * Position de depart de notre robot
	 */
	public int getDebutR() {
		return debutR;
	}

	/*
	 *  la direction du mur a droite de la base ennemie
	 */
	public int getDirDroiteA() {
		if(debutA==0) {
			return 270;
		}
		return 90;
	}

	/*
	 *  la direction du mur a gauche de la base ennemie
	 */
	public int getDirGaucheA() {
		if(debutA==0) {
			return 90;
		}
		return 270;
	}
	
	/**public int points () {
		this.nbPoint+=1;
		this.nombrePalets-=1;
		return nbPoint;
	}
	*/
	
	public boolean perdu() {
		String etat="";
		if(etat=="perdu") {
			return true;
		}
		else return false;
	}
	
	/*public void avancer (cm) {
		activerServomoteurs();
		Delay.msDelay(cm);// utiliser un paramètre à la place de 2000
		if(distance<paramètreFinalADefinir) {
			motorA.stop(); //motor A s'arrête
			motorB.stop(); //motor A s'arrête
			if() {//si robot
				//faire action X
				return AttRobot;
			}
			else if( ) {//si palet
				//faire action Y
				return AttPalet;
			}
			else if (){//si mur
				//faire action Z
				return AttRobot;
			}
			else () {//si ligne blanche
				//faire action B
				return "Ligne blanche";
			}
		}
		return null;
	}*/

	
	 
	
	public String getPos() {
		//àfinir
	/*if(couleur1=="jaune"&&couleur2=="vert"||couleur1=="jaune"&&couleur2=="noir"||couleur1=="jaune"&&couleur2=="vert") {
		position="B";
	}*/
		return null;
	}
	
	
	/*
	public void getCouleur() {
		//permet de recup les 2 dernières couleur sur lesquels on est passé, couleur1 est la plus récente
		// et couleur2 la plus vieille
		Capteurs Captcouleur = new Capteurs();
		if(couleur1==null) {
		couleur1=Captcouleur.CapteurCouleur();
		}
		else {
			couleur2=couleur1;
			couleur1=Captcouleur.CapteurCouleur();
		}
		
	}
	*/

}
