package iA;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class DriveSquare
{
	public static void main(String[] args)
	{
		System.out.println("Drive in a Square\nand Stop\n");
		System.out.println("Press any key to start");
		//commandes importante:
		Button.LEDPattern(4);    
		Sound.beepSequenceUp();   
		//fait clignoter des lumières vertes et emet un son quand le programme est prêt à être démarré.

		//commandes importante:
		Button.waitForAnyPress();
		// permet d'attendre que l'on appuye sur un des boutons du robot pour lancer le programme.

		// create two motor objects to control the motors.
		UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
		UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

		//la boucle permet d'effectuer 4 fois les actions écrites dedans afin de faire les 4 cotés du carré.
		for (int i=0; i < 4; i++)
		{
			// set motors to 50% power.
			motorA.setPower(50);
			motorB.setPower(50);

			// wait 2 seconds.
			Delay.msDelay(2000);

			// stop motors with brakes on. 
			motorA.stop();
			motorB.stop();

			// inverse le sens de rotation d'un des deux moteurs (pour que les 2 roues tournent dans le sens opposé
			// et ainsi fassent tourner le robot). /!\ BACKWARD = en arrière; FORWARD= en avant. 
			motorA.backward();
			motorB.forward();

			// make the turn.
			motorA.setPower(50);
			motorB.setPower(50);

			// adjust time to get a 90° turn.
			Delay.msDelay(1500);

			motorA.stop();
			motorB.stop();

			// set right motor back to forward motion.
			motorA.forward();
			motorB.forward();
		}

		// free up motor resources. 
		motorA.close(); 
		motorB.close();

		Sound.beepSequence(); // we are done.
	}
}
