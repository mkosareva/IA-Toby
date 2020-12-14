import lejos.hardware.Button;

/**
 * Classe qui est embarqué sur l'agent et qui sert à lancer directement le programme une fois
 * le Robot prêt pour l'action. Cette classe indique que notre robot commence au milieu et qu'on
 * ne connait pas la position de départ de l'adversaire.
 * @author Toby's group
 *
 */
public class DebutM extends Strategies {

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
