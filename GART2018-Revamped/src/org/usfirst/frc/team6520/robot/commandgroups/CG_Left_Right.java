package org.usfirst.frc.team6520.robot.commandgroups;

import org.usfirst.frc.team6520.robot.Robot;
import org.usfirst.frc.team6520.robot.commands.C_AutoByTimer;
import org.usfirst.frc.team6520.robot.commands.C_AutoShoot;
import org.usfirst.frc.team6520.robot.commands.C_AutoTillPass_Left;
import org.usfirst.frc.team6520.robot.commands.C_AutoTillPass_Right;
import org.usfirst.frc.team6520.robot.commands.C_AutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Left_Right extends CommandGroup {

	public CG_Left_Right () {

	addSequential(new C_AutoTillPass_Right());
	addSequential(new C_AutoTurn(90));
	addSequential(new C_AutoTillPass_Right());
	addSequential(new C_AutoTurn(90));
	addSequential(new C_AutoByTimer(1));
	addSequential(new C_AutoTurn(90));
	addSequential(new C_AutoShoot());
	}
}
