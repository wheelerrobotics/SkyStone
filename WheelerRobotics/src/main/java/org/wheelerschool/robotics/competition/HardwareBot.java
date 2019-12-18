package org.wheelerschool.robotics.competition;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Hardware wrapper for the Wheeler (0252) competition robot.
 */
public class HardwareBot {
    private HardwareMap hardwareMap;  // Hardware Map given by OpMode

    /*
    Drive Motors
     */
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    /*
    Arm/Grabber Hardware
     */
    public DcMotor armAngle;


    /**
     * Set up drive motors
     */
    private void driveSetup() {
        //FL
        frontLeft = hardwareMap.dcMotor.get("driveFrontLeft");
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //FR
        frontRight = hardwareMap.dcMotor.get("driveFrontRight");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //BL
        backLeft = hardwareMap.dcMotor.get("driveBackLeft");
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //BR
        backRight = hardwareMap.dcMotor.get("driveBackRight");
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Set up arm hardware
     */
    private void armSetup() {
        armAngle = hardwareMap.dcMotor.get("armAngle");
    }

    /**
     * Create robot hardware object. Will execute setup of all hardware
     * @param hw (HardwareMap); Supplied hardware map from OpMode
     */
    public HardwareBot(HardwareMap hw) {
        hardwareMap = hw;

        driveSetup();
        armSetup();
    }
}
