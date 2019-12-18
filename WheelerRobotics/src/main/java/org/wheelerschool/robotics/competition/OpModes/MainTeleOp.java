package org.wheelerschool.robotics.competition.OpModes;

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
        float drive_yl = -gamepad1.left_stick_y;
        float drive_yr = -gamepad1.right_stick_y;

        bot.frontLeft.setPower(drive_yl);
        bot.backLeft.setPower(drive_yl);

        bot.frontRight.setPower(drive_yr);
        bot.backRight.setPower(drive_yr);

        if (gamepad1.dpad_up) {
            bot.armAngle.setPower(1);
        } else if (gamepad1.dpad_down) {
            bot.armAngle.setPower(-1);
        } else {
            bot.armAngle.setPower(0);
        }
    }
}
