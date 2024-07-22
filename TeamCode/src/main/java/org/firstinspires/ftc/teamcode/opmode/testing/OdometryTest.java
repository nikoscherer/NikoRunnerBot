package org.firstinspires.ftc.teamcode.opmode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Library8101.Other.ButtonToggle;
import org.firstinspires.ftc.teamcode.common.drive.Localizer;
import org.firstinspires.ftc.teamcode.hardware.Constants;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.hardware.Sensors;

import java.sql.RowId;

//@Disabled
@TeleOp(name = "Odometry Test", group = "test")
public class OdometryTest extends CommandOpMode {

    private final RobotHardware robot = RobotHardware.getInstance();

    GamepadEx gamepad;

    ButtonToggle FOD;


    private double elapsedTime = 0.0;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();

        gamepad = new GamepadEx(gamepad1);

        Constants.AUTO = true;
        robot.init(hardwareMap);

        FOD = new ButtonToggle();

        robot.imu.resetYaw();

        while(opModeInInit()) {
            telemetry.addLine("Robot Initialized.");
            telemetry.update();
        }
    }



    @Override
    public void run() {
        CommandScheduler.getInstance().run();

        FOD.update(gamepad.getButton(GamepadKeys.Button.A));

        robot.drive.set(gamepad.getLeftY(), gamepad.getLeftX(),
                gamepad.getRightX(), FOD.getState(), -(double) robot.sensors.get(Sensors.SensorType.YAW));

        robot.periodic();

        telemetry.addData("Y", robot.localizer.getPose().getY());
        telemetry.addData("X", robot.localizer.getPose().getX());
        telemetry.addData("Heading", robot.localizer.getPose().getHeading());

        telemetry.addData("ΔY", robot.localizer.getDeltaPose().getY());
        telemetry.addData("ΔX", robot.localizer.getDeltaPose().getX());
        telemetry.addData("ΔHeading", robot.localizer.getDeltaPose().getHeading());

        telemetry.addData("Distance Per Tick", robot.localizer.distancePerTick);



        double timeUpdate = System.nanoTime();
        telemetry.addData("loop time", timeUpdate - elapsedTime);
        elapsedTime = timeUpdate;
        telemetry.update();
    }
}