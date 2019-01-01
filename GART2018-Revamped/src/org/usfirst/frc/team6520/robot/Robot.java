/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6520.robot;

import org.usfirst.frc.team6520.robot.commandgroups.CG_Left_Left;
import org.usfirst.frc.team6520.robot.commandgroups.CG_Left_Right;
import org.usfirst.frc.team6520.robot.commandgroups.CG_Mid_Left;
import org.usfirst.frc.team6520.robot.commandgroups.CG_Mid_Right;
import org.usfirst.frc.team6520.robot.commandgroups.CG_Right_Left;
import org.usfirst.frc.team6520.robot.commandgroups.CG_Right_Right;
import org.usfirst.frc.team6520.robot.commandgroups.CG_TTT;
import org.usfirst.frc.team6520.robot.commands.C_AutoByDistance;
import org.usfirst.frc.team6520.robot.commands.C_AutoByTimer;
import org.usfirst.frc.team6520.robot.commands.C_AutoShoot;
import org.usfirst.frc.team6520.robot.commands.C_AutoTillPass_Left;
import org.usfirst.frc.team6520.robot.commands.C_AutoTurn;
import org.usfirst.frc.team6520.robot.commands.C_DriveByAccel;
import org.usfirst.frc.team6520.robot.commands.C_Rotate;
import org.usfirst.frc.team6520.robot.commands.C_RotateByP;
import org.usfirst.frc.team6520.robot.subsystems.SS_AutoDrive;
import org.usfirst.frc.team6520.robot.subsystems.SS_Pid;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	public static OI oi;
	public static Preferences prefs;
	public static String STARTING_POSITION;
	public static String FIELD_CONFIG;
	
	Command autonomousCommand;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	public void initCamera() {
    	
    	RobotMap.camera.setResolution(1280, 720);
    }

	@Override
	public void robotInit() {
		oi = new OI();
		initCamera();
		RobotMap.comp.setClosedLoopControl(true);
//		RobotMap.ss_pid = new SS_Pid(SmartDashboard.getNumber("p", 0), SmartDashboard.getNumber("i", 0), SmartDashboard.getNumber("d", 0));
//		RobotMap.ultra_behind_left.setAutomaticMode(true);
//		RobotMap.ultra_behind_right.setAutomaticMode(true);
//		RobotMap.ultra_right.setAutomaticMode(true);
//		RobotMap.ultra_left.setAutomaticMode(true);
		chooser.addDefault("Default Auto", "M");
		chooser.addObject("Middle", "M");
		chooser.addObject("Left", "L");
		chooser.addObject("Right", "R");
		
		
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
    	SmartDashboard.putNumber("vị trí hiện tại", RobotMap.gyro.getAngle());
	}

	@Override
	public void autonomousInit() {
		RobotMap.gyro.reset();
//		RobotMap.ss_Auto.rotate(90);
//		autonomousCommand = chooser.getSelected();
//		STARTING_POSITION = prefs.getString("STARTING POSITION", "M");
		FIELD_CONFIG = DriverStation.getInstance().getGameSpecificMessage();

		String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		autonomousCommand = new C_DriveByAccel(6,0.3);
//		autonomousCommand = new C_AutoByTimer(6);
		
		
//		switch (autoSelected) {
//		default:
//			autonomousCommand = new C_AutoByDistance(2.76);
//			break;
//		case "M":
//			if (FIELD_CONFIG.charAt(0) == 'L'){
//				autonomousCommand = new CG_Mid_Left();
//				break;
//			}
//			else {
//				autonomousCommand = new CG_Mid_Right();
//				break;
//			}
//			
//		case "L":
//			if (FIELD_CONFIG.charAt(0) == 'L'){
//				autonomousCommand = new CG_Left_Left();
//				break;
//			}
//			else {
//				autonomousCommand = new CG_Left_Right();
//				break;
//			}
//		case "R":
//			if (FIELD_CONFIG.charAt(0) == 'L'){
//				autonomousCommand = new CG_Right_Left();
//				break;
//			}
//			else {
//				autonomousCommand = new CG_Right_Right();
//				break;
//			}
//		}
		
		

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {

		Scheduler.getInstance().run();
		RobotMap.update();

	}

	@Override
	public void teleopInit() {
		RobotMap.encoder.reset();
		RobotMap.ss_Intake.raised = false;
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		RobotMap.update();
		RobotMap.teleOpUpdate();
		
		//continuous commands
		RobotMap.ss_Drivebase.driveTwoJoysticks();
		RobotMap.ss_Launcher.shoot();
		RobotMap.ss_Intake.intake();
		
//		RobotMap.ss_test.test();
		
//		SmartDashboard.putBoolean("value", RobotMap.ss_test.value);

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
