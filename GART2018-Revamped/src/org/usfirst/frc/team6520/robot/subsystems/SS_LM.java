package org.usfirst.frc.team6520.robot.subsystems;

import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class SS_LM extends PIDSubsystem {

    // Initialize your subsystem here
	public SS_LM(double p, double i, double d) {
		super(p, i, d);
		getPIDController().setAbsoluteTolerance(0);
		getPIDController().setInputRange(0, 360);
		getPIDController().setOutputRange(-0.5, 0.5);
		getPIDController().setContinuous(true);
//		LiveWindow.addActuator("Drive Train", "PIDSubsystem Controller", getPIDController());
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
//		RobotMap.left.pidWrite(output);
		RobotMap.right.pidWrite(output);
	}
	public double getGyroAngle(){
        return RobotMap.gyro.getAngle();
    }
    public void resetGyro(){
    	RobotMap.gyro.reset();
    }
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage
        return RobotMap.gyro.getAngle();
    }
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
