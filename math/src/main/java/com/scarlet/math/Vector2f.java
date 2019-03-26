package com.scarlet.math;

/*
 * 2 dimensional vector of floats.
 */
public class Vector2f {
    public float x, y;

    /*
     * Constructor(s).
     */
    public Vector2f() {
        x = 0;
        y = 0;
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f v) {
        x = v.x;
        y = v.y;
    }

    /*
     * Setter(s).
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2f v) {
        x = v.x;
        y = v.y;
    }

    /*
     * Operation(s).
     */
    public float dot(Vector2f other) {
        return this.x * other.x + this.y * other.y;
    }

    public float distance(Vector2f other) {
        return (float) Math.sqrt((double) distanceSquare(other));
    }

    public float distanceSquare(Vector2f other) {
        float xDifference = this.x - other.x;
        float yDifference = this.y - other.y;

        return xDifference * xDifference + yDifference * yDifference;
    }

    /*
     * static Operation(s).
     */
    public static float dotProduct(Vector2f v1, Vector2f v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public static float distance(Vector2f v1, Vector2f v2) {
        return (float) Math.sqrt((double) distanceSquare(v1, v2));
    }

    public static float distanceSquare(Vector2f v1, Vector2f v2) {
        float xDifference = v1.x - v2.x;
        float yDifference = v1.y - v2.y;

        return xDifference * xDifference + yDifference * yDifference;
    }
}