package com.scarlet.ui.button;

import android.util.Log;

import com.scarlet.math.CubicBezierCurve;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

/*
 * Generates the attributes used for drawing a button entity.
 * This button is drawn using vector graphics.
 * PCN format.
 */
public class ButtonDrawableMesh {
    private static Vector4f innerColor = new Vector4f(0.2f, 0.2f, 0.2f, 0.8f);
    //private static Vector4f borderColor = new Vector4f(0.529f, 0.807f, 0.921f, 0.7f);
    private static Vector4f borderColor = new Vector4f(0.6f, 0.6f, 0.9f, 1.0f);

    private static float borderThickness = 0.10f;

    private static float xInnerMax = 1.0f;
    private static float xInnerMin = -1.0f;
    private static float yInnerMax = 1.0f;
    private static float yInnerMin = -1.0f;

    private static float xOuterMax = xInnerMax + borderThickness;
    private static float xOuterMin = xInnerMin - borderThickness;
    private static float yOuterMax = yInnerMax + borderThickness;
    private static float yOuterMin = yInnerMin - borderThickness;

    /*
     * Constructor(s).
     */
    private ButtonDrawableMesh() { }

    /*
     * Generate the attributes.
     */
    public static float[] generate(float width, float height, float borderWidth) {
        float[] attributes = new float[1020];

        borderThickness = borderWidth;

        xInnerMax = width / 2.0f;
        xInnerMin = width / -2.0f;
        yInnerMax = height / 2.0f;
        yInnerMin = height / -2.0f;

        xOuterMax = xInnerMax + borderThickness;
        xOuterMin = xInnerMin - borderThickness;
        yOuterMax = yInnerMax + borderThickness;
        yOuterMin = yInnerMin - borderThickness;

        tesselateInnerQuad(attributes);

        tesselateSideBorders(attributes);

        tesselateTopLeftCorner(attributes);

        tesselateTopRightCorner(attributes);

        tesselateBottomLeftCorner(attributes);

        tesselateBottomRightCorner(attributes);

        return attributes;
    }

    /*
     * Generate the vertex data for the inner quad.
     */
    private static void tesselateInnerQuad(float[] attributes) {
        float[] data = {
                xInnerMin, yInnerMax, 0, innerColor.x, innerColor.y, innerColor.z, innerColor.w, 0, 0, 1,
                xInnerMin, yInnerMin, 0, innerColor.x, innerColor.y, innerColor.z, innerColor.w, 0, 0, 1,
                xInnerMax, yInnerMin, 0, innerColor.x, innerColor.y, innerColor.z, innerColor.w, 0, 0, 1,
                xInnerMax, yInnerMin, 0, innerColor.x, innerColor.y, innerColor.z, innerColor.w, 0, 0, 1,
                xInnerMax, yInnerMax, 0, innerColor.x, innerColor.y, innerColor.z, innerColor.w, 0, 0, 1,
                xInnerMin, yInnerMax, 0, innerColor.x, innerColor.y, innerColor.z, innerColor.w, 0, 0, 1
        };
        System.arraycopy(data, 0, attributes, 0, 60);
    }

    /*
     * Generate the vertex data for the side border quads.
     */
    private static void tesselateSideBorders(float[] attributes) {
        float[] dataLeft = {
                xOuterMin, yInnerMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1, // Top-Left
                xOuterMin, yInnerMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1, // Bottom-Left
                xInnerMin, yInnerMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1, // Bottom-Right
                xInnerMin, yInnerMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1, // Bottom-Right
                xInnerMin, yInnerMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1, // Top-Right
                xOuterMin, yInnerMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1  // Top-Left
        };
        System.arraycopy(dataLeft, 0, attributes, 60, 60);

        float[] dataRight = {
                xInnerMax, yInnerMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMax, yInnerMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xOuterMax, yInnerMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xOuterMax, yInnerMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xOuterMax, yInnerMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMax, yInnerMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1
        };
        System.arraycopy(dataRight, 0, attributes, 120, 60);

        float[] dataTop = {
                xInnerMin, yOuterMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMin, yInnerMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMax, yInnerMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMax, yInnerMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMax, yOuterMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMin, yOuterMax, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1
        };
        System.arraycopy(dataTop, 0, attributes, 180, 60);

        float[] dataBottom = {
                xInnerMin, yInnerMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMin, yOuterMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMax, yOuterMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMax, yOuterMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMax, yInnerMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1,
                xInnerMin, yInnerMin, 0.0f, borderColor.x, borderColor.y, borderColor.z, borderColor.w, 0, 0, 1
        };
        System.arraycopy(dataBottom, 0, attributes, 240, 60);
    }

