package frc.robot.util;

import frc.robot.Robot;
import frc.robot.RobotContainer;

public class RobotMath {

    // Maps a double in a certain range to another range
    public static double mapDouble(double x, double in_min, double in_max, double out_min, double out_max) {
        x = (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;

        return x;
    }

    // Calculates the angle between a robot and a point
    public static double getAngleFromPoint(double x, double y) {
        double robotX = RobotContainer.driveTrain.getXPos();
        double robotY = RobotContainer.driveTrain.getYPos();

        double xDiff = x - robotX;
        double yDiff = y - robotY;

        double angle = Math.toDegrees(Math.atan2(xDiff, yDiff));

        return angle;
    }

    // Calculates the distance to a point
    public static double getDistanceFromPoint(double x, double y) {
        double distance;

        double robotX = RobotContainer.driveTrain.getXPos();
        double robotY = RobotContainer.driveTrain.getYPos();

        double xDiff = x - robotX;
        double yDiff = y - robotY;

        distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

        return distance;
    }

    public static double fixAngle(double angle) {
        if (Math.abs(angle - RobotContainer.navx.getAHRS().getYaw()) > 90) {
            if (angle >= 0 && angle <= 180) {
                angle -= 180;
            } else {
                angle += 180;
            }
        }

        return angle;
    }
}
