package iA;

import lejos.hardware.Button;
import lejos.hardware.Sound;

public class Agent {
	Actions act = new Actions();
	Capteurs capt = new Capteurs();

	public void start() {
		Button.LEDPattern(4);     
		Sound.beepSequenceUp();
//programme pour se diriger et le recupérer
		while(Button.ESCAPE.isUp()) {
			act.avancer();
			if(capt.CapterDistance()<0.15) {
				if(capt.CapteurToucher()) {
					act.arreter();
					act.fermerPinces();
				}
				else while(!capt.CapteurToucher()) {
					act.avancer();
				}

			}

		}
	}

}
