package com.scarlet.math;

public class CubicBezierCurve {
    private Vector3f[] points;

    /*
     * Constructor(s).
     */
    public CubicBezierCurve(Vector3f[] points) {
        this.points = points;
    }

    /*
     * Getter(s).
     */
    public Vector3f getStart() {
        return points[0];
    }

    public Vector3f getEnd() {
        return points[3];
    }

    public Vector3f getPoint(float t) {
        float diff = 1 - t;

        float c_0 = diff * diff * diff;
        float c_1 = 3 * diff * diff * t;
        float c_2 = 3 * diff * t * t;
        float c_3 = t * t * t;

        return points[0].multiply(c_0).add(points[1].multiply(c_1).add(points[2].multiply(c_2).add(points[3].multiply(c_3))));
    }

    /*
     * Subdivide a bezier curve into points.
     */
    public Vector3f[] subdivide(int numberOfPoints) {
        // Check the input.
        if (numberOfPoints < 2) throw new RuntimeException("Cannot subdivide a bezier curve with less than 2 points.");

        // Allocate a new array and compute the interval magnitude.
        float interval = 1.0f / (numberOfPoints - 1);
        Vector3f[] points = new Vector3f[numberOfPoints];

        // Compute the points.
        for (int i = 0; i < numberOfPoints; i++) points[i] = getPoint(i * interval);

        return points;
    }
}