package ia;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
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
	
}
