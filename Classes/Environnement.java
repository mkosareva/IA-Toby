package iA;
import iA.Capteurs;

public class Environnement {
	//classe qui actualise les données, besoin d'un tableau
	int nombrePalets=9;
	int nbPoint=0;
	final static String[] terrain = new String[] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R"};
	String position;
	String etat;
	String couleur1,couleur2;

	
	public Environnement() {
		// TODO Auto-generated constructor stub
	}
	
	public int points () {
		this.nbPoint+=1;
		this.nombrePalets-=1;
		return nbPoint;
	}
	
	public boolean perdu() {
		if(etat=="perdu") {
			return true;
		}
		else return false;
	}
	
	/*public void avancer (cm) {
		activerServomoteurs();
		Delay.msDelay(cm);// utiliser un paramètre à la place de 2000
		if(distance<paramètreFinalADefinir) {
			motorA.stop(); //motor A s'arrête
			motorB.stop(); //motor A s'arrête
			if() {//si robot
				//faire action X
				return AttRobot;
			}
			else if( ) {//si palet
				//faire action Y
				return AttPalet;
			}
			else if (){//si mur
				//faire action Z
				return AttRobot;
			}
			else () {//si ligne blanche
				//faire action B
				return "Ligne blanche";
			}
		}
		return null;
	}*/

	
	 
	
	public String getPos() {
		//àfinir
	/*if(couleur1=="jaune"&&couleur2=="vert"||couleur1=="jaune"&&couleur2=="noir"||couleur1=="jaune"&&couleur2=="vert") {
		position="B";
	}*/
		return null;
	}
	
	
	
	public void getCouleur() {
		//permet de recup les 2 dernières couleur sur lesquels on est passé, couleur1 est la plus récente
		// et couleur2 la plus vieille
		Capteurs Captcouleur = new Capteurs();
		if(couleur1==null) {
		couleur1=Captcouleur.CapteurCouleur();
		}
		else {
			couleur2=couleur1;
			couleur1=Captcouleur.CapteurCouleur();
		}
		
	}
	
	
	

}
