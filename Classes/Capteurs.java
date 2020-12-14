import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/**
 * Cette classe contient toutes les m�thodes qui doivent permettre � notre agent
 * d'�voluer en fonction de son environnement. Pour cela on utilise 3 capteurs ;
 * 1 capteur de touch� qui renvoie true ou flase - 1 capteur de couleur qui retourne
 * ????? - 1 capteur ultrasonique qui permet retourner une distance ou un bool�en si
 * il d�tecte un autre robot. Les attributs de
 * type SampleProvider permettent de r�cup�rer les valeurs des capteurs. On se
 * propose de mettre ces valeurs dans des tableaux.
 * @author Toby's group
 *
 */
public class Capteurs {
	/**
	 * Les trois capteurs
	 */
	EV3TouchSensor cTouche =new EV3TouchSensor(SensorPort.S2);;
	EV3UltrasonicSensor cUltra =new EV3UltrasonicSensor(SensorPort.S4);
	EV3ColorSensor cColor =new EV3ColorSensor(SensorPort.S3);
	
	/**
	 * Les trois SampleProvider
	 */
	private SampleProvider touche;
	private SampleProvider ultra ;
	private SampleProvider color;
	
	/**
	 * Les tableaux des capteurs, qui permettent l'acc�s direct aux valeurs
	 * des capteurs.
	 */
	private float[] tabT;
	private float[] tabU;
	private float[] tabU2;
	private float[] tabC;

	private boolean pincesOpen=true;

	/**
	 * Constructeur de la classe qui permet d'initialiser les attributs
	 * d'instance, soit les SampleProvider et les tableaux.	
	 * @param s2
	 * @param s4
	 * @param s3
	 */
	public Capteurs(){
		/**cTouche=new EV3TouchSensor(s2);
		cUltra=new EV3UltrasonicSensor(s4);
		cColor=new EV3ColorSensor(s3);*/

		touche=cTouche.getTouchMode();
		ultra= cUltra.getMode("Distance");
		color=cColor.getRGBMode();

		tabT = new float [touche.sampleSize()];
		tabU = new float [ultra.sampleSize()];
		tabC = new float [color.sampleSize()];
	} 

	/**
	 * M�thode qui indique l'�tat du capteur de touch�.
	 * @return true ou false selon l'�tat du capteur
	 */
	public boolean isPressed() {
		touche.fetchSample(tabT, 0);
		return tabT[0] != 0;
	}

	/**
	 * M�thode qui prend en param�tre l'�tat futur des pinces, ouvertes vs ferm�es
	 * Le robot commence avec les pinces ferm�es. 
	 * @param open
	 */
	public void etatPinces(boolean etat) {
		if(pincesOpen==etat) return;
		else pincesOpen=etat;
	}

	/**
	 * Getter simple de l'�tat des pinces.
	 * @return un bool�en qui permet de connaitre l'�tat des pinces
	 */
	public boolean getPincesOpen() {
		return pincesOpen;
	}


	/**
	 * M�thode qui retourne la distance d�tect�e par le capteur ultrasonique
	 * @return float la valeur en cm s�parant le robot de l'objet detect�
	 */
	public float getDistance() {
		cUltra.enable();
		ultra = cUltra.getDistanceMode();
		ultra.fetchSample(tabU, 0);
		return tabU[0];
	}

	
	/**
	 * M�thode qui utilise le capteur de couleur pour mesurer le taux de rouge vert et bleu.
	 * Cette m�thode devait aussi associer la mesure du capteur � une des couleurs d�finies dans
	 * les attributs.
	 * @return couleur la couleur detect�e par le capteur.
	 */
	public String getCouleur() {
		String couleur = "gris";
		int nb1=0,nb2=0,nb3=0;
		//modifie le capteur de couleur pour lui faire capter le taux de rouge, bleu et vert pr�sent dans la couleur d�tect�
		cColor.getRGBMode();
		cColor.setFloodlight(Color.WHITE);

		//initialise une couleur appel�e "rgb"
		SensorMode rgb;
		/*Le programme tournera jusqu'� ce qu'on appuye sur le bouton "echap" en haut � gauche de la brique.
		 * ou qu'il capte du blanc */
		while (Button.ESCAPE.isUp() || couleur != "blanc" ){
			rgb = cColor.getColorIDMode();
			nb1=((Color) rgb).getRed(); // 40
			nb2=((Color) rgb).getGreen();// 34
			nb3=((Color) rgb).getBlue(); //17
			/*if((38<=nb1 && nb1<=42) && (32<=nb2 && nb2<=36) && (15<=nb3 && nb3<=19)) {
				couleur="blanc";
			}*/
			if((0<=nb1 && nb1<=100) && (0<=nb2 && nb2<=100) && (0<=nb3 && nb3<=100)) {
				couleur="blanc";
			}

		}
		System.out.println(couleur+" / "+nb1+ " / "+nb2+" / "+nb3);
		Delay.msDelay(3000);
		return couleur;
	}

	/**
	 * M�thode non r�alis�e qui devait permettre de ne pas percuter 
	 * un robot apr�s l'avoir d�tect�.
	 */
	public void esquiverRobot (){
		
	}

	/**
	 * M�thode qui utilise le mode "�coute" du capteur ultrasonique pour
	 * reconnaitre le robot adverse et stocke la valeur dans un tableau.
	 * @return float de la valeur du SampleProvider du capteur, si la m�thode
	 * retourne 1 c'est qu'un robot est d�tect�.
	 */
	public float reconnaitreRobot() {
		cUltra.enable();
		ultra = cUltra.getListenMode();
		ultra.fetchSample(tabU2, 0);
		return tabU2[0];
	}
}