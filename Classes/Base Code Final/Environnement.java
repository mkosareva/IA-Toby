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
	 * Couleur perçu en dernier
	 */
	public String getCouleur() {
		return couleur;
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

}
