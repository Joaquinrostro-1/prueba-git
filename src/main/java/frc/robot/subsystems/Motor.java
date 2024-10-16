package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants;

public class Motor {
    int motor1Id;
    VictorSPX motor1;
    int motor3Id;
    VictorSPX motor3;

    public Motor (int motor1Id, int motor2Id, boolean invert) {
        this.motor1Id = motor1Id;
        this.motor1 = new VictorSPX(motor1Id);
        this.motor3Id = motor2Id;
        this.motor3 = new VictorSPX(motor2Id);

        this.motor1.setInverted(false);
        this.motor3.setInverted(invert);

        this.motor3.follow(this.motor1);
    }

    public void set(double percentage) {
       this.motor1.set(ControlMode.PercentOutput, percentage);
    }
}