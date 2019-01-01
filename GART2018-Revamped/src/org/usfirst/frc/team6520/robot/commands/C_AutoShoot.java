package org.usfirst.frc.team6520.robot.commands;

import org.usfirst.frc.team6520.robot.Robot;
import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_AutoShoot extends Command {

	double startTime;
	double time;
	boolean shot = false;
    public C_AutoShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    	time = startTime;
    	RobotMap.solenoid_launcher.set(Value.kForward);
    	Timer.delay(3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	    if (time - startTime > 7) {	

	    	RobotMap.solenoid_fire.set(Value.kReverse);
	    	Timer.delay(0.2);
	    	shot = true;
	    }
	    RobotMap.ss_Launcher.setWheels(RobotMap.ss_Launcher.switchSpeed);
    	time = Timer.getFPGATimestamp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shot;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.ss_Launcher.setWheels(0);
    	RobotMap.solenoid_launcher.set(Value.kReverse);
    	RobotMap.solenoid_fire.set(Value.kForward);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

