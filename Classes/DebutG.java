import lejos.hardware.Button;

/**
 * Classe qui est embarqu� sur l'agent et qui sert � lancer directement le programme une fois
 * le Robot pr�t pour l'action. Cette classe indique que notre robot commence � gauche et qu'on
 * ne connait pas la position de d�part de l'adversaire.
 * @author Toby's group
 *
 */
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
