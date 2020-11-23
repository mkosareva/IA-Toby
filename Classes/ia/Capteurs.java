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
 * Cette classe gère le capteur toucher ainsi que le capteur UltraSonicSensor
 */
public class Capteurs extends EV3TouchSensor {
	EV3TouchSensor port = new EV3TouchSensor(SensorPort.S3);
	EV3UltrasonicSensor port2 = new EV3UltrasonicSensor(SensorPort.S2);

	/*
	 * On defini les ports sur lesquels sont brancher les capteurs
	 */

	public Capteurs(Port port,EV3UltrasonicSensor port2){
		super(port);
		this.port2=port2;
	}

	/**
	 * Retourne vrai si le capteur toucher est enfoncer
	 */
	public boolean isPressed(){
		float[] sample = new float[1];
		fetchSample(sample, 0);
		return sample[0] != 0;
	}

	/**
	 * Distance restante entre le robot et l'objet detecter
	 */
	public float getDistance() {
		SampleProvider distance = this.getMode("Distance");
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
		//modifie le capteur de couleur pour lui faire capter le taux de rouge, bleu et vert présent dans la couleur détecté
		color.getRGBMode();
		color.setFloodlight(Color.WHITE);

		//initialise une couleur appelée "RGB"
		Color rgb;
		/*Le programme tournera jusqu'à ce qu'on appuye sur le bouton "echap" en haut à gauche de la brique.
		 * ou qu'il capte du blanc */
		while (Button.ESCAPE.isUp() && couleur != "blanc" ){
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