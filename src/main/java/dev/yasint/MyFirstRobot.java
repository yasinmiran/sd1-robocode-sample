package dev.yasint;

import robocode.*;

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class MyFirstRobot extends AdvancedRobot {

    private byte radarDirection = 1;
    private double moveDirection = 1;

    public void run() {
        setColors(Color.red, Color.black, Color.white);

        setAdjustRadarForRobotTurn(true);
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        while (true) {
            setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double enemyBearing = getHeading() + e.getBearing();
        double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
        double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));

        double gunTurnAngle = normalRelativeAngleDegrees(enemyBearing - getGunHeading());

        setTurnGunRight(gunTurnAngle);
        setTurnRight(e.getBearing() + 90 - 30 * moveDirection);

        double bulletPower = Math.min(3, getEnergy());
        if (getGunHeat() == 0 && Math.abs(gunTurnAngle) < 20) {
            setFire(bulletPower);
        }

        setAhead(100 * moveDirection);
        setTurnRadarRight(360 * radarDirection);
        radarDirection *= -1;
        moveDirection *= -1;
        execute();
    }

    public void onHitWall(HitWallEvent e) {
        moveDirection *= -1;
    }

    public void onHitRobot(HitRobotEvent e) {
        moveDirection *= -1;
    }

    public void onBulletHit(BulletHitEvent e) {
        radarDirection *= -1;
    }
}
