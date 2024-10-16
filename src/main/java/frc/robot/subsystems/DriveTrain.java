package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;

public class DriveTrain {
    Motor rightMotors;
    Motor leftMotors;
    

    DifferentialDrive driveTrain;
    ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    ShuffleboardTab shuffleboard;
    PIDController pid = new PIDController(0.006, 0.016, 0.0);

    public DriveTrain() {
        rightMotors = new Motor(Constants.DriveTrainConstants.frontRightId, Constants.DriveTrainConstants.backRightId, false);
        leftMotors = new Motor(Constants.DriveTrainConstants.frontLeftId, Constants.DriveTrainConstants.backLeftId, false);

        driveTrain = new DifferentialDrive(rightMotors::set, leftMotors::set);
    }

    public void moveRobot(double xSpeed, double rotation) {
        driveTrain.arcadeDrive(xSpeed, rotation);

        shuffleboard = Shuffleboard.getTab("Gyro");
        shuffleboard.addDouble("Robot yaw ", (() -> gyro.getAngle()));
        
    }

    public double getGyroRead() {
        double angle = gyro.getAngle();
        while (angle > 360) {
            angle -= 360;
        }

        while (0 > angle) {
            angle += 360;
        }

        return angle;

    }
    public void rotate90Degrees() {
        double setPoint = 90.0;
        double percentage = pid.calculate(getGyroRead(), setPoint);

        if (percentage > 1.0) {
            percentage = 1.0;
        } else if (-1.0 > percentage) {
            percentage = -1.0;
        }

        moveRobot(0.0, percentage);
    }


}
