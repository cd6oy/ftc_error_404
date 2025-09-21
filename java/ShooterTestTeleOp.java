package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Shooter Test TeleOp", group = "Tests")
public class ShooterTestTeleOp extends LinearOpMode {

    // Motor declaration for the shooter
    private DcMotor shootMotor;

    // Constants
    private static final double SHOOTER_POWER = 1.0; // Power for the shooter motor (can be adjusted from 0.0 to 1.0)

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the shooter motor from the hardware map.
        // Make sure the name "shootMotor" matches the configuration on your Robot Controller
        // for the motor plugged into port 0 of the Expansion Hub.
        shootMotor = hardwareMap.dcMotor.get("shootMotor");

        // Set motor mode. RUN_WITHOUT_ENCODER is fine for simple teleop control.
        shootMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // You may need to reverse the motor direction depending on your robot's build.
        // Uncomment the line below if the motor spins in the wrong direction.
        // shootMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.addData(">", "Press Start to begin testing.");
        telemetry.addData(">", "Press Right Bumper (RB) on Gamepad 1 to run the shooter motor.");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Check if the right bumper on gamepad 1 is pressed
            if (gamepad1.right_bumper) {
                // If pressed, run the shooter motor at the defined power
                shootMotor.setPower(SHOOTER_POWER);
            } else {
                // If not pressed, stop the motor
                shootMotor.setPower(0);
            }

            // Telemetry to show button status and motor power
            telemetry.addData("Status", "Running");
            telemetry.addData("Right Bumper", gamepad1.right_bumper);
            telemetry.addData("Shooter Power", shootMotor.getPower());
            telemetry.update();
        }
    }
}