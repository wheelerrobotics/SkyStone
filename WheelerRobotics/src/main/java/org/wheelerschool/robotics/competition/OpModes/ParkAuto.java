package org.wheelerschool.robotics.competition.OpModes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.wheelerschool.robotics.competition.HardwareBot;

@Autonomous
public class ParkAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot bot = new HardwareBot(hardwareMap);
        bot.resetMotorEnc(bot.frontLeft);
        bot.resetMotorEnc(bot.frontRight);
        bot.resetMotorEnc(bot.backLeft);
        bot.resetMotorEnc(bot.backRight);

        waitForStart();

        bot.drivePower(0.3f, 0, 0);

        while (
                (bot.frontLeft.getCurrentPosition() + bot.frontRight.getCurrentPosition() + bot.backLeft.getCurrentPosition() + bot.backRight.getCurrentPosition())/4 < 1250
                && opModeIsActive()
        ) {
            Log.d("enc",
                    (
                            Integer.toString(bot.frontRight.getCurrentPosition())
                                    + " | " + Integer.toString(bot.frontLeft.getCurrentPosition())
                                    + " | " + Integer.toString(bot.backLeft.getCurrentPosition())
                                    + " | " + Integer.toString(bot.backRight.getCurrentPosition())
                    )
            );
            telemetry.update();
        }

        bot.drivePower(0, 0, 0);
    }
}
