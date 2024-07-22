//package org.firstinspires.ftc.teamcode.common.commandbase;
//
//import com.arcrobotics.ftclib.command.InstantCommand;
//import com.arcrobotics.ftclib.command.ParallelCommandGroup;
//
//import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
//
//public class TransferCommand extends ParallelCommandGroup {
//    public TransferCommand() {
//        super(
//                new InstantCommand(() -> RobotHardware.getInstance().lifter.setHeight(10)),
//                new InstantCommand(() -> RobotHardware.getInstance().claw.setAngle(0)),
//                new InstantCommand(() -> RobotHardware.getInstance().claw.open())
//        );
//    }
//}
