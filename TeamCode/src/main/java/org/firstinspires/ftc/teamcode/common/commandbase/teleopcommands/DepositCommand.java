//package org.firstinspires.ftc.teamcode.common.commandbase.teleopcommands;
//
//import com.arcrobotics.ftclib.command.InstantCommand;
//import com.arcrobotics.ftclib.command.ParallelCommandGroup;
//
//import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
//
//public class DepositCommand extends ParallelCommandGroup {
//
//    public DepositCommand(int height) {
//        super(
//                new InstantCommand(() -> RobotHardware.getInstance().lifter.setHeight(height)),
//               new InstantCommand(() -> RobotHardware.getInstance().claw.setAngle(135))
//        );
//
//        addRequirements(RobotHardware.getInstance().lifter, RobotHardware.getInstance().claw);
//   }
//}