package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.common.drive.Localizer;
import org.firstinspires.ftc.teamcode.common.drive.MecanumDrive;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvCamera;

import java.util.HashMap;

// Example
public class RobotHardware {
    private HardwareMap hardwareMap;
    private static RobotHardware instance = null;

    private boolean enabled = false;

    public MecanumDrive drive;
    public Localizer localizer;

    public BHI260IMU imu;
    public DcMotorEx driveFL, driveFR, driveBL, driveBR;

    // TEMP
    public Servo armLeft, armRight;
    public Servo armClaw;
    public Servo wristPitch, wristRoll;

    // TODO: see if its possible to combine these camera views.
    public VisionPortal aprilTagView;
    public AprilTagProcessor aprilTagProcessor;
    public OpenCvCamera openCvView;

    public HashMap<Sensors.SensorType, Number> sensors;


    public void init(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        imu = hardwareMap.get(BHI260IMU.class, "imu");
        imu.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD)));

        driveFL = hardwareMap.get(DcMotorEx.class, "FrontLeft");
        driveFR = hardwareMap.get(DcMotorEx.class, "FrontRight");
        driveBL = hardwareMap.get(DcMotorEx.class, "BackLeft");
        driveBR = hardwareMap.get(DcMotorEx.class, "BackRight");

        // TEMP
        armLeft = hardwareMap.get(Servo.class, "leftLift");
        armRight = hardwareMap.get(Servo.class, "rightLift");

        armClaw = hardwareMap.get(Servo.class, "Claw");
        wristPitch = hardwareMap.get(Servo.class, "wristPitch");
        wristRoll = hardwareMap.get(Servo.class, "wristRoll");

        driveFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        driveFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        driveFL.setDirection(DcMotorSimple.Direction.REVERSE);
        driveBL.setDirection(DcMotorSimple.Direction.REVERSE);

        drive = new MecanumDrive();

        this.sensors = new HashMap<>();

        armRight.setPosition(.25);
        wristPitch.setPosition(.6);
        wristRoll.setPosition(.85);
        armClaw.setPosition(.5);

        read();

        localizer = new Localizer();
    }

    public void periodic() {
        read();
        if(Constants.AUTO) {
            localizer.update();
        }
        drive.periodic();
    }

    // might have to seperate reads? (decrease loop time)
    public void read() {
        sensors.put(Sensors.SensorType.YAW, imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
        sensors.put(Sensors.SensorType.PITCH, imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.RADIANS));

        if(Constants.AUTO) {
            sensors.put(Sensors.SensorType.ODO_LEFT, -driveFL.getCurrentPosition());
            sensors.put(Sensors.SensorType.ODO_RIGHT, driveFR.getCurrentPosition());
            sensors.put(Sensors.SensorType.ODO_BACK, -driveBL.getCurrentPosition());
        }
    }


    public static RobotHardware getInstance() {
        if(instance == null) {
            instance = new RobotHardware();
        }

        instance.enabled = true;
        return instance;
    }

    public void exit() {
        instance = null;
    }
}
