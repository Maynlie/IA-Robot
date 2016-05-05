package com.tpmif14.robot26;

import java.io.PrintStream;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FusorDetector;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.nxt.NXTRegulatedMotor;
import lejos.util.Delay;
import lejos.nxt.UltrasonicSensor;

public class Robot {
	
	private static NXTRegulatedMotor moteurG = Motor.B;
	private static NXTRegulatedMotor moteurD = Motor.A;	
	private static UltrasonicSensor sonar ;
	int NbTourMoteurG;
	int NbTourMoteurD;

	public static void tournerGauche()
	{
		moteurG.resetTachoCount();
		moteurD.resetTachoCount();
		int i = 0;
		while(i<455)
		{
			i = moteurD.getTachoCount();
			moteurD.forward();
			moteurG.backward();
			LCD.drawInt(i, 0, 0);
		}
		moteurD.stop();
		moteurG.stop();
	}
	
	public static void DemiTour()
	{
		moteurD.resetTachoCount();
		moteurG.resetTachoCount();
		int i = 0;
		while(i<930)
		{
			i = moteurD.getTachoCount();
			moteurD.forward();
			moteurG.backward();
			LCD.drawInt(i, 0, 0);
		}
		moteurD.stop();
		moteurG.stop();
	}


	public static void main(String[] args){
		Button.waitForAnyPress();
		Mouvements.tournerGauche();
		Button.waitForAnyPress();
		sonar = new UltrasonicSensor(SensorPort.S3);
		sonar.capture();
		 LCD.drawString("Calibrage",0,0);
		Button.waitForAnyPress();
		
		int nbrot = Mouvements.Calibrage(sonar);
		 sonar.ping();
		  int dist = sonar.getDistance();
		LCD.drawString(Integer.toString(dist),0,0); 
		
		// LCD.drawString(Calibr,0,0);
		  Button.waitForAnyPress();
			
		
		  
		boolean fin = false;
		
		  /*FeatureListener fl = new ObjectDetector();
		  UltrasonicSensor s = new UltrasonicSensor(SensorPort.S1);
		  FeatureDetector d1 = new RangeFeatureDetector(s, 80, 500);
		  FusorDetector fusion = new FusorDetector();
		  fusion.addDetector(d1);
		  fusion.addListener(listener);*/
		  
		  /*
		  Motor.A.forward();
		  Motor.C.forward();
		  Delay.msDelay(1000);
		  Motor.A.forward();
		  Motor.C.backward();
		  Delay.msDelay(1000);*///Si pas de delay stop
		  // tournerGauche();
		  
		//  Mouvements.avanceDUnTourDeRoue();
		  
		  sonar.ping();
		  dist = sonar.getDistance();
		  LCD.drawString(Integer.toString(dist),0,0);
		  
		  
		  while(!fin)
		  {
			
			  Mouvements.tournerGauche();
			  sonar.ping();
			   dist = sonar.getDistance();
			   LCD.drawString(Integer.toString(dist),0,0); 
			   if(dist < 23)
			   {
				Mouvements.tournerDroite();
			   }
			   sonar.ping();
			   dist = sonar.getDistance();
			   LCD.drawString(Integer.toString(dist),0,0); 
			   if(dist < 23)
			   {
				   Mouvements.tournerDroite();
				  
			   } else {
				   Mouvements.avanceDUneCase(sonar, nbrot);
			   }
			   sonar.ping();
			   dist = sonar.getDistance();
			   LCD.drawString(Integer.toString(dist),0,0);
			   if(dist >= 255)
			   {
				   Mouvements.avanceDUneCase(sonar,nbrot);
				   sonar.ping();
				   dist = sonar.getDistance();
				   
				   if(dist >= 255)
				   {
					fin = true;   
				   }
				   
			   }
			   
		  }
		 
		//LCD.drawInt(tacho, 0, 0);
		Button.waitForAnyPress();	//1441
	}

}
