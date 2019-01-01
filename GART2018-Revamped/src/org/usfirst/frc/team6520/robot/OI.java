/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6520.robot;

import org.usfirst.frc.team6520.robot.commands.C_DeployIntake;
import org.usfirst.frc.team6520.robot.commands.C_Fire;
import org.usfirst.frc.team6520.robot.commands.C_RaiseLauncher;
import org.usfirst.frc.team6520.robot.commands.C_RaiseSlide;
import org.usfirst.frc.team6520.robot.commands.C_Rotate;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick gamepad = new Joystick(0);
	public Joystick panel = new Joystick(1);
	
	//gamepad control
	public Button TEST_A = new JoystickButton(gamepad, 1);
	
	// driver control	
	public Button LAUNCHER_B = new JoystickButton(panel, 2); 
	
	// function control
	public Button FIRE_A = new JoystickButton(panel, 1);
	public Button SLIDE_X = new JoystickButton(panel, 3);
	public Button INTAKE_Y = new JoystickButton(panel, 4);
	public Button LOADCUBE_RIGHTBACK = new JoystickButton(panel, 6);
	
	
	
	public OI(){
		LAUNCHER_B.whenPressed(new C_RaiseLauncher());
		INTAKE_Y.whenPressed(new C_DeployIntake());
		FIRE_A.whileHeld(new C_Fire());
		SLIDE_X.whenPressed(new C_RaiseSlide());
//		FIRE_A.whenPressed(new C_Fire());
		TEST_A.whenPressed(new C_Rotate(90));
//		SLIDE_X.whileHeld(new C;
	}
}
