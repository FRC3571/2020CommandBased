package frc.robot.util;

import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class RobotMath {

    /**
     * get distance per pulse for encoder given certain params
     * 
     * @param countsPerRevolution (per wheel cycle)
     * @param wheelRadius         the radius of the wheel
     * @return the distance per pulse
     */

    public static double mapDouble(double x, double in_min, double in_max, double out_min, double out_max) {
        x = (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;

        return x;
    }

    public static double getAngleFromPoint(double x, double y) {
        double robotX = Robot.getRobotContainer().getDriveTrain().getXPos();
        double robotY = Robot.getRobotContainer().getDriveTrain().getYPos();

        double xDiff = x - robotX;
        double yDiff = y - robotY;

        double angle = Math.toDegrees(Math.atan2(xDiff, yDiff));

        return angle;
    }

    public static double getDistanceFromPoint(double x, double y) {
        double distance;

        double robotX = Robot.getRobotContainer().getDriveTrain().getXPos();
        double robotY = Robot.getRobotContainer().getDriveTrain().getYPos();

        double xDiff = x - robotX;
        double yDiff = y - robotY;

        distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

        return distance;
    }
}
