package org.firstinspires.ftc.teamcode.common.nikorunner;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Spline2d;

// WHAT THIS SHOULD DO

// 1. Find 2 close derivatives
// 2. Calculate a circle that exists along those 2 derivatives
// 3. Make the robot follow using the centripetal force
// 4. Use PID to follow the force

public class CentripetalFollower extends Follower {
    public static final RobotHardware robot = RobotHardware.getInstance();

    public CentripetalFollower(Spline2d fSpline) {
        super(fSpline);
    }

    @Override
    public void follow() {

    }
}
