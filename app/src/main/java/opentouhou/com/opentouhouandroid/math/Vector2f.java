package opentouhou.com.opentouhouandroid.math;

/**
 * 2 dimensional vector of floats.
 */

public class Vector2f
{
    public float x, y;

    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f v)
    {
        x = v.x;
        y = v.y;
    }

    // setters
    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2f v)
    {
        x = v.x;
        y = v.y;
    }

    // computations
    public float dot(Vector2f other)
    {
        return this.x * other.x + this.y * other.y;
    }

    public float distanceSquare(Vector2f other)
    {
        float xDifference = this.x - other.x;
        float yDifference = this.y - other.y;

        return xDifference * xDifference + yDifference * yDifference;
    }

    // static methods
    public static float dotProduct(Vector2f v1, Vector2f v2)
    {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public static float distance(Vector2f v1, Vector2f v2)
    {
        return (float)Math.sqrt(((double) distanceSquare(v1, v2)));
    }

    public static float distanceSquare(Vector2f v1, Vector2f v2)
    {
        return (v1.x - v2.x) * (v1.x - v2.x) + (v1.y - v2.y) * (v1.y - v2.y);
    }
}
