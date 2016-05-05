

import lejos.nxt.LCD;
import lejos.nxt.UltrasonicSensor;

public final class Mouvements {

	
	public static void avanceDUnTourDeRoue(UltrasonicSensor sonar){
		
		  sonar.ping();
		   int dist = sonar.getDistance();
		   LCD.drawString(Integer.toString(dist),0,0); 
		   if(dist > 10)
		   {
			   Moteur.rotate(360);
		   }
	}
	
	public static void reculeDUnTourDeRoue(){
		Moteur.rotate(-360);
	}
	
	public static int Calibrage(UltrasonicSensor sonar)
	{
		int nbrotations = 0;
		 sonar.ping();
		   int dist = sonar.getDistance();
		   LCD.drawString(Integer.toString(dist),0,0); 
		   while (dist > 10)
		   {
			   Moteur.rotate(360);
			   nbrotations++;
			   sonar.ping();
			   dist = sonar.getDistance();
			   LCD.drawString(Integer.toString(dist),0,0); 
		   }
		   
		return nbrotations;
	}
	
	
	
	/* Avance d'une case */
	//Tester avec un nombre de tacho count (ralentissement aussi pour s'approcher de la valeur qui va bien?)
	public static int avanceDUneCase(UltrasonicSensor sonar, int nbrot){
		Moteur.resetTachoCount();
		 sonar.ping();
		   int dist = sonar.getDistance();
		   LCD.drawString(Integer.toString(dist),0,0); 
		   if(dist > 10)
		   {
			   Moteur.rotate(360*nbrot);
		   }
		return Moteur.gauche().getTachoCount();
		
	}
	
	public static void avance(){
		Moteur.forward();
	}
	
	public static void recule(){
		Moteur.backward();
	}
	
	public static void stop(){
		Moteur.stop();
	}
	
	//Teter un algo où la vitesse de rotation des roues diminue au cours du temps pour s'approcher du tachocount nécessaire au 90°
	
	public static void tournerGauche()
	{
		Moteur.resetTachoCount();
		int i = 0;
		while(i<445)
		{
			i = Moteur.droite().getTachoCount();
			//Moteur.setSpeed(550 - i);
			Moteur.gauche().backward();
			Moteur.droite().forward();
			//LCD.drawInt(i, 0, 0);
		}
		Moteur.stopD();
		
	}
	
	public static void tournerDroite()
	{
		Moteur.resetTachoCount();
		int i = 0;
		while(i<450)
		{
			i = Moteur.gauche().getTachoCount();
			Moteur.droite().backward();
			Moteur.gauche().forward();
			//LCD.drawInt(i, 0, 0);
		}
		Moteur.stopD();
	}
	
	public static void DemiTour()
	{
		Moteur.resetTachoCount();
		int i = 0;
		while(i<890)
		{
			i = Moteur.droite().getTachoCount();
			Moteur.droite().forward();
			Moteur.gauche().backward();
			LCD.drawInt(i, 0, 0);
		}
		Moteur.stop();
	}
}
