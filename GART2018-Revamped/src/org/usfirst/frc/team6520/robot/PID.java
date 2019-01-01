package org.usfirst.frc.team6520.robot;

public class PID {
	double kP, kI, kD;
	
	public PID (double kP, double kI, double kD, double input, double output){
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}
	
	double P = 0, I = 0, D = 0, prevD = 0, transfer;
	double setpoint;
	double error;
	boolean enabled = true;
	double acceptableError = 0.2;
	
	public double PIDloop(){
		while (Math.abs(error) < acceptableError){
			error = setpoint - input;
			P = error * kP;
			I += error * kI;
			transfer = D;
			D = (D - prevD)/20 * kD;
			prevD = transfer;
		}
		return (P + I + D);
	}
	
}