    /*
     * Generate vertex data for the top-left corner.
     */
    private static void tesselateTopLeftCorner(float[] attributes) {
        Vector3f innerCorner = new Vector3f(xInnerMin, yInnerMax, 0.0f);

        Vector3f[] controlPoints = {
                new Vector3f(xInnerMin, yOuterMax, 0.0f), // c0
                new Vector3f(xOuterMin, yOuterMax, 0.0f), // c1
                new Vector3f(xOuterMin, yOuterMax, 0.0f), // c2
                new Vector3f(xOuterMin, yInnerMax, 0.0f), // c3
        };
        CubicBezierCurve cornerCurve = new CubicBezierCurve(controlPoints);

        Vector3f[] points = cornerCurve.subdivide(7);

        int index = 300;
        Log.d("Button Point", "Point: " + points[0].x + " " + points[0].y + " " + points[0].z);
        for (int i = 1; i < points.length; i++) {
            Log.d("Button Point", "Point: " + points[i].x + " " + points[i].y + " " + points[i].z);

            //Log.d("Button", "Index: " + index);
            addVertex(attributes, points[i], index);
            index += 10;

            //Log.d("Button", "Index: " + index);
            addVertex(attributes, innerCorner, index);
            index += 10;

            //Log.d("Button", "Index: " + index);
            addVertex(attributes, points[i - 1], index);
            index += 10;
        }

        Log.d("Button", "Index: " + index);
    }

    private static void tesselateTopRightCorner(float[] attributes) {
        Vector3f innerCorner = new Vector3f(xInnerMax, yInnerMax, 0.0f);

        Vector3f[] controlPoints = {
                new Vector3f(xOuterMax, yInnerMax, 0.0f), // c0
                new Vector3f(xOuterMax, yOuterMax, 0.0f), // c1
                new Vector3f(xOuterMax, yOuterMax, 0.0f), // c2
                new Vector3f(xInnerMax, yOuterMax, 0.0f), // c3
        };

        CubicBezierCurve cornerCurve = new CubicBezierCurve(controlPoints);

        Vector3f[] points = cornerCurve.subdivide(7);

        int index = 480;
        for (int i = 1; i < points.length; i++) {
            addVertex(attributes, points[i], index);
            index += 10;

            addVertex(attributes, innerCorner, index);
            index += 10;

            addVertex(attributes, points[i - 1], index);
            index += 10;
        }

        Log.d("Button", "Index: " + index);
    }

    private static void tesselateBottomLeftCorner(float[] attributes) {
        Vector3f innerCorner = new Vector3f(xInnerMin, yInnerMin, 0.0f);

        Vector3f[] controlPoints = {
                new Vector3f(xOuterMin, yInnerMin, 0.0f), // c0
                new Vector3f(xOuterMin, yOuterMin, 0.0f), // c1
                new Vector3f(xOuterMin, yOuterMin, 0.0f), // c2
                new Vector3f(xInnerMin, yOuterMin, 0.0f), // c3
        };

        CubicBezierCurve cornerCurve = new CubicBezierCurve(controlPoints);

        Vector3f[] points = cornerCurve.subdivide(7);

        int index = 660;
        for (int i = 1; i < points.length; i++) {
            addVertex(attributes, points[i], index);
            index += 10;

            addVertex(attributes, innerCorner, index);
            index += 10;

            addVertex(attributes, points[i - 1], index);
            index += 10;
        }

        Log.d("Button", "Index: " + index);
    }

    private static void tesselateBottomRightCorner(float[] attributes) {
        Vector3f innerCorner = new Vector3f(xInnerMax, yInnerMin, 0.0f);

        Vector3f[] controlPoints = {
                new Vector3f(xInnerMax, yOuterMin, 0.0f), // c0
                new Vector3f(xOuterMax, yOuterMin, 0.0f), // c1
                new Vector3f(xOuterMax, yOuterMin, 0.0f), // c2
                new Vector3f(xOuterMax, yInnerMin, 0.0f), // c3
        };

        CubicBezierCurve cornerCurve = new CubicBezierCurve(controlPoints);

        Vector3f[] points = cornerCurve.subdivide(7);

        int index = 840;
        for (int i = 1; i < points.length; i++) {
            addVertex(attributes, points[i], index);
            index += 10;

            addVertex(attributes, innerCorner, index);
            index += 10;

            addVertex(attributes, points[i - 1], index);
            index += 10;
        }

        Log.d("Button", "Index: " + index);
    }

    /*
     * A helper method to add a vertex.
     */
    private static void addVertex(float[] attributes, Vector3f position, int index) {
        attributes[index] = position.x;
        attributes[index + 1] = position.y;
        attributes[index + 2] = position.z;

        attributes[index + 3] = borderColor.x;
        attributes[index + 4] = borderColor.y;
        attributes[index + 5] = borderColor.z;
        attributes[index + 6] = borderColor.w;

        attributes[index + 7] = 0;
        attributes[index + 8] = 0;
        attributes[index + 9] = 1;
    }
}