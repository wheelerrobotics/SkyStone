package org.wheelerschool.robotics.competition;

import android.util.Log;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


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
    Intake Hardware
     */
    public DcMotor intakeL;
    public DcMotor intakeR;

    /*
    Arm Hardware
     */
    public DcMotor armExtL;
    public DcMotor armExtR;

    /*
    Grabber
     */
    public Servo grabberLeft;
    public Servo grabberRight;

    /**
     * Set up drive motors
     */
    private void driveSetup() {
        //FL
        frontLeft = hardwareMap.dcMotor.get("driveFrontLeft");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //FR
        frontRight = hardwareMap.dcMotor.get("driveFrontRight");
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //BL
        backLeft = hardwareMap.dcMotor.get("driveBackLeft");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //BR
        backRight = hardwareMap.dcMotor.get("driveBackRight");
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Set up intake hardware
     */
    private void intakeSetup() {
        intakeL = hardwareMap.dcMotor.get("intakeL");
        intakeL.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeR = hardwareMap.dcMotor.get("intakeR");
        intakeR.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    /**
     * Set up arm hardware
     */
    private void armSetup() {
        armExtL = hardwareMap.dcMotor.get("armExtL");
        armExtL.setDirection(DcMotorSimple.Direction.REVERSE);
        armExtL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armExtL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armExtR = hardwareMap.dcMotor.get("armExtR");
        armExtR.setDirection(DcMotorSimple.Direction.FORWARD);
        armExtR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armExtR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     *
     */
    private void grabberSetup() {
        grabberLeft = hardwareMap.servo.get("grabberLeft");
        grabberLeft.setDirection(Servo.Direction.REVERSE);
        grabberRight = hardwareMap.servo.get("grabberRight");
        grabberRight.setDirection(Servo.Direction.FORWARD);
    }


    ///// RESET METHODS
    /**
     * Reset arm
     */
    public void resetArm() {
        armExtL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armExtR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armExtL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armExtR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    ////// CONTROL
    /**
     * Control arm motor
     */
    public void controlArmExt(DcMotor m, float p) {
        if (p< 0 && m.getCurrentPosition() < 100) {
            m.setPower(0);
            Log.d("arm", "Limited");
        } else {
            m.setPower(p);
        }
    }

    /**
     * Create robot hardware object. Will execute setup of all hardware
     * @param hw (HardwareMap); Supplied hardware map from OpMode
     */
    public HardwareBot(HardwareMap hw) {
        hardwareMap = hw;

        driveSetup();
        intakeSetup();
        armSetup();
        grabberSetup();
    }
}
