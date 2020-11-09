package ia;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/*
 * La classe gerant le capteur de pression du robot
 */
public class Capteurs extends EV3TouchSensor{
	EV3UltrasonicSensor port2 = new EV3UltrasonicSensor(SensorPort.S2);
	
	/*
	 */
	public Capteurs(Port port,EV3UltrasonicSensor port2){
		super(port);
		this.port2=port2;
	}
	
	/**
	 * true si le capteur est en position basse, false sinon
	 */
	public boolean isPressed(){
		float[] sample = new float[1];
		fetchSample(sample, 0);
		return sample[0] != 0;
	}

	/**
	 * la distance en metre entre l'obstacle present devant le robot et le robot
	 */
	public float getDistance() {
		SampleProvider distance = this.getMode("Distance");
		float[] sample = new float[distance.sampleSize()];
		distance.fetchSample(sample, 0);
		return sample[0];
	}
	
}
