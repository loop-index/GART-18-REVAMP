package org.usfirst.frc.team6520.robot.commands;

import org.usfirst.frc.team6520.robot.Robot;
import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_AutoTillPass_Left extends Command {
	
	boolean encountered = false;
	boolean passed = false;
	
	double filtered_dist = RobotMap.kf.updateEstimate(RobotMap.ultra_left.getRangeMM());

    public C_AutoTillPass_Left() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (passed == false){
    		RobotMap.ss_Drivebase.driveGyro();
    		if (filtered_dist < 1500 && filtered_dist >= 100 && encountered == false){
    			encountered = true;
    			
    		}
    		if (encountered == true){
    			if (filtered_dist > 3500 || filtered_dist < 100){
        			passed = true;
    			}
    		}
    		filtered_dist = RobotMap.kf.updateEstimate(RobotMap.ultra_left.getRangeMM());
    		SmartDashboard.putBoolean("encountered?", encountered);
    		SmartDashboard.putBoolean("passed?", passed);
    		Timer.delay(0.01);
    	}
    		RobotMap.ss_Drivebase.stop();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.ss_Drivebase.stop();
    	passed = false;
    	encountered = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.ss_Drivebase.stop();
    	passed = false;
    	encountered = false;
    }
}
