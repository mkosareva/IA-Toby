package iA;

import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import lejos.hardware.Button;
/**
 * @author Utilisateur
 *
 */
public class Actions {

		
			String AttRobot="Attention Robot !";
			String AttPalet="Attention Palet !";
			String AttMur="Attention Mur !";
			// create two motor objects to control the motors.
			UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
			UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
			UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);

			public Actions() {
				// TODO Auto-generated constructor stub
			}

			//final ? surcharge autoris�e? 

			/**
			 * M�thode qui permet d'avancer en activant les deux servomoteurs
			 * pq la m�thode s'arr�te
			 */
			public void avancer() {
				activerServomoteurs();
			}

			public void reculer () {//reculer
				motorA.backward();
				motorB.backward();
				activerServomoteurs();
				Delay.msDelay(500);
				arreter();
			}

			public void demiTour () {//faire demi-tour
				motorA.forward();
				motorB.backward();
				activerServomoteurs();
				Delay.msDelay(500);//500 est � remplacer par une constante � d�finir en test
				arreter();
			}

			public void tourner90 (char gd) {//tourne � gauche
				if(gd=='d') {
					motorA.backward();
					motorB.forward();
					activerServomoteurs();
					Delay.msDelay(500);//500 est � remplacer par une valeur � d�finir en test
					arreter();
				}
				else if(gd=='g') {
					motorA.forward();
					motorB.backward();
					activerServomoteurs();
					Delay.msDelay(500);//500 est � remplacer par une valeur � d�finir en test
					arreter();
				}
			}

			/**
			 * M�thode qui doit tourner le robot d'un angle d�fini � gauche ou � droite
			 * @param gd d�signe la gauche ou la droite
			 * @param angle est l'angle dont le robot doit tourner, l'angle est d�j� converti en temps
			 */
			public void tourner (char gd,int angle) {
				//avancerUneRoue(gd,angle);
				//check le SonicSensor pour savoir si on peut avancer
				//check le ColorSensor pour savoir o� on est � peu pr�s
			}

			public void arreter () {
				// stop motors with brakes on. 
				motorA.stop();
				motorB.stop();
			}

			public void activerServomoteurs () {
				// set motors to 50% power.
				motorA.setPower(50);
				motorB.setPower(50);
			}

			public void esquiverRobot (){

			}

			public void reconnaitre () {
				//if()
			}

			public void ouvrirPinces () {
				motorC.backward();
				motorC.setPower(100);
				Delay.msDelay(1000);
				motorC.close();
			}

			public void fermerPinces () {
				motorC.forward();
				motorC.setPower(100);
				Delay.msDelay(1000);
				motorC.close();
			}
			public void recupererPalet () {

			}
			
			public void trouverLigne () {

			}

			public void test () {

			}

		

	}