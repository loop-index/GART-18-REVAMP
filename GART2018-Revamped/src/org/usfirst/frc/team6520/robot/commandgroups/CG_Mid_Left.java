package org.usfirst.frc.team6520.robot.commandgroups;

import org.usfirst.frc.team6520.robot.commands.C_AutoByTimer;
import org.usfirst.frc.team6520.robot.commands.C_AutoShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Mid_Left extends CommandGroup {

    public CG_Mid_Left() {
       addSequential(new C_AutoByTimer(3));
       addSequential(new C_AutoShoot());
    }
}
