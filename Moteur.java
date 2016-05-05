package com.tpmif14.robot26;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public final class Moteur {

	// Mouvements mouvG, mouvD, avancer, reculer;
	
	private static NXTRegulatedMotor moteurG = Motor.C;
	
	private static NXTRegulatedMotor moteurD = Motor.A;
	
	public static NXTRegulatedMotor gauche(){
		return moteurG;
	}
	
	public static NXTRegulatedMotor droite(){
		return moteurD;
	}
	
	public static void forward(){
		setSpeed(50);
		moteurG.forward();
		moteurD.forward();
	}
	
	public static void backward(){
		setSpeed(50);
		moteurG.backward();
		moteurD.backward();
	}
	
	public static void stop(){
		moteurG.stop();
		moteurD.stop();
	}
	
	public static void stopD(){
		moteurG.stop();
		moteurD.stop();
	}
	
	public static void stopG(){
		moteurD.stop();
		moteurG.stop();
	}
	
	public static void rotate(int angle){
		moteurG.rotate(angle, true);
		moteurD.rotate(angle);
	}
	
	public static void resetTachoCount(){
		moteurG.resetTachoCount();
		moteurD.resetTachoCount();
	}
	
	public static void setSpeed(int s)
	{
		moteurG.setSpeed(s);
		moteurD.setSpeed(s);
	}

}
