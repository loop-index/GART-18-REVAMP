package org.usfirst.frc.team6520.robot.commands;

import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class C_Rotate extends Command {
	public static int angle;
    public C_Rotate(int angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.ss_pid.enable();
    	RobotMap.ss_pid.setSetpoint(angle + RobotMap.gyro.getAngle());
    	SmartDashboard.putNumber("đích", angle + RobotMap.gyro.getAngle());

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("vị trí hiện tại", RobotMap.gyro.getAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return RobotMap.ss_pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.ss_pid.disable();
    	RobotMap.left.set(0);
    	RobotMap.right.set(0);
    	SmartDashboard.putNumber("vị trí hiện tại", RobotMap.gyro.getAngle());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
