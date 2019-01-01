package org.usfirst.frc.team6520.robot.commands;

import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_AutoByDistance extends Command {

	double distance;
	double error = 0.2;
	double filteredDist = RobotMap.kf.updateEstimate(RobotMap.ultra_behind_right.getRangeMM());

	public C_AutoByDistance(double value) {
		this.distance = value;
	}

	protected void initialize() {
	}

	protected void execute() {
		double calculatedDistance = distance - error;
		while (filteredDist <= calculatedDistance * 1000) {
			RobotMap.ss_Drivebase.driveGyro();
			RobotMap.update();
			Timer.delay(0.01);
			filteredDist = RobotMap.kf.updateEstimate(RobotMap.ultra_behind_right.getRangeMM());
		}

		RobotMap.ss_Drivebase.stop();
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		RobotMap.ss_Drivebase.stop();
	}

	protected void interrupted() {
		RobotMap.ss_Drivebase.stop();
	}
}
