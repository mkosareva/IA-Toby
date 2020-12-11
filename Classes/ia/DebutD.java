package ia;

import lejos.hardware.Button;

public class DebutD extends Strategies {

	public static void main(String[] args) {
		Strategies strat=new Strategies();
		Button.ENTER.waitForPressAndRelease();
		for(;Button.ESCAPE.isUp();) {
			strat.s3_debutD();
			if (!Button.ESCAPE.isUp()) {
				break;
			}
		}
		strat.stop();

	}




}
