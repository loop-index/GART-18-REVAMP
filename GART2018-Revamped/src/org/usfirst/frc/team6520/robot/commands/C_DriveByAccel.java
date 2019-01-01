package org.usfirst.frc.team6520.robot.commands;

import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_DriveByAccel extends Command {
	double a;
	double v = 0;
	double initTime = Timer.getFPGATimestamp();
	double prevTime = initTime;
	double runTime;
	
    public C_DriveByAccel(double rt, double a) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.runTime = rt;
    	this.a = a;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("dba", "start");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double curTime = Timer.getFPGATimestamp();
    	double interval = curTime - prevTime;
    	if (curTime - initTime <= runTime/2) {
    		v += a*interval;   		
    	} else if (curTime - initTime < runTime) {
    		v -= a*interval;
    	} else {
    		end();
    	}
    	RobotMap.ss_Drivebase.driveF(v);
    	SmartDashboard.putNumber("time", curTime - initTime);
    	SmartDashboard.putNumber("vel", v);
    	prevTime = curTime;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.ss_Drivebase.stop();
    	SmartDashboard.putString("dba", "done");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
