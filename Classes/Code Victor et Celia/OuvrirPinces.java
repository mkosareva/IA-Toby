package iA;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class OuvrirPinces {
	public static void main(String[] args) {
		System.out.println("Pinces fermer");
		System.out.println("Press any key to start");

		//commandes importante:
		Button.LEDPattern(4); 
		Sound.beepSequenceUp();
		//fait clignoter des lumi�res vertes et emet un son quand le programme est pr�t � �tre d�marr�.

		//commandes importante:
		Button.waitForAnyPress();
		//permet d'attendre que l'on appuye sur un des boutons du robot pour lancer le programme.


		// cr�er 1 objet pour controler le moteur des pinces.
		UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);

		//forward permet de mettre le sens du moteur de tel sorte � ce que �a les ouvre.
		// ne pas modifier les valeurs de puissance et de temps, elles ont �t� test� pour �tre comme on le veut.
		motorC.backward();

		//met la puissance du moteur � 100%
		motorC.setPower(100);

		//attend 1000 milliseconde avant la prochaine instruction (1 seconde)
		Delay.msDelay(1000);

		motorC.close();

		//bip pour indiquer que le programme est termin�
		Sound.beepSequence();



	}



}
