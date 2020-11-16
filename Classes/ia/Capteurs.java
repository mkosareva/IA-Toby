package ia;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/*
 * Cette classe gère les capteurs de toucher, sonic et de couleurs
 */
public class Capteurs extends BaseSensor {
	EV3UltrasonicSensor port1;
	EV3TouchSensor port2;
	EV3ColorSensor port3;
	
	/*
	 * On definit les ports sur lesuqles ont brancher les capteurs
	 */
	public Capteurs(EV3UltrasonicSensor port1){
		this.port1=port1;
	}
	
	public Capteurs(EV3TouchSensor port2){
		this.port2=port2;
	}
	
	public Capteurs(EV3ColorSensor port3){
		this.port3=port3;
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
	 * Distance restante entre le robot et l'objet detecté
	 */
	public float getDistance() {
		SampleProvider distance = port1.getDistanceMode();
		float[] sample = new float[distance.sampleSize()];
		distance.fetchSample(sample, 0);
		return sample[0];
	}
	
	public String getColor() {
		return "";
	}
	
}
