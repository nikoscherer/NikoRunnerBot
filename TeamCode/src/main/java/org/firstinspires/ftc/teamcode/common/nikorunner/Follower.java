package org.firstinspires.ftc.teamcode.common.nikorunner;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.nikorunnerlib.Spline2d;

// Input a path into the follower
@Config
abstract class Follower {
    public double errorRadius = 0.5;
    public double errorHeading = 5;



    Spline2d spline;

    public Follower(Spline2d fSpline) {
        this.spline = fSpline;
    }

    public abstract void follow();
}
