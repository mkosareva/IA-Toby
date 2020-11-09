

import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import library.ColorSensor;
import library.TouchSensor;
import library.UltraSonicSensor;

/**
 * 
 * @author Utilisateur
 *
 */
public class Capteurs {
	TouchSensor touch = new TouchSensor(SensorPort.S1);
	UltraSonicSensor ultra = new UltraSonicSensor(SensorPort.S4);
	ColorSensor    colorsensor = new ColorSensor(SensorPort.S3);
	float distance;
	boolean toucher;
	String couleur;


	public Capteurs() {
		// TODO Auto-generated constructor stub
	}


	public float CapterDistance (){
		distance=ultra.getRange();
		return distance;
	}

	public boolean AutreRobot() {
		return ultra.getListen();
	}

	/**
	 * @return boolean
	 */
	public boolean CapteurToucher () {
		return touch.isPressed();
	}

	/**
	 * @return String
	 */
	public String CapteurCouleur () {
		Color rgb = null;
		colorsensor.setRGBMode();
		colorsensor.setFloodLight(Color.WHITE);
		int red= rgb.getRed();
		int green = rgb.getGreen();
		int blue = rgb.getBlue();
		if(red<=3 && red>=1 && green<=3 && green>=1 && blue<=2 && blue>=0) {
			return "noir";
		}
		else if(red<=13 && red>=11 && green<=13 && green>=11 && blue<=7 && blue>=8) {
			return "gris";
		}
		else if(red<=3 && red>=1 && green<=3 && green>=1 && blue<=2 && blue>=0) {
			return "blanc";
		}
		
		else if(red<=3 && red>=1 && green<=3 && green>=1 && blue<=2 && blue>=0) {
			return "jaune";
		}
		
		else if(red<=3 && red>=1 && green<=3 && green>=1 && blue<=2 && blue>=0) {
			return "vert";
		}
		
		else if(red<=3 && red>=1 && green<=3 && green>=1 && blue<=2 && blue>=0) {
			return "bleu";
		}
		
		else return "gris";



	}







}
