package ia;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);
		// TODO Auto-generated method stub
		motorC.forward();
		motorC.setPower(100);
		Delay.msDelay(1000);
		motorC.close();
	}

}
