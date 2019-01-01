package org.usfirst.frc.team6520.robot.subsystems;

//import org.usfirst.frc.team6520.robot.PIDControl;
import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_AutoDrive extends Subsystem {
	
	int speed = 0;
//	public static PIDController pid = new PIDController(1, 0, 15, RobotMap.gyro, RobotMap.left);
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
//    public void rotate(double angle){
//    	pid.reset();
//    	pid.setInputRange(-180, 180);
//    	pid.setOutputRange(-1, 1);
//    	pid.setPercentTolerance(3);
//    	pid.setSetpoint(angle);
//    	pid.enable();
//    	
//    	int curAng = (int) RobotMap.gyro.getAngle();
//    	int target = (int) angle;
////    	pid.setSetpoint(0);
//    	
////    	do {
////    		int dir = 1;
////    		if (target > curAng){
////    			dir = -1;
////    		}
////    		double val = pid.performPID(Math.abs(target - curAng));
////    		RobotMap.left.set(dir*val);
////    		RobotMap.right.set(dir*val);
////    		
////    		curAng = (int) RobotMap.gyro.getAngle();
////    		
////    	} while (Math.abs(target - curAng) > 10);
//    	while (!pid.onTarget()) {
//    		RobotMap.left.set(pid.get());
//    		RobotMap.right.set(-pid.get());
//    	}
//    	pid.disable();
//    	RobotMap.left.set(0);
//    	RobotMap.right.set(0);
//    }
    
    public void onlyP (double P, double angle){
    	double accErr = 0.4;
    	double curAng = RobotMap.gyro.getAngle();
    	
    	while (Math.abs(angle - curAng) > accErr){
    		curAng = RobotMap.gyro.getAngle();
    		double speed = (angle - curAng)*P;
    		RobotMap.left.set(speed);
    		RobotMap.right.set(speed);
    	}
    	
    }
}

