package org.usfirst.frc.team6520.robot.commandgroups;

import org.usfirst.frc.team6520.robot.Robot;
import org.usfirst.frc.team6520.robot.commands.C_AutoByDistance;
import org.usfirst.frc.team6520.robot.commands.C_AutoByTimer;
import org.usfirst.frc.team6520.robot.commands.C_AutoShoot;
import org.usfirst.frc.team6520.robot.commands.C_AutoTillPass_Left;
import org.usfirst.frc.team6520.robot.commands.C_AutoTillPass_Right;
import org.usfirst.frc.team6520.robot.commands.C_AutoTurn;
import org.usfirst.frc.team6520.robot.commands.C_DriveByPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Left_Left extends CommandGroup {

	public CG_Left_Left () {

	addSequential(new C_DriveByPosition(2.79, 1.16, 0, 1, 'R'));
	addSequential(new C_AutoTurn(90));
	addSequential(new C_AutoShoot());
	}
}
