package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.hardware.Constants;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.hardware.Sensors;

@TeleOp(name = "Sample TeleOp", group = "!")
public class SampleTeleOp extends CommandOpMode {

    private final RobotHardware robot = RobotHardware.getInstance();

    private GamepadEx gamepadEx;

    private double elapsedTime = 0.0;

    @Override
    public void initialize() {
        CommandScheduler.getInstance().reset();

        gamepadEx = new GamepadEx(gamepad1);

        robot.init(hardwareMap);

        while(opModeInInit()) {
            telemetry.addLine("Robot Initialized.");
            telemetry.update();
        }
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();

        if(Constants.FOD) {
            robot.drive.set(gamepadEx.getLeftY(), gamepadEx.getLeftX(), gamepadEx.getRightX());
        } else {
            robot.drive.set(gamepadEx.getLeftY(), gamepadEx.getLeftX(), gamepadEx.getRightX());
        }

        robot.periodic();

        double timeUpdate = System.nanoTime();
        telemetry.addData("YAW:", robot.sensors.get(Sensors.SensorType.YAW));
        telemetry.addData("loop time", timeUpdate - elapsedTime);
        elapsedTime = timeUpdate;
        telemetry.update();
    }
}