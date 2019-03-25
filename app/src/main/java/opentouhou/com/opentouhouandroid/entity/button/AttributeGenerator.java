package opentouhou.com.opentouhouandroid.entity.button;

/*
 * Generates the attributes used for drawing a button entity.
 * This button is drawn using vector graphics.
 * PCN format.
 */

import android.util.Log;

import opentouhou.com.opentouhouandroid.math.CubicBezierCurve;
import opentouhou.com.opentouhouandroid.math.Vector3f;

public class AttributeGenerator {
    //public float[] innerAttributes;
    public float[] borderAttributes;

    /*
     * Constructor(s).
     */
    public AttributeGenerator() {
        //innerAttributes = new float[60];
        //borderAttributes = new float[960];
        borderAttributes = new float[1020];
    }

    /*
     *
     */
    public void generate() {
        tesselateInnerQuad();

        tesselateSideBorders();

        tesselateTopLeftCorner();

        tesselateTopRightCorner();

        tesselateBottomLeftCorner();

        tesselateBottomRightCorner();
    }

    /*
     * Generate the vertex data for the inner quad.
     */
    private void tesselateInnerQuad() {
        float[] data = {
                -1,  1, 0, 1, 1, 1, 1, 0, 0, 1,
                -1, -1, 0, 1, 1, 1, 1, 0, 0, 1,
                 1, -1, 0, 1, 1, 1, 1, 0, 0, 1,
                 1, -1, 0, 1, 1, 1, 1, 0, 0, 1,
                 1,  1, 0, 1, 1, 1, 1, 0, 0, 1,
                -1,  1, 0, 1, 1, 1, 1, 0, 0, 1
        };
        System.arraycopy(data, 0, borderAttributes, 0, 60);
    }

    /*
     * Generate the vertex data for the side border quads.
     */
    private void tesselateSideBorders() {
        float[] dataLeft = {
                -1.1f,  1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1, // Top-Left
                -1.1f, -1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1, // Bottom-Left
                -1.0f, -1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1, // Bottom-Right
                -1.0f, -1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1, // Bottom-Right
                -1.0f,  1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1, // Top-Right
                -1.1f,  1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1  // Top-Left
        };
        System.arraycopy(dataLeft, 0, borderAttributes, 60, 60);

        float[] dataRight = {
                1.0f,  1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                1.0f, -1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                1.1f, -1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                1.1f, -1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                1.1f,  1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                1.0f,  1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1
        };
        System.arraycopy(dataRight, 0, borderAttributes, 120, 60);

        float[] dataTop = {
                -1.0f, 1.1f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                -1.0f, 1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                 1.0f, 1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                 1.0f, 1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                 1.0f, 1.1f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                -1.0f, 1.1f, 0.0f, 1, 1, 1, 1, 0, 0, 1
        };
        System.arraycopy(dataTop, 0, borderAttributes, 180, 60);

        float[] dataBottom = {
                -1.0f, -1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                -1.0f, -1.1f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                 1.0f, -1.1f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                 1.0f, -1.1f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                 1.0f, -1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1,
                -1.0f, -1.0f, 0.0f, 1, 1, 1, 1, 0, 0, 1
        };
        System.arraycopy(dataBottom, 0, borderAttributes, 240, 60);
    }

    /*
     * Generate vertex data for the top-left corner.
     */
    private void tesselateTopLeftCorner() {
        Vector3f innerCorner = new Vector3f(-1.0f, 1.0f, 0.0f);

        Vector3f[] controlPoints = {
                new Vector3f(-1.0f, 1.1f, 0.0f), // c0
                new Vector3f(-1.1f, 1.1f, 0.0f), // c1
                new Vector3f(-1.1f, 1.1f, 0.0f), // c2
                new Vector3f(-1.1f, 1.0f, 0.0f), // c3
        };
        CubicBezierCurve cornerCurve = new CubicBezierCurve(controlPoints);

        Vector3f[] points = cornerCurve.subdivide(7);

        int index = 300;
        Log.d("Button Point", "Point: " + points[0].x + " " + points[0].y + " " + points[0].z);
        for (int i = 1; i < points.length; i++) {
            Log.d("Button Point", "Point: " + points[i].x + " " + points[i].y + " " + points[i].z);

            //Log.d("Button", "Index: " + index);
            addVertex(points[i], index);
            index += 10;

            //Log.d("Button", "Index: " + index);
            addVertex(innerCorner, index);
            index += 10;

            //Log.d("Button", "Index: " + index);
            addVertex(points[i - 1], index);
            index += 10;
        }

        Log.d("Button", "Index: " + index);
    }

