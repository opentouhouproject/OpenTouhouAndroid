package com.scarlet.ui.button;

import android.util.Log;

import com.scarlet.math.MathUtil;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

public class LeftRoundedButtonMesh {
    private static float xMin, xMax, yMin, yMax;
    private static Vector4f color = new Vector4f(0.0f, 0.0f, 0.0f, 0.8f);
    private static Vector3f normal = new Vector3f(0.0f, 0.0f, 1.0f);

    /*
     * Constructor(s).
     */
    private LeftRoundedButtonMesh() { }

    /*
     *
     */
    public static float[] generate(float width, float height) {
        float[] attributes = new float[390];

        xMin = height / 2;
        xMax = width;

        yMin = 0;
        yMax = height;

        tesselateInnerQuad(attributes);

        tesselateLeftSemiCircle(attributes);

        return attributes;
    }

    private static void tesselateInnerQuad(float[] attributes) {
        float[] data = {
                xMin, yMax, 0, color.x, color.y, color.z, color.w, normal.x, normal.y, normal.z,
                xMin, yMin, 0, color.x, color.y, color.z, color.w, normal.x, normal.y, normal.z,
                xMax, yMin, 0, color.x, color.y, color.z, color.w, normal.x, normal.y, normal.z,
                xMax, yMin, 0, color.x, color.y, color.z, color.w, normal.x, normal.y, normal.z,
                xMax, yMax, 0, color.x, color.y, color.z, color.w, normal.x, normal.y, normal.z,
                xMin, yMax, 0, color.x, color.y, color.z, color.w, normal.x, normal.y, normal.z
        };
        System.arraycopy(data, 0, attributes, 0, 60);
    }

    private static void tesselateLeftSemiCircle(float[] attributes) {
        int index = 60;
        int numTriangles = 11;
        int numPoints = numTriangles + 1;

        float radius = yMax / 2;
        float xOffset = yMax / 2;
        float yOffset = yMax / 2;

        float[] circumferencePoints = getCircumerencePoints(numPoints, radius, xOffset, yOffset);
        float[] centerPoint = {
                xOffset, yOffset, 0.0f, color.x, color.y, color.z, color.w, normal.x, normal.y, normal.z
        };

        for (int i = 0; i < numTriangles; i++) {
            System.arraycopy(circumferencePoints, 10 * i, attributes, index, 10);
            index += 10;

            System.arraycopy(circumferencePoints, 10 * (i + 1), attributes, index, 10);
            index += 10;

            System.arraycopy(centerPoint, 0, attributes, index, 10);
            index += 10;
        }
    }

    private static float[] getCircumerencePoints(int numPoints, float radius, float offsetX, float offsetY) {
        float[] circumferencePoints = new float[numPoints * 10];

        float angle = 90;
        int index = 0;
        float angleIncrement = (270f - 90f) / (numPoints - 1);

        for (int i = 0; i < numPoints; i++) {
            float radians = MathUtil.degreesToRadians(angle);
            float x = radius * (float) Math.cos(radians) + offsetX;
            float y = radius * (float) Math.sin(radians) + offsetY;

            Log.d("Circumference Point", 1 * (float) Math.sin(radians) +  " " + 1 * (float) Math.cos(radians));

            circumferencePoints[index] = x;
            circumferencePoints[index + 1] = y;
            circumferencePoints[index + 2] = 0.0f;

            circumferencePoints[index + 3] = color.x;
            circumferencePoints[index + 4] = color.y;
            circumferencePoints[index + 5] = color.z;
            circumferencePoints[index + 6] = color.w;

            circumferencePoints[index + 7] = normal.x;
            circumferencePoints[index + 8] = normal.y;
            circumferencePoints[index + 9] = normal.z;

            index += 10;
            angle += angleIncrement;
        }

        return circumferencePoints;
    }
}