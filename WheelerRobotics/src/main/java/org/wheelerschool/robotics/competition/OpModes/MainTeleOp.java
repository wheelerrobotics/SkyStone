package org.wheelerschool.robotics.competition.OpModes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.wheelerschool.robotics.competition.HardwareBot;

@TeleOp(name="TeleOp")
public class MainTeleOp extends OpMode {
    HardwareBot bot;

    @Override
    public void init() {
        bot = new HardwareBot(hardwareMap);
    }

    @Override
    public void loop() {
        float drive_y = -gamepad1.left_stick_y;
        drive_y = Math.copySign((float) (Math.pow(3, Math.abs(drive_y))-1) / 2.f, drive_y);
        float drive_x = 0; //gamepad1.left_stick_x;
        float drive_r = gamepad1.left_stick_x;
        drive_r = Math.copySign((float) (Math.pow(10, Math.abs(drive_r))-1) / 9.f, drive_r);
        drive_r *= 0.5f;

        double scale = Math.hypot(drive_x, drive_y);
        double robotAngle = Math.atan2(drive_y, -drive_x) - Math.PI / 4;
        final double v1 = scale * Math.cos(robotAngle) + drive_r;
        final double v2 = scale * Math.sin(robotAngle) - drive_r;
        final double v3 = scale * Math.sin(robotAngle) + drive_r;
        final double v4 = scale * Math.cos(robotAngle) - drive_r;

        bot.frontLeft.setPower(v1);
        bot.backLeft.setPower(v3);

        bot.frontRight.setPower(v2);
        bot.backRight.setPower(v4);

        telemetry.addData("Y", drive_y);
        telemetry.addData("X", drive_x);
        telemetry.addData("R", drive_r);

        float armExtCtl = -gamepad1.right_stick_y;


        if (gamepad1.dpad_up) {
            bot.armAngle.setPower(1);
            armExtCtl = 0.2f;
        } else if (gamepad1.dpad_down) {
            bot.armAngle.setPower(-1);
        } else {
            bot.armAngle.setPower(0);
        }

        bot.controlArmExt(bot.armExtL, armExtCtl);
        bot.controlArmExt(bot.armExtR, armExtCtl);

        if (gamepad1.right_bumper) {
            bot.grabberLeft.setPower(1.0f);
            bot.grabberRight.setPower(1.0f);
        } else if (gamepad1.left_bumper) {
            bot.grabberLeft.setPower(-1.0f);
            bot.grabberRight.setPower(-1.0f);
        } else {
            bot.grabberLeft.setPower(0f);
            bot.grabberRight.setPower(0f);
        }

        //Log.d("enc", Integer.toString(bot.armExtL.getCurrentPosition()) + " | " + Integer.toString(bot.armExtR.getCurrentPosition()));
    }
}
