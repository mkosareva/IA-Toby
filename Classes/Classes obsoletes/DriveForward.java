package iA;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class DriveForward
{
	public static void main(String[] args)
	{
		System.out.println("Drive Forward\nand Stop\n");
		System.out.println("Press any key to start");

		//commandes importante:
		Button.LEDPattern(4);     
		Sound.beepSequenceUp();   
		//fait clignoter des lumi�res vertes et emet un son quand le programme est pr�t � �tre d�marr�.

		// commande importante : 
		Button.waitForAnyPress();
		// permet d'attendre que l'on appuye sur un des boutons du robot pour lancer le programme.

		// cr�er 2 objets pour controler les moteurs.
		UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
		UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

		// met la puissance des moteurs � 50% de leur puissance maximale jusqu'� ce qu'il soit indiqu� de les arr�ter.
		//Plus pratique de travailler selon la puissance maximale des moteurs que de faire de la physique-chimie :)
		motorA.setPower(50);
		motorB.setPower(50);

		// Permet d'attendre 2000 millisecondes (2secondes) avant la prochaine instruction.
		//Cette commande prend toujours des valeurs >0
		Delay.msDelay(2000);

		// Indique d'arr�ter les moteurs en marche. 
		motorA.stop();
		motorB.stop();

		//Ferme les moteurs pour lib�rer des ressources en m�moire :)
		motorA.close(); 
		motorB.close();

		Sound.beepSequence(); // we are done.
	}
}
