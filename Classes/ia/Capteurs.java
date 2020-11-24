package ia;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;
import lejos.robotics.SampleProvider;

/*
 * Cette classe g�re le capteur toucher ainsi que le capteur UltraSonicSensor
 */
public class Capteurs {
	EV3TouchSensor port = new EV3TouchSensor(SensorPort.S2);
	EV3UltrasonicSensor port2 = new EV3UltrasonicSensor(SensorPort.S4);
	EV3ColorSensor port3 = new EV3ColorSensor(SensorPort.S3);
	private boolean pincesOpen=true;
	private SampleProvider sp=port.getTouchMode();
	/*
	 * On defini les ports sur lesquels sont brancher les capteurs
	 */

	/*public Capteurs(Port s2,Port s4, Port s3){
		this.port=s2;
		this.port2=s4;
		this.port3=s3;
	}
	*/

	/**
	 * Retourne vrai si le capteur toucher est enfoncer
	 */
	public boolean isPressed() {
        float[] sample = new float[1];
        sp.fetchSample(sample, 0);
        return sample[0] != 0;
	}
	/*
	 * M�thode qui prend en param�tre l'�tat des pinces, ouvertes vs ferm�es
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
	public float getDistance() {
		SampleProvider distance = port2.getMode("Distance");
		float[] sample = new float[distance.sampleSize()];
		distance.fetchSample(sample, 0);
		return sample[0];
	}

	public String CapteurCouleur() {
		// TODO Auto-generated method stub
		return null;
	}
	public String couleur() {
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
		String couleur = null;
		int nb1,nb2,nb3;
		//modifie le capteur de couleur pour lui faire capter le taux de rouge, bleu et vert pr�sent dans la couleur d�tect�
		color.getRGBMode();
		color.setFloodlight(Color.WHITE);

		//initialise une couleur appel�e "RGB"
		Color rgb;
		/*Le programme tournera jusqu'� ce qu'on appuye sur le bouton "echap" en haut � gauche de la brique.
		 * ou qu'il capte du blanc */
		while (Button.ESCAPE.isUp() || couleur != "blanc" ){
			rgb = ((ColorDetector)color).getColor();
			nb1=rgb.getRed();
			nb2=rgb.getGreen();// 34
			nb3=rgb.getBlue(); //17
			if((38<=nb1 && nb1<=42) && (32<=nb2 && nb2<=36) && (15<=nb3 && nb3<=19)) {
				couleur="blanc";
			}
		}

		return couleur;
	}

}