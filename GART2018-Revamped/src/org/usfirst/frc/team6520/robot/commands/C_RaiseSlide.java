package org.usfirst.frc.team6520.robot.commands;

import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_RaiseSlide extends Command {

    public C_RaiseSlide() {
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.ss_Intake.raised = !RobotMap.ss_Intake.raised;
    	if (RobotMap.ss_Intake.raised == false){
    		RobotMap.solenoid_slide.set(true);;
    	}
    	else {
    		RobotMap.solenoid_slide.set(false);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.ss_Intake.raised = false;
    	RobotMap.solenoid_slide.set(false);
    }
}
