package org.firstinspires.ftc.teamcode.common.nikorunner.Localization;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Point2d;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Pose2d;


/** @noinspection DataFlowIssue*/
public class ThreeWheelArcBasedOdometry {
    private static final RobotHardware robot = RobotHardware.getInstance();

    // Config
//    public static double ticksPerRev = 8192; // REV through bore encoder
//    public static double ticksPerIn = 0;


    // left odometry, right odometry, back odometry
    public static final Point2d[] ODO_LOCATIONS = {
            new Point2d(0, 0),
            new Point2d(0, 0),
            new Point2d(0, 0)};


    Pose2d robotPose = new Pose2d(0, 0, 0);

    private static final int[] lastOdoPositions = {0, 0, 0};

    private final Pose2d deltaPose = new Pose2d(0, 0, 0);

    public ThreeWheelArcBasedOdometry() {
        // initialize lastOdoPositions
    }

    public Pose2d update() {
        int[] delta = {
                (int) robot.sensors.get(Sensors.SensorType.ODO_LEFT) - lastOdoPositions[0],
                (int) robot.sensors.get(Sensors.SensorType.ODO_RIGHT) - lastOdoPositions[1],
                (int) robot.sensors.get(Sensors.SensorType.ODO_BACK) - lastOdoPositions[2]};

        // relative change in position
        double relTheta = (delta[1] - delta[0]) / (ODO_LOCATIONS[0].getY() - ODO_LOCATIONS[1].getY());
        double relY = delta[2] - ODO_LOCATIONS[2].getX() * relTheta;
        double relX = (delta[1] * ODO_LOCATIONS[0].getY() - delta[0] * ODO_LOCATIONS[1].getY())
                / (ODO_LOCATIONS[0].getY() - ODO_LOCATIONS[1].getY());

        // global change in position
        double deltaX =
                (relX * Math.cos((double) robot.sensors.get(Sensors.SensorType.YAW))
                        - (relY * Math.sin((double) robot.sensors.get(Sensors.SensorType.YAW))));
        double deltaY =
                (relY * Math.cos((double) robot.sensors.get(Sensors.SensorType.YAW))
                        + (relX * Math.sin((double) robot.sensors.get(Sensors.SensorType.YAW))));

        lastOdoPositions[0] = (int) robot.sensors.get(Sensors.SensorType.ODO_LEFT);
        lastOdoPositions[1] = (int) robot.sensors.get(Sensors.SensorType.ODO_RIGHT);
        lastOdoPositions[2] = (int) robot.sensors.get(Sensors.SensorType.ODO_BACK);

        // return global change in position
        deltaPose.setX(deltaX);
        deltaPose.setY(deltaY);
        deltaPose.setHeading(relTheta);
        return deltaPose;
    }
}

