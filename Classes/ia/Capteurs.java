package ia;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/*
 * Cette classe gère le capteur toucher ainsi que le capteur UltraSonicSensor
 */
public class Capteurs {
	EV3TouchSensor cTouche =new EV3TouchSensor(SensorPort.S2);;
	EV3UltrasonicSensor cUltra =new EV3UltrasonicSensor(SensorPort.S4);
	EV3ColorSensor cColor =new EV3ColorSensor(SensorPort.S3);

	private SampleProvider touche;
	private SampleProvider ultra ;
	private SampleProvider color;

	private float[] tabT;
	private float[] tabU;
	private float[] tabC;

	private boolean pincesOpen=true;

	/*
	 * On defini les ports sur lesquels sont branchés les capteurs
	 */

	public Capteurs(Port s2,Port s4, Port s3){
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
	 * Retourne vrai si le capteur toucher est enfoncer
	 */
	public boolean isPressed() {
		float[] sample = new float[1];
		touche.fetchSample(sample, 0);
		return sample[0] != 0;
	}
	/*
	 * Méthode qui prend en paramètre l'état des pinces, ouvertes vs fermées
	 * Le robot commence avec les pinces ouvertes
	 * return un 
	 */
	public void etatPinces(boolean open) {
		pincesOpen=open;
	}

	public boolean getPincesOpen() {
		return pincesOpen;
	}

	/**
	 * Distance restante entre le robot et l'objet detecter
	 */

	float getDistance() {
		cUltra.enable();
		ultra = cUltra.getDistanceMode();
		ultra.fetchSample(tabU, 0);
		return tabU[0];
	}

	public boolean capteurCouleur() {
		// TODO Auto-generated method stub
		return false;
	}
	public String couleur() {
		String couleur = "gris";
		int nb1=0,nb2=0,nb3=0;
		//modifie le capteur de couleur pour lui faire capter le taux de rouge, bleu et vert présent dans la couleur détecté
		cColor.getRGBMode();
		cColor.setFloodlight(Color.WHITE);

		//initialise une couleur appelée "rgb"
		SensorMode rgb;
		/*Le programme tournera jusqu'à ce qu'on appuye sur le bouton "echap" en haut à gauche de la brique.
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

	public void esquiverRobot (){
		
	}

	public SampleProvider reconnaitreRobot() {
		cUltra.enable();
		ultra = cUltra.getListenMode();
		//ultra.fetchSample(tabUl, 0);
		return ultra;
	}
}