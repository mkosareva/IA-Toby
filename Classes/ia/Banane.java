package ia;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class Banane {

	public static void main(String []a){
		Actions test=new Actions (MotorPort.A, MotorPort.B, MotorPort.C);
		//Capteurs cap=new Capteurs(SensorPort.S2,SensorPort.S4,SensorPort.S3);
		
		//Button.waitForAnyPress();
		//while(!Button.ESCAPE.isUp()) { // ou isDown ?
		//Capteurs test1=new Capteurs();

		//test.orienter(90); //=> ok
		//test.avancer(300, 100); //=> ok 
		//test.reculer(100, 100); //=> ok 
		test.fermerPinces();
		//test.fermerPinces();
		//test.ouvrirPinces();
		//System.out.println("salut");
		//test.get2PremierPaletPourUnAvantageDeFou();
		/*if(!capteur.isPressed()) {
			test.avancer(400,1000);
		}
		 */
		//test.avancer(400, 1000);
		//test.orienter(90);
		//test.avancer(400, 2000);
		//System.out.println(cap.getDistance());
		//Delay.msDelay(3000);
		//test.rechercher();
		//Strategies strat=new Strategies();
		//strat.deposerPalet();	//}
		//strat.s1_debutG();
	}
}
