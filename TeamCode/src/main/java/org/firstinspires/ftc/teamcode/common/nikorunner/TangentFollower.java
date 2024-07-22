package org.firstinspires.ftc.teamcode.common.nikorunner;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Spline2d;

/*
    Tangent Follower

    IN INITIALIZATION
    Calculate 100 points along the spline

    WHILE RUNNING
    Find the closest point to the robot in that array

 */

public class TangentFollower extends Follower {
    public static final RobotHardware robot = RobotHardware.getInstance();

    public TangentFollower(Spline2d fSpline) {
        super(fSpline);
    }

    @Override
    public void follow() {

    }
}
