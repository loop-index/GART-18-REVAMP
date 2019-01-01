package org.usfirst.frc.team6520.robot.subsystems;

import org.usfirst.frc.team6520.robot.Robot;
import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Launcher extends Subsystem {
	
	//boolean alternative: 0 = false, 1 = true
	public int raised = 0;
	public double switchSpeed = 0.55;
	public double scaleSpeed = 1.0;
	
	public void setWheels(double speed){
		RobotMap.shoot1.set(-speed);
		RobotMap.shoot2.set(speed);
		RobotMap.shoot3.set(-speed);
		RobotMap.shoot4.set(speed);
	}
	
	public void loadCube(){
		RobotMap.shoot1.set(0.8);
		RobotMap.shoot2.set(-0.8);
		RobotMap.shoot3.set(0.6);
		RobotMap.shoot4.set(-0.6);
	}
	
	public void shoot(){
		if (Robot.oi.panel.getRawAxis(2) != 0 && Robot.oi.panel.getRawAxis(3) == 0){
			setWheels(switchSpeed);
		}
		else if (Robot.oi.panel.getRawAxis(2) != 0 && Robot.oi.panel.getRawAxis(3) != 0){
			setWheels(scaleSpeed);
		}
		else {
			setWheels(0);
		}
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

