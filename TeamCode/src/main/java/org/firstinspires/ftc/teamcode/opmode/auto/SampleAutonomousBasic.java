package org.firstinspires.ftc.teamcode.opmode.auto;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Constants;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Pose2d;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Geometry.Vector2d;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Pathing.CommandWaypoint;
import org.firstinspires.ftc.teamcode.nikorunnerlib.Pathing.Path;

@Autonomous(name = "SampleAutonomousBasic", group = "!")
public class SampleAutonomousBasic extends LinearOpMode {

    private final RobotHardware robot = RobotHardware.getInstance();

    @Override
    public void runOpMode() {
        CommandScheduler.getInstance().reset();
        Constants.AUTO = true;

        robot.init(hardwareMap);

        // set pose to current position on the field.
        robot.localizer.setPoseEstimate(new Pose2d(0, 0, Math.PI / 2));

        while (opModeInInit()) {
            telemetry.addLine("Autonomous is ready");
            telemetry.update();
        }


        // Input file
        // > Paths w Commands
        // > Commands

        // Commands
        // setCommand(index, Command);
        // > setCommand(0, new InstantCommand(() -> robot.alignToTag());

//        Path path = new Path.PathBuilder(robot.localizer.getPose(), new Vector2d(0, 0))
//                .addCommand(new CommandWaypoint(new TransferCommand(), 0))
//                .build();


        new SequentialCommandGroup();
        // Queue commands
        CommandScheduler.getInstance().schedule(
                // Queue follower and commands
        );

        while (opModeIsActive() && !isStopRequested()) {
            robot.read();
            robot.periodic();
            robot.periodic();
        }
    }
}