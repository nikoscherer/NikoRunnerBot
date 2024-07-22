package org.firstinspires.ftc.teamcode.common.commandbase.autocommands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Pose2d;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

// TODO: make sure this will run correctly
public class Relocalize extends InstantCommand {
    public final RobotHardware robot = RobotHardware.getInstance();

    public Pose2d cameraPose;

    public Relocalize(Pose2d camPose) {
        this.cameraPose = camPose;
    }

    @Override
    public void initialize() {
        robot.openCvView.closeCameraDevice();
        robot.aprilTagView.resumeStreaming();

        List<AprilTagDetection> scannedTags = robot.aprilTagProcessor.getDetections();

        // If error seems to great, do not use the scanned pose.
        for(AprilTagDetection tag : scannedTags) {
            // Calculations for estimated robot pose
        }

        robot.aprilTagView.stopStreaming();
    }
}
