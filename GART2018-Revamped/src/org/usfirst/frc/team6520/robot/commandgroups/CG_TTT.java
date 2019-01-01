package org.usfirst.frc.team6520.robot.commandgroups;

import org.usfirst.frc.team6520.robot.commands.C_TurnLeft;
import org.usfirst.frc.team6520.robot.commands.C_TurnRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_TTT extends CommandGroup {

    public CG_TTT() {
    	addSequential(new C_TurnLeft(330));
    	addSequential(new C_TurnRight(270));
    	addSequential(new C_TurnLeft(180));
    }
}
