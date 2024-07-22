package org.firstinspires.ftc.teamcode.common.commandbase.drivecommand;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.common.drive.Localizer;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Pathing.Path;

public class PathCommand extends CommandBase {
    private RobotHardware robot = RobotHardware.getInstance();

    // for ease of access
    private final MecanumDrive drive;
    private final Localizer localizer;


    public PathCommand(Path path) {
        this.drive = robot.drive;
        this.localizer = robot.localizer;


    }

    @Override
    public void execute() {
        // ALL nikorunner pathing

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
