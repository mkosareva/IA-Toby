/**
 * Cette classe permet de donner de l'information � notre agent en ce qui concerne
 * sa position sur le terrain. L'attribut terrain correspond � un choix arbitraire
 * de notre part de segmentation du plateau.
 */
public class Environnement {	
	
	private int angle;
	private int debutR,debutA;
	
	final static String[] terrain = new String[] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R"};

	private String couleur1,couleur2;

	/**
	 * Constructeur qui instancie les attributs selon les position de d�part des robots.
	 * La position du robot adverse est donn�e par rapport � celle de notre robot et
	 * peut valoir 1 (gauche), 2 (milieu) ou 3 (droite).
	 * @param debutA position de d�part du robot adverse
	 * @param debutR position de d�part de notre robot
	 */
	public Environnement(int debutA, int debutR) {
		this.debutR = debutR;
		this.debutA = debutA;
	}
	
	/**
	 * M�thode qui permet de d�finir un angle � l'aide de la distance 
	 * du capteur ultrasonique de la classe Capteurs. Le robot doit tourner
	 * jusqu'� ce qu'il atteigne la plus petite distance. Il sera alors face �
	 * un mur. 
	 * @return int l'arrondi � l'entier le plus proche de l'angle 
	 */
	public int getAngle() {
		return this.angle;
	}

	/**
	 * M�thode qui retourne une position de d�part.
	 * @return debutA la position de d�part du robot adverse.
	 */
	public int getDebutA() {
		return debutA;
	}

	/**
	 * M�thode qui retourne une position de d�part.
	 * @return debutR la position de d�part de notre robot.
	 */
	public int getDebutR() {
		return debutR;
	}
	
	/**
	 * M�thode qui retourne la derni�re couleur d�tect�e.
	 * @return couleur1 en taux de rouge vert bleu
	 */
	public String couleur1() {
		return couleur1;
	}
	
	/**
	 * M�thode qui retourne l'avant derni�re couleur d�tect�e.
	 * @return couleur2 en taux de rouge vert bleu
	 */
	public String couleur2() {
		return couleur2;
	}
	
	
	/**
	 * M�thode qui prend en param�tre un tableau de String et attrape une exception si la couleur
	 * n'est pas �quivalente � un attribut. La r�f�rence du terrain num�rot� est sur la page Github
	 * du projet. Cette m�thode �tait un autre moyen de trouver la direction du robot.
	 * @param couleur les derni�res couleurs d�tect�es par le robot.
	 * @return char un charact�re du tableau terrain.
	 */
	public char getPos(String[] couleur) {
		return ' ';
	}
	
	/**
	 * M�thode qui devait servir � rappeler les deux derni�res couleurs per�ues par le
	 * capteur de couleurs.
	 * @return tab un tableau de String contenant les couleurs capt�s.
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
	 * M�thode qui devait �tre utilis�e pour pr�venir si le robot est dans un �tat dit
	 * "perdu" mais il est plus efficace d'appeler directement une m�thode de recherche.
	 * @return boolean qui indique true si le robot est perdu.
	 */
	/*
	 public boolean perdu() {
		if(etat=="perdu") {
			return true;
		}
		else return false;
	}
	*/

}
