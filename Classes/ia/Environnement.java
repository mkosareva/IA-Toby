package ia;

import java.awt.Color;

import lejos.hardware.port.Port;

/**
 * Cette classe permet de donner de l'information à notre agent en ce qui concerne
 * se position sur le terrain. 
 */
public class Environnement {
	
	/**
	 * Les attributs représentant les couleurs présentent sur le terrain.
	 */
	private final Color RED=Color.RED;
	private final Color GRAY=Color.GRAY;
	private final Color YELLOW=Color.YELLOW;
	private final Color BLACK=Color.BLACK;
	private final Color GREEN=Color.GREEN;
	private final Color WHITE=Color.WHITE;
	private final Color BLUE=Color.BLUE;
	
	
	
	private int angle;
	private int debutR,debutA;
	
	final static String[] terrain = new String[] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R"};
	String position;

	String couleur1,couleur2;

	/**
	 * Constructeur qui instancie les attributs selon les position de départ des robots.
	 * La position du robot adverse est donnée par rapport à celle de notre robot.
	 * @param debutA position de départ du robot adverse
	 * @param debutR position de départ de notre robot
	 */
	public Environnement(int debutA, int debutR) {
		this.debutR = debutR;
		this.debutA = debutA;
	}
	
	/**
	 * Méthode qui permet de définir un angle à l'aide de la distance 
	 * du capteur ultrasonique de la classe Capteurs. Le robot doit tourner
	 * jusqu'à ce qu'il atteigne la plus petite distance. Il sera alors face à
	 * un mur. 
	 * @return int l'arrondi à l'entier le plus proche de l'angle 
	 */
	public int getAngle() {
		return this.angle;
	}

	/**
	 * Méthode qui retourne une position de départ.
	 * @return debutA la position de départ du robot adverse.
	 */
	public int getDebutA() {
		return debutA;
	}

	/**
	 * Méthode qui retourne une position de départ.
	 * @return debutR la position de départ de notre robot.
	 */
	public int getDebutR() {
		return debutR;
	}
	
	
	
	/**
	 * Méthode qui prend en paramètre un tableau de String et attrape une exception si la couleur
	 * n'est pas équivalente à un attribut. La référence du terrain numéroté est sur la page Github
	 * du projet. Cette méthode était un autre moyen de trouver la direction du robot.
	 * @param couleur les dernières couleurs détectées par le robot.
	 * @return char un charactère du tableau terrain.
	 */
	public char getPos(String[] couleur) {
		return ' ';
	}
	
	/**
	 * Méthode qui devait servir à rappeler les deux dernières couleurs perçues par le
	 * capteur de couleurs.
	 * @return tab un tableau de String contenant les couleurs captés.
	 */
	public String[] lastColors() {
		String [] tab= new String [2];
		Capteurs CapteurCouleur = new Capteurs();
		if(couleur1!=null) {
			couleur2=CapteurCouleur.getCouleur();
			tab[0]=couleur2;
			tab[1]=couleur1;
			return tab;
		}
		couleur1=CapteurCouleur.getCouleur();
		tab[0]=couleur1;
		tab[1]="unknown";
		return tab;
	}
	
	/**
	 * Méthode qui devait être utilisée pour prévenir si le robot est dans un état dit
	 * "perdu" mais il est plus efficace d'appeler directement une méthode de recherche.
	 * @return boolean qui indique true si le robot est perdu.
	 */
	/*public boolean perdu() {
		if(etat=="perdu") {
			return true;
		}
		else return false;
	}*/

}
