package nilies.util;

import nilies.component.Point;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TranslateUtil {

    public static Point moveTowardsAngle(Point from, Double alpha, Double distance){

        Double newX, newY;

        if (angleInBetween(0, 90, alpha)) {
            newX = from.x + distance * cos(Math.toRadians(alpha));
            newY = from.y - distance * sin(Math.toRadians(alpha));
        } else if (angleInBetween(91, 179, alpha)) {
            newX = from.x - distance * cos(Math.toRadians(180 - alpha));
            newY = from.y - distance * sin(Math.toRadians(180 - alpha));
        } else if (angleInBetween(180, 270, alpha)) {
            newX = from.x - distance * cos(Math.toRadians(alpha - 180));
            newY = from.y + distance * sin(Math.toRadians(alpha - 180));
        } else {
            newX = from.x + distance * sin(Math.toRadians(alpha - 270));
            newY = from.y + distance * cos(Math.toRadians(alpha - 270));
        }

        return new Point(newX, newY);
    }

    public static boolean angleInBetween(double x, double y, double alpha) {
        return (alpha >= x && alpha <= y);
    }
}
