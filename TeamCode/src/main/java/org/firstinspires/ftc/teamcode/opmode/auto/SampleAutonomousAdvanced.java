package org.firstinspires.ftc.teamcode.opmode.auto;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.nikorunner.AutonomousManager;
import org.firstinspires.ftc.teamcode.hardware.Constants;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Pose2d;

@Autonomous(name = "SampleAutonomousAdvanced", group = "!")
public class SampleAutonomousAdvanced extends LinearOpMode {

    private final RobotHardware robot = RobotHardware.getInstance();

    private final AutonomousManager autonomousManager = new AutonomousManager();


    @Override
    public void runOpMode() {
        CommandScheduler.getInstance().reset();
        Constants.AUTO = true;

        robot.init(hardwareMap);
        autonomousManager.loadAuto("New_Auto.json");

        // set pose to current position on the field.
        robot.localizer.setPoseEstimate(new Pose2d(0, 0, Math.PI / 2));

        while(opModeInInit()) {
            telemetry.addLine("Autonomous is ready");
            telemetry.update();
        }


        // Input file
        // > Paths w Commands
        // > Commands

        // Commands
        // setCommand(index, Command);
        // > setCommand(0, new InstantCommand(() -> robot.alignToTag());

        // Queue commands
        CommandScheduler.getInstance().schedule(
                // Queue follower and commands
        );

        while(opModeIsActive() && !isStopRequested()) {
            robot.read();
            robot.periodic();
            robot.periodic();
        }
    }
}
