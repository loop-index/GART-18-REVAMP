package org.usfirst.frc.team6520.robot.subsystems;

import org.usfirst.frc.team6520.robot.Robot;
import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Intake extends Subsystem {

	double speed = 0.8;
	public int open = 0;
	public boolean raised = false;
	double error = 0.2;

	public void intake() {
		if (Robot.oi.panel.getRawAxis(1) > error){
			RobotMap.intake_left.set(speed);
			RobotMap.intake_right.set(-speed);
			RobotMap.ss_Launcher.loadCube();
		}
		else if (Robot.oi.panel.getRawAxis(1) < -error){
			RobotMap.intake_left.set(-speed);
			RobotMap.intake_right.set(speed);
//			RobotMap.ss_Launcher.loadCube();
		}
		else {
			RobotMap.intake_left.set(0);
			RobotMap.intake_right.set(0);
		}
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
