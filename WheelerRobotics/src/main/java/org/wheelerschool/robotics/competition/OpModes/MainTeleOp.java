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
        float drive_x = gamepad1.right_stick_x;
        float drive_r = gamepad1.left_stick_x;
        drive_r = Math.copySign((float) (Math.pow(10, Math.abs(drive_r))-1) / 9.f, drive_r);
        if (Math.abs(drive_r) > 0) {
            float r_base = 0.2f;
            drive_r *= 1f - r_base;
            drive_r += Math.copySign(r_base, drive_r);
            drive_r *= 0.5f;
        }

        bot.drivePower(drive_y, -drive_x, drive_r);

        telemetry.addData("Y", drive_y);
        telemetry.addData("X", drive_x);
        telemetry.addData("R", drive_r);

        float armExtCtl = -gamepad1.right_stick_y;

        /*
        if (Math.abs(armExtCtl) > 0) {
            Log.d("update", "done");
            bot.controlArmExt(bot.armExtL, armExtCtl);
            bot.controlArmExt(bot.armExtR, armExtCtl);
        }*/

        /// STATES:
        if (gamepad1.right_bumper) {
            bot.stateIntake();
        }
        if (gamepad1.a) {
            bot.stateGrab();
        }
        if (gamepad1.b) {
            bot.grab(false);
        }

        if (gamepad1.dpad_up) {
            bot.armExtPos(3900, 0.5f);
        }

        if (gamepad1.dpad_down) {
            bot.armExtPos(50, 0.2f);
        }


        if (gamepad1.x) {
            bot.platform(true);
        } else if (gamepad1.y) {
            bot.platform(false);
        }


        /// MANUAL OVERRIDES:
        if (gamepad1.left_trigger > 0) {
            bot.intakeManual(-gamepad1.left_trigger);
        }


        /*
        Log.d("enc",
                (
                        Integer.toString(bot.frontRight.getCurrentPosition())
                        + " | " + Integer.toString(bot.frontLeft.getCurrentPosition())
                        + " | " + Integer.toString(bot.backLeft.getCurrentPosition())
                        + " | " + Integer.toString(bot.backRight.getCurrentPosition())
                )
        );*/
        Log.d("enc",
                ( Integer.toString(bot.armExtL.getCurrentPosition())
                        + " | " + Integer.toString(bot.armExtR.getCurrentPosition())
                )
        );
    }
}
