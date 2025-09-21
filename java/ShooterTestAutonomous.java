package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Shooter Test Autonomous", group = "Tests")
public class ShooterTestAutonomous extends LinearOpMode {

    // Motor declaration for the shooter
    private DcMotor shootMotor;

    // Constants
    private static final double SHOOTER_POWER = 0.8; // Power for the shooter motor (0.0 to 1.0)
    private static final long GAP_DURATION_MS = 1000; // 1-second gap
    private static final long CCW_SPIN_DURATION_MS = 1000; // 1-second counter-clockwise spin
    private static final double ROTATIONS = 2.0; // Number of full rotations
    private static final double COUNTS_PER_REVOLUTION = 537.7; // Ticks per revolution for your motor. **CHANGE THIS** for your specific motor (e.g., goBILDA 5203 series is 384.5).

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the shooter motor from the hardware map.
        // Make sure the name "shootMotor" matches the configuration on your Robot Controller.
        shootMotor = hardwareMap.dcMotor.get("shootMotor");

        // Reset the encoder and set the motor to run using encoders.
        // This is crucial for accurate rotational control.
        shootMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // You may need to reverse the motor direction depending on your robot's build.
        // Uncomment the line below if the motor spins in the wrong direction.
        // shootMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.addData(">", "Press Start to begin autonomous test.");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (isStopRequested()) return;

        // --- Autonomous Sequence ---
        telemetry.addData("Status", "Running Sequence");
        telemetry.update();

        // --- Autonomous Sequence ---

        // 1. Spin 2 full circles clockwise
        telemetry.addData("Action", "Spinning " + ROTATIONS + " rotations clockwise");
        telemetry.update();

        // Calculate the target encoder ticks
        int targetTicks = (int) (ROTATIONS * COUNTS_PER_REVOLUTION);

        // Set the target position for the motor
        shootMotor.setTargetPosition(targetTicks);

        // Switch the motor to RUN_TO_POSITION mode
        shootMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set the motor power to start moving to the target
        shootMotor.setPower(SHOOTER_POWER);

        // Wait until the motor has reached its target position
        while (opModeIsActive() && shootMotor.isBusy()) {
            // You can add telemetry here to monitor the position in real-time
            telemetry.addData("Status", "Running to position...");
            telemetry.addData("Target | Current", "%d | %d", targetTicks, shootMotor.getCurrentPosition());
            telemetry.update();
            idle(); // Yields the CPU to other threads
        }

        // Stop the motor
        shootMotor.setPower(0);

        // 2. Wait for 1 second
        sleep(GAP_DURATION_MS);

        // 3. Spin counter-clockwise for 1 second
        telemetry.addData("Action", "Spinning counter-clockwise for 1 second");
        telemetry.update();
        shootMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Switch to timed mode
        shootMotor.setPower(-SHOOTER_POWER); // Negative power for opposite direction
        sleep(CCW_SPIN_DURATION_MS);
        shootMotor.setPower(0); // Stop the motor

        telemetry.addData("Status", "Sequence Complete");
        telemetry.update();
        sleep(2000); // Keep message on screen
    }
}