package iA;

import lejos.hardware.*;

public class HelloWorld
{
    public static void main(String[] args)
    {
    	//affiche Hello World sur la brique du robot.
        System.out.println("Hello World!!");

        //attend que l'on appuye sur un des boutons de la brique pour arreter le programme.
        Button.waitForAnyPress();
    }
}
