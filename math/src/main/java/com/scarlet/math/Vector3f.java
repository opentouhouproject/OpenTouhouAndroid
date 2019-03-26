package com.scarlet.math;

/**
 * 3 dimensional vector of floats.
 */

public class Vector3f {

    public float x, y, z;

    public Vector3f() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(Vector3f v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public Vector3f(Vector4f v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void negate() {
        x = -1 * x;
        y = -1 * y;
        z = -1 * z;
    }

    public Vector3f add(Vector3f v) {
        Vector3f result = new Vector3f();
        result.set(x + v.x, y + v.y, z + v.z);
        return result;
    }

    public Vector3f subtract(Vector3f v) {
        Vector3f result = new Vector3f();
        result.set(x - v.x, y - v.y, z - v.z);
        return result;
    }

    public Vector3f multiply(Vector3f v) {
        Vector3f result = new Vector3f();
        result.set(x * v.x, y * v.y, z * v.z);
        return result;
    }

    public Vector3f scale(float f) {
        Vector3f result = new Vector3f();
        result.set(x * f, y * f, z * f);
        return result;
    }

    public void selfAdd(Vector3f v) {
        set(x + v.x, y + v.y, z + v.z);
    }

    public void selfSubtract(Vector3f v) {
        set(x - v.x, y - v.y, z - v.z);
    }

    public void selfMultiply(Vector3f v) {
        set(x * v.x, y * v.y, z * v.z);
    }

    public void selfScale(float f) {
        set(x * f, y * f, z * f);
    }

    public float length() {
        return (float) Math.sqrt((double) x * x + y * y + z * z);
    }

    public void normalize() {
        float length = length();
        selfScale(1 / length);
    }

    public float dot(Vector3f u, Vector3f v) {
        return u.x * v.x + u.y * v.y + u.z * v.z;
    }

    public Vector3f cross(Vector3f u,  Vector3f v) {
        float vv_x = u.y * v.z - v.y * u.z;
        float vv_y = -1 * (u.x * v.z - v.x * u.z);
        float vv_z = u.x * v.y - v.x * u.y;

        return new Vector3f(vv_x, vv_y, vv_z);
    }

    public float cross_value(Vector3f u, Vector3f v) {
        float vv_x = u.y * v.z - v.y * u.z;
        float vv_y = -1 * (u.x * v.z - v.x * u.z);
        float vv_z = u.x * v.y - v.x * u.y;

        return vv_x + vv_y + vv_z;
    }
}
