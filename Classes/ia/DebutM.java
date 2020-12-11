package ia;

import lejos.hardware.Button;

public class DebutM {

	public static void main(String[] args) {
		Strategies strat=new Strategies();
		Button.ENTER.waitForPressAndRelease();
		for(;Button.ESCAPE.isUp();) {
			strat.s2_debutM();
			if (!Button.ESCAPE.isUp()) {
				break;
			}
		}
		strat.stop();

	}

}
