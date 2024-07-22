package org.firstinspires.ftc.teamcode.common.drive;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Point2d;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Pose2d;


public class Localizer {

    private static final RobotHardware robot = RobotHardware.getInstance();

    Pose2d robotPose;
    Pose2d deltaPose;

    ElapsedTime timer;

    double ticksPerRev = 8192; // REV Through Bore
    double wheelRadius = 1.378 / 2; // in  robocaster 35mm

    public double distancePerTick = (wheelRadius * Math.PI * 2) / ticksPerRev;

    public static final Point2d[] ODO_LOCATIONS = {
            new Point2d(1, 4.5),
            new Point2d(1, -4.5),
            new Point2d(1, 1)};

    public int[] delta = {0, 0, 0};
    public double deltaTheta;

    int[] lastPosition;
    public double lastHeading;

    public Localizer() {
        robotPose = new Pose2d(0, 0, 0);
        deltaPose = new Pose2d(0, 0, 0);

        timer = new ElapsedTime();

        lastPosition  =  new int[] {
                (int) robot.sensors.get(Sensors.SensorType.ODO_LEFT),
                (int) robot.sensors.get(Sensors.SensorType.ODO_RIGHT),
                (int) robot.sensors.get(Sensors.SensorType.ODO_BACK)
        };
        lastHeading = (double) robot.sensors.get(Sensors.SensorType.YAW);
    }

    public void update() {
        delta[0] = (int) robot.sensors.get(Sensors.SensorType.ODO_LEFT) - lastPosition[0];
        delta[1] = (int) robot.sensors.get(Sensors.SensorType.ODO_RIGHT) - lastPosition[1];
        delta[2] = (int) robot.sensors.get(Sensors.SensorType.ODO_BACK) - lastPosition[2];
        deltaTheta = (double) robot.sensors.get(Sensors.SensorType.YAW) - lastHeading;

        double relTheta = (delta[1] - delta[0]) / (ODO_LOCATIONS[0].getY() - ODO_LOCATIONS[1].getY());

        double relX = (delta[1] * ODO_LOCATIONS[0].getY()) - (delta[0] * ODO_LOCATIONS[1].getY());

        double relY = delta[2] - (ODO_LOCATIONS[2].getX() * relTheta);

        // global change in position
        double deltaX = (relX * Math.cos(deltaTheta)) - (relY * Math.sin(deltaTheta));
        double deltaY = (relY * Math.cos(deltaTheta)) + (relX * Math.sin(deltaTheta));

        deltaPose.setX(deltaX);
        deltaPose.setY(deltaY);
        deltaPose.setHeading(relTheta);


        // TODO: Ticks most likely stack
        robotPose.setX(robotPose.getX() + deltaPose.getX());
        robotPose.setY(robotPose.getY() + deltaPose.getY());
        robotPose.setHeading(robotPose.getHeading() + deltaPose.getHeading());

        lastPosition[0] = (int) robot.sensors.get(Sensors.SensorType.ODO_LEFT);
        lastPosition[1] = (int) robot.sensors.get(Sensors.SensorType.ODO_RIGHT);
        lastPosition[2] = (int) robot.sensors.get(Sensors.SensorType.ODO_BACK);
        lastHeading = (double) robot.sensors.get(Sensors.SensorType.YAW);

//        int[] delta = {(int) robot.sensors.get(Sensors.SensorType.ODO_LEFT) - lastOdoPositions[0],
//                (int) robot.sensors.get(Sensors.SensorType.ODO_RIGHT) - lastOdoPositions[1],
//                (int) robot.sensors.get(Sensors.SensorType.ODO_BACK) - lastOdoPositions[2]};
//
//        deltaTheta = ((double) robot.sensors.get(Sensors.SensorType.YAW)) - lastHeading;
//
//        double relTheta =
//                (delta[1] - delta[0]) / (ODO_LOCATIONS[0].getY() - ODO_LOCATIONS[1].getY()) == 0? 1 : (ODO_LOCATIONS[0].getY() - ODO_LOCATIONS[1].getY());
//
//        double relX =
//                ((delta[1] * ODO_LOCATIONS[0].getY())
//                        - (delta[0] * ODO_LOCATIONS[1].getY()));
//
//        double relY = delta[2] - ODO_LOCATIONS[2].getX() * relTheta;
//
//        // global change in position
//        double deltaX =
//                (relX * Math.cos(deltaTheta)
//                        - (relY * Math.sin(deltaTheta)));
//        double deltaY =
//                (relY * Math.cos(deltaTheta)
//                        + (relX * Math.sin(deltaTheta)));
//
//
//        // global change in position
//        deltaPose.setX(deltaX / distancePerTick);
//        deltaPose.setY(deltaY / distancePerTick);
//        deltaPose.setHeading(relTheta);
//
//        // update global position
//        robotPose.setX(robotPose.getX() + deltaPose.getX());
//        robotPose.setY(robotPose.getY() + deltaPose.getY());
//        robotPose.setHeading(robotPose.getHeading() + deltaPose.getHeading());
//
//        // Update deltas
//        lastOdoPositions[0] = (int) robot.sensors.get(Sensors.SensorType.ODO_LEFT);
//        lastOdoPositions[1] = (int) robot.sensors.get(Sensors.SensorType.ODO_RIGHT);
//        lastOdoPositions[2] = (int) robot.sensors.get(Sensors.SensorType.ODO_BACK);
//        lastHeading = (double) robot.sensors.get(Sensors.SensorType.YAW);
    }

    public void scanTags() {

    }

    public void setPoseEstimate(Pose2d poseEstimate) {
        this.robotPose = poseEstimate;
    }

    public Pose2d getPose() {
        return robotPose;
    }

    public Pose2d getDeltaPose() {
        return deltaPose;
    }
}
