package org.firstinspires.ftc.teamcode.common.drive;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
// TODO: Change to Nikorunner Vector2d
import com.arcrobotics.ftclib.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

@Config
public class MecanumDrive extends SubsystemBase {

    // Config Variables
    public static double speedMultiplier = .75;

    private final RobotHardware robot = RobotHardware.getInstance();

    public void set(double y, double x, double rot, boolean fod, double angle) {
        if(fod) {
            Vector2d rotatedVector = new Vector2d(x, y).rotateBy(angle);
            set(rotatedVector.getY(), rotatedVector.getX(), rot);
        } else {
            set(y, x, rot);
        }
    }

    public void set(double y, double x, double rot) {
        robot.driveFL.setPower((y + x + (rot * .4)) * speedMultiplier);
        robot.driveFR.setPower((y - x - (rot * .4)) * speedMultiplier);
        robot.driveBL.setPower((y - x + (rot * .4)) * speedMultiplier);
        robot.driveBR.setPower((y + x - (rot * .4)) * speedMultiplier);
    }
}
