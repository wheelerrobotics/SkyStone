package org.wheelerschool.robotics;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


// OpMode class
public class SmallOp extends OpMode {

    // DEFINE motor objects:
    DcMotor driveLeft;
    DcMotor driveRight;

    // RUNS ON Initialization
    @Override
    public void init() {
        // SET UP motor objects:
        driveLeft = hardwareMap.dcMotor.get("driveLeft"); // use names defined in application
        driveRight = hardwareMap.dcMotor.get("driveRight");
    }

    // RUNS CONTINUOUSLY (after start)
    @Override
    public void loop() {
        // READ left and right joysticks (y-axis)
        float leftPower = gamepad1.left_stick_y;
        float rightPower = gamepad1.right_stick_y;

        // UPDATE motor powers
        driveLeft.setPower(leftPower);
        driveRight.setPower(rightPower);

        // UPDATE numbers on driver station display
        telemetry.addData("left", leftPower);
        telemetry.addData("right", rightPower);
    }
}
