package org.firstinspires.ftc.teamcode.common.nikorunner;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Path2d;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Point2d;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Pose2d;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Other.Util;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Pathing.SplineGenerator;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Spline2d;

import java.util.ArrayList;


// WHAT THIS SHOULD DO

// 1. Create an array of points along the spline
// 2. Find the closest point (that goes along the path)
// 3. Finds the angled needed to move
// 4. Uses PID to follow to the point

public class PointFollower extends Follower {
    public static final RobotHardware robot = RobotHardware.getInstance();

    int index = -1;

    Path2d path;

    public PointFollower(Spline2d fSpline) {
        super(fSpline);

        path = new SplineGenerator(
                fSpline.getStartPose(),
                fSpline.getStartTangent(),
                fSpline.getEndTangent(),
                fSpline.getEndPose()
        ).getSpline();
    }


    @Override
    public void follow() {
        Pose2d robotPose = robot.localizer.getPose();

        // If robot pose is inside the target point
        if(Util.inRange(robotPose.getX() - path.getPoints().get(index).getX(), super.errorRadius)
                && Util.inRange(robotPose.getY() - path.getPoints().get(index).getY(), super.errorRadius)) {
            if(!(path.getPoints().size() >= index)) {
                index = index + 1;
            }

        }

        // TODO: Set to angle the point is at
        robot.drive.set(.5, .5, 0, true, 45);
    }
}
