package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/*
 * This is a simple sample autonomous program that uses the Road Runner library to drive
 * in a square. You can use this as a template for your own autonomous programs.
 */
@Autonomous(group = "drive")
public class MyAwesomeAutonomous extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the SampleMecanumDrive class
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        // We want to start the bot at x: 10, y: -8, heading: 90 degrees
        Pose2d startPose = new Pose2d(10, -8, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        waitForStart();

        if (isStopRequested()) return;

        // Example trajectory that moves forward 30 inches
        Trajectory traj1 = drive.trajectoryBuilder(startPose)
                .forward(30)
                .build();

        drive.followTrajectory(traj1);
    }
}