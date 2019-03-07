package opentouhou.com.opentouhouandroid.math;

/**
 * Common methods for math.
 */

public class MathUtil {

    public static float distanceSquare(float x1, float y1, float x2, float y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    public static float degreesToRadians(float angle) {
        return (float) (angle * Math.PI / 180);
    }

}
