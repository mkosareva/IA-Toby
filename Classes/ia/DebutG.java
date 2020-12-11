package ia;

import lejos.hardware.Button;
import lejos.hardware.KeyListener;

public class DebutG extends Strategies {

	public static void main(String[] args) {
		Strategies strat=new Strategies();
		Button.ENTER.waitForPressAndRelease();
		for(;Button.ESCAPE.isUp();) {
			strat.s1_debutG();
			if (Button.ESCAPE.isUp()) {
				break;
			}
		}
		strat.stop();
	}
	
}
