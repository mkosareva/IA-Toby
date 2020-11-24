package ia;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;

public class Strategies {

	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
	UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);
	

	EV3UltrasonicSensor CaptDist = new EV3UltrasonicSensor(SensorPort.S2);

	float distance;

	Actions test=new Actions (MotorPort.A, MotorPort.B, MotorPort.C);

	public Strategies() {
		// TODO Auto-generated constructor stub
	}

	public void getPremierPalet() {

		if(etatPinces==false) {
			ouvrirPinces();
		}

		while(!Touch.isPressed()) {
			avancer();
		}
		arreter();

		fermerPinces();

		tourner90('g');

		avancer();

		Delay.msDelay(1500);

		arreter();

		tourner90('d');

		while(couleur!="blanc") {
			avancer();
		}

		arreter();

		ouvrirPinces();

		break;
	}

	public void chercherEtRecupererUnPalet() {

		if(etatPinces==false) {
			ouvrirPinces();
		}

		distance=CaptDist.getDistance();
		
		//il faudrait faire une méthode tourner(char,float) qui indique dans quel sens le robot va tourner et pendant combien de temps
		// dans ce while, on le fait tourner jusqu'à ce qu'il capte un élément dans une distance donnée
		while(distance>.15 && distance<.6) {
			tourner('d',(int) 2);
			Delay.msDelay(500);
			distance=CaptDist.getDistance();
		}
		//le problème c'est qu'il sera pas totalement en face du palet pour aller le chercher, il faut ajuster l'angle, d'où recommencer
		//à tourner un peu après que l'élément soit rentré en ligne de vue.
		tourner((int) 2);
		
		//une fois que le robot est bien en face, on le fait avancer jusqu'à ce que le bouton de toucher soit poussé
		//on prévoit le cas où si le robot parcours une trop grande distance c'est que le palet n'est pas atteint/qu'il est dans un mur
		//et du coup on recommence la méthode

		while(!Touch.isPressed()) {
			if(distanceParcourue>20) {
				chercherEtRecupererUnPalet();
			}
			avancer();
			Delay.msDelay(1500);
			distanceParcourue+=5;
		}
		
		arreter();

		fermerPinces();
		
		//faire la suite, comment on sait qu'il est dans le bon sens pour repartir dans le camp ennemi ?


	}

}
