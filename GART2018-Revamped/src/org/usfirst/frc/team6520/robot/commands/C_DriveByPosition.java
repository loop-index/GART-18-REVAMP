package org.usfirst.frc.team6520.robot.commands;

import org.usfirst.frc.team6520.robot.Robot;
import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_DriveByPosition extends Command {
    double startAngle;
    boolean state = false;
    double distance;
    int mode;
    double maxSide;
    char switchPos;
    char sideSensor;
    
	public C_DriveByPosition(double d, double s, double a, int m, char ss) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		this.distance = d * 1000;
		this.maxSide = s;
		this.startAngle = a;
		this.mode = m;
		this.sideSensor = ss;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Sensors.updateVal();
//    	double dL = Sensors.dLKF;
//    	double dR = Sensors.dRKF;
//    	double dSL = Sensors.dSLKF;
//    	double dSR = Sensors.dSRKF;
    	double dL = RobotMap.ultra_behind_left.getRangeMM();
    	double dR = RobotMap.ultra_behind_right.getRangeMM();
    	double dSR = RobotMap.ultra_right.getRangeMM();
    	double dSL = RobotMap.ultra_left.getRangeMM();
    	
	    if (sideSensor == 'R') {	
			if ((dL > distance || dR > distance) && (dSR > 4 && dSR <= maxSide)) {
	    		RobotMap.ss_Drivebase.stop();
	    		state = true;
	    	} else {
	    		RobotMap.ss_Drivebase.driveGyro();
	    	} 
	    } else {
    		if ((dL > distance || dR > distance) && (dSL > 4 && dSL <= maxSide)) {
	    		RobotMap.ss_Drivebase.stop();
	    		state = true;
	    	} else {
	    		RobotMap.ss_Drivebase.driveGyro();
	    	} 
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.ss_Drivebase.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