    private void tesselateTopRightCorner() {
        Vector3f innerCorner = new Vector3f(1.0f, 1.0f, 0.0f);

        Vector3f[] controlPoints = {
                new Vector3f(1.1f, 1.0f, 0.0f), // c0
                new Vector3f(1.1f, 1.1f, 0.0f), // c1
                new Vector3f(1.1f, 1.1f, 0.0f), // c2
                new Vector3f(1.0f, 1.1f, 0.0f), // c3
        };

        CubicBezierCurve cornerCurve = new CubicBezierCurve(controlPoints);

        Vector3f[] points = cornerCurve.subdivide(7);

        int index = 480;
        for (int i = 1; i < points.length; i++) {
            addVertex(points[i], index);
            index += 10;

            addVertex(innerCorner, index);
            index += 10;

            addVertex(points[i - 1], index);
            index += 10;
        }

        Log.d("Button", "Index: " + index);
    }

    private void tesselateBottomLeftCorner() {
        Vector3f innerCorner = new Vector3f(-1.0f, -1.0f, 0.0f);

        Vector3f[] controlPoints = {
                new Vector3f(-1.1f, -1.0f, 0.0f), // c0
                new Vector3f(-1.1f, -1.1f, 0.0f), // c1
                new Vector3f(-1.1f, -1.1f, 0.0f), // c2
                new Vector3f(-1.0f, -1.1f, 0.0f), // c3
        };

        CubicBezierCurve cornerCurve = new CubicBezierCurve(controlPoints);

        Vector3f[] points = cornerCurve.subdivide(7);

        int index = 660;
        for (int i = 1; i < points.length; i++) {
            addVertex(points[i], index);
            index += 10;

            addVertex(innerCorner, index);
            index += 10;

            addVertex(points[i - 1], index);
            index += 10;
        }

        Log.d("Button", "Index: " + index);
    }

    private void tesselateBottomRightCorner() {
        Vector3f innerCorner = new Vector3f(1.0f, -1.0f, 0.0f);

        Vector3f[] controlPoints = {
                new Vector3f(1.0f, -1.1f, 0.0f), // c0
                new Vector3f(1.1f, -1.1f, 0.0f), // c1
                new Vector3f(1.1f, -1.1f, 0.0f), // c2
                new Vector3f(1.1f, -1.0f, 0.0f), // c3
        };

        CubicBezierCurve cornerCurve = new CubicBezierCurve(controlPoints);

        Vector3f[] points = cornerCurve.subdivide(7);

        int index = 840;
        for (int i = 1; i < points.length; i++) {
            addVertex(points[i], index);
            index += 10;

            addVertex(innerCorner, index);
            index += 10;

            addVertex(points[i - 1], index);
            index += 10;
        }

        Log.d("Button", "Index: " + index);
    }

    /*
     * A helper method to add a vertex.
     */
    private void addVertex(Vector3f position, int index) {
        borderAttributes[index] = position.x;
        borderAttributes[index + 1] = position.y;
        borderAttributes[index + 2] = position.z;

        borderAttributes[index + 3] = 1;
        borderAttributes[index + 4] = 1;
        borderAttributes[index + 5] = 1;
        borderAttributes[index + 6] = 1;

        borderAttributes[index + 7] = 0;
        borderAttributes[index + 8] = 0;
        borderAttributes[index + 9] = 1;
    }
}