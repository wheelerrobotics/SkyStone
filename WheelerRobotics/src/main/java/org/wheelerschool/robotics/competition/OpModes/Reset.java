package org.wheelerschool.robotics.competition.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.wheelerschool.robotics.competition.HardwareBot;

@Autonomous
public class Reset extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot hw = new HardwareBot(hardwareMap);

        waitForStart();

        hw.resetArm();
    }
}
