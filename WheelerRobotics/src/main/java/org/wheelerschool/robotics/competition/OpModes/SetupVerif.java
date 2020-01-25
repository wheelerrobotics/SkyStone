package org.wheelerschool.robotics.competition.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.wheelerschool.robotics.competition.HardwareBot;

@Autonomous
public class SetupVerif extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot bot = new HardwareBot(hardwareMap);

        waitForStart();

        while (true) {
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        bot.frontRight.setPower(0.2f);
                        break;
                    case 1:
                        bot.frontLeft.setPower(0.2f);
                        break;
                    case 2:
                        bot.backLeft.setPower(0.2f);
                        break;
                    case 3:
                        bot.backRight.setPower(0.2f);
                        break;
                }
                Thread.sleep(1000);
                bot.frontRight.setPower(0);
                bot.frontLeft.setPower(0);
                bot.backLeft.setPower(0);
                bot.backRight.setPower(0);
            }
        }
    }
}
