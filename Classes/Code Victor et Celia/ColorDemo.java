package iA;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;
import library.ColorSensor;
import library.Lcd;

public class ColorDemo
{
	public static void main(String[] args)
	{
		//créer un objet capteur de couleur pour le controler.
		ColorSensor    color = new ColorSensor(SensorPort.S3);

		//affiche "color demo" sur la brique du robot.
		System.out.println("Color Demo");
		Lcd.print(2, "Press to start");

		//commandes importante:
		Button.LEDPattern(4);  
		Sound.beepSequenceUp();
		//fait clignoter des lumières vertes et emet un son quand le programme est prêt à être démarré.


		Button.waitForAnyPress();
		// permet d'attendre que l'on appuye sur un des boutons du robot pour lancer le programme.


		Button.LEDPattern(0);
		//eteint les led de la brique.

		//Le programme tournera jusqu'à ce qu'on appuye sur le bouton "echap" en haut à gauche de la brique.
		while (Button.ESCAPE.isUp())
		{
			Lcd.clear(4);
			//permet d'afficher sur la 4eme ligne de la console (brique) la luminosité ambiente
			//met à jour l'affichage toutes les 250 millisecondes
			Lcd.print(4, "ambient=%.3f", color.getAmbient());
			Delay.msDelay(250);
		}
		//attend 1seconde avant les prochaines instructions
		Delay.msDelay(1000);

		//modifie le capteur de couleur pour ne lui faire capter plus que le niveau de rouge
		color.setRedMode();
		color.setFloodLight(Color.RED);
		color.setFloodLight(true);

		while (Button.ESCAPE.isUp())
		{
			Lcd.clear(5);
			//permet d'afficher sur la 5eme ligne de la brique le niveau de rouge détecté (affiche un nombre entre 0 et 255, 0 étant pas de rouge du tout)
			//met à jour l'affichage toute les 250ms
			Lcd.print(5, "red=%.3f", color.getRed());
			Delay.msDelay(250);
		}

		Delay.msDelay(1000);

		//modifie le capteur de couleur pour lui faire capter le taux de rouge, bleu et vert présent dans la couleur détecté
		color.setRGBMode();
		color.setFloodLight(Color.WHITE);

		//initialise une couleur appelée "RGB"
		Color rgb;

		while (Button.ESCAPE.isUp())
		{
			rgb = color.getColor();

			//affiche sur la 6eme ligne de la brique respectivement le taux de rouge, vert et bleu présent dans la couleur
			Lcd.clear(6);
			Lcd.print(6, "r=%d g=%d b=%d", rgb.getRed(), rgb.getGreen(), rgb.getBlue());
			Delay.msDelay(250);
		}

		Delay.msDelay(1000);

		//met le capteur de couleur dans un nouveau "mode" afin de lui faire détecter automatiquement la couleur présenté.
		color.setColorIdMode();
		color.setFloodLight(false);

		while (Button.ESCAPE.isUp())
		{
			//affiche le nom de la couleure détéctée sur la 7eme ligne de la brique.
			Lcd.clear(7);
			Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
			Delay.msDelay(250);
		}

		// free up resources.
		color.close();

		Sound.beepSequence();    // we are done.

		Button.LEDPattern(4);
		Button.waitForAnyPress();
	}

}
