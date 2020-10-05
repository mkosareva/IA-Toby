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

		Button.LEDPattern(4);     // flash green led and
		Sound.beepSequenceUp();   // make sound when ready.

		Button.waitForAnyPress();

		UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);
		motorC.forward();

		motorC.setPower(100);

		Delay.msDelay(1000);
		
		motorC.close();

		Sound.beepSequence();



	}



}
