package frc.robot.util;

import frc.robot.RobotContainer;

public class RobotMath {

    // Maps a double in a certain range to another range
    public static double mapDouble(double x, final double in_min, final double in_max, final double out_min,
            final double out_max) {
        x = (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;

        return x;
    }

    // Calculates the angle between a robot and a point
    public static double getAngleFromPoint(final double x, final double y) {
        final double robotX = RobotContainer.driveTrain.getXPos();
        final double robotY = RobotContainer.driveTrain.getYPos();

        final double xDiff = x - robotX;
        final double yDiff = y - robotY;

        final double angle = Math.toDegrees(Math.atan2(xDiff, yDiff));

        return angle;
    }

    // Calculates the distance to a point
    public static double getDistanceFromPoint(final double x, final double y) {
        double distance;

        final double robotX = RobotContainer.driveTrain.getXPos();
        final double robotY = RobotContainer.driveTrain.getYPos();

        final double xDiff = x - robotX;
        final double yDiff = y - robotY;

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
