package com.scarlet.math;

/**
 * 4 dimensional vector of floats.
 */

public class Vector4f {
    public float x, y, z, w;

    public Vector4f() {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
    }

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f(Vector4f v) {
        x = v.x;
        y = v.y;
        z = v.z;
        w = v.w;
    }


    public Vector4f(Vector3f v, float w) {
        x = v.x;
        y = v.y;
        z = v.z;
        this.w = w;
    }

    // setters
    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    // unary
    public Vector4f negate() {
        return new Vector4f(-1 * x, -1 * y, -1 * z, -1 * w);
    }

    // binary
    public Vector4f add(Vector4f v) {
        return new Vector4f(x + v.x, y + v.y, z + v.z, w + v.w);
    }

    public Vector4f subtract(Vector4f v) {
        return new Vector4f(x - v.x, y - v.y, z - v.z, w - v.w);
    }

    public Vector4f multiply(Vector4f v) {
        return new Vector4f(x * v.x, y * v.y, z * v.z, w * v.w);
    }

    public Vector4f scale(float f) {
        return new Vector4f(x * f, y * f, z * f, w * f);
    }

    // unary
    public void selfNegate() {
        set(-1 * x, -1 * y, -1 * z, -1 * w);
    }

    // binary
    public void selfAdd(Vector4f v) {
        set(x + v.x, y + v.y, z + v.z, w + v.w);
    }

    public void selfSubtract(Vector4f v) {
        set(x - v.x, y - v.y, z - v.z, w - v.w);
    }

    public void selfMultiply(Vector4f v) {
        set(x * v.x, y * v.y, z * v.z, w * v.w);
    }

    public void selfScale(float f) {
        set(x * f, y * f, z * f, w * f);
    }

    // returns the magnitude of the vector
    public float length() {
        return (float) Math.sqrt((double) (x * x + y * y + z * z + w * w));
    }

    // normalizes the vector
    public void selfNormalize() {
        float length = length();
        selfScale(1 / length);
    }

    public Vector4f normalize() {
        Vector4f result = new Vector4f(this);

        float length = length();
        result.selfScale(1 / length);

        return result;
    }

    public static float dot(Vector4f u, Vector4f v) {
        return u.x * v.x + u.y * v.y + u.z * v.z + u.w * v.w;
    }

    public static float dot3(Vector4f u, Vector4f v) {
        return u.x * v.x + u.y * v.y + u.z * v.z;
    }

    public static Vector3f cross(Vector4f u, Vector4f v) {
        float vv_x = u.y * v.z - u.z * v.y;
        float vv_y = u.z * v.x - u.x * v.z;
        float vv_z = u.x * v.y - u.y * v.x;

        return new Vector3f(vv_x, vv_y, vv_z);
    }

    public static float cross_value(Vector4f u, Vector4f v) {
        float vv_x = u.y * v.z - u.z * v.y;
        float vv_y = u.z * v.x - u.x * v.z;
        float vv_z = u.x * v.y - u.y * v.x;

        return vv_x + vv_y + vv_z;
    }
}
