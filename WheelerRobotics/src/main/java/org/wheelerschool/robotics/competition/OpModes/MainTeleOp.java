package org.wheelerschool.robotics.competition.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="TeleOp")
public class MainTeleOp extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor armAngle;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("driveFrontLeft");
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight = hardwareMap.dcMotor.get("driveFrontRight");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft = hardwareMap.dcMotor.get("driveBackLeft");
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backRight = hardwareMap.dcMotor.get("driveBackRight");
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armAngle = hardwareMap.dcMotor.get("armAngle");
    }

    @Override
    public void loop() {
        float drive_yl = -gamepad1.left_stick_y;
        float drive_yr = -gamepad1.right_stick_y;

        frontLeft.setPower(drive_yl);
        backLeft.setPower(drive_yl);

        frontRight.setPower(drive_yr);
        backRight.setPower(drive_yr);

        if (gamepad1.dpad_up) {
            armAngle.setPower(1);
        } else if (gamepad1.dpad_down) {
            armAngle.setPower(-1);
        } else {
            armAngle.setPower(0);
        }
    }
}
