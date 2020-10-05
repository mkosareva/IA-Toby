package iA;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;

public class actions {
	 static UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
     static UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
     
	public actions() {
		
		
	}
	
	
	
	public void tournergauche90(){
		motorA.backward();
        motorB.forward();
		
		motorA.setPower(50);
        motorB.setPower(50);
     // adjust time to get a 90° turn.
        Delay.msDelay(1500);

        motorA.stop();
        motorB.stop();
	}
	
	public void tournerdroite90(){
		motorA.forward();
        motorB.backward();
        
		motorA.setPower(50);
        motorB.setPower(50);
     // adjust time to get a 90° turn.
        Delay.msDelay(1500);

        motorA.stop();
        motorB.stop();
	}
	
	public static void demitour(){
		motorA.forward();
        motorB.backward();
        
		motorA.setPower(50);
        motorB.setPower(50);
     // adjust time to get a 90° turn.
        Delay.msDelay(530);

        motorA.stop();
        motorB.stop();
	}
	
	public static void main(String[] args)
    {
		Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        Button.waitForAnyPress();
        
        demitour();
    }
	

}
