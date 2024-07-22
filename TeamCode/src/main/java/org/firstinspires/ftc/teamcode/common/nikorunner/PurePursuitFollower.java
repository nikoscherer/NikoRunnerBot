package org.firstinspires.ftc.teamcode.common.nikorunner;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Spline2d;

// WHAT THIS SHOULD DO

// 1. Create an array of lines across the spline (equation)
// 2. Find the intersection point of one of the lines, and set the robots trajectory to that vector
// 3. Use PID to follow the vector

public class PurePursuitFollower extends Follower {
    public static final RobotHardware robot = RobotHardware.getInstance();

    public PurePursuitFollower(Spline2d fSpline) {
        super(fSpline);
    }

    @Override
    public void follow() {

    }
}
