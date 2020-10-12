import java.awt.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;
/**
 * @author Utilisateur
 *
 */
public class Actions {

	public Actions() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * permet d'avancer en activant les deux servomoteurs
	 */
	public void avancer () {
		System.out.println("Drive Forward\nand Stop\n");
        System.out.println("Press any key to start");

        Button.LEDPattern(4);     // flash green led and
        Sound.beepSequenceUp();   // make sound when ready.

        Button.waitForAnyPress();

        // create two motor objects to control the motors.
        UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
        UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

        // set motors to 50% power.
        motorA.setPower(50);
        motorB.setPower(50);

        // wait 2 seconds.
        Delay.msDelay(2000);

        // stop motors with brakes on. 
        motorA.stop();
        motorB.stop();

        // free up motor resources. 
        motorA.close(); 
        motorB.close();
 
        Sound.beepSequence(); // we are done.
	}

	public void reculer () {

	}

	public void avancerUneRoue () {

	}

	public void reculerUneRoue () {

	}
	public void tourner () {

	}
	public void arreter () {

	}

	public void activerServomoteurs () {

	}

	public void esquiverRobot (){

	}

	public void reconnaitre () {

	}
	public void ouvrirPinces () {

	}
	public void fermerPinces () {

	}
	public void recupererPalet () {

	}
	public void trouverLigne () {

	}
	
	 public void test () {
		 
	 }

}
