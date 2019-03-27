package com.scarlet.math;

/*
 * 3 dimensional vector of floats.
 */
public class Vector3f {
    public float x, y, z;

    /*
     * Constructor(s).
     */
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

    /*
     * Setter(s).
     */
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /*
     * Mutable operations.
     * Methods that mutate this vector.
     */
    public void selfNegate() {
        set(-x, -y, -z);
    }

    public void selfAdd(Vector3f other) {
        set(x + other.x, y + other.y, z + other.z);
    }

    public void selfSubtract(Vector3f other) {
        set(x - other.x, y - other.y, z - other.z);
    }

    public void selfMultiply(Vector3f other) {
        set(x * other.x, y * other.y, z * other.z);
    }

    public void selfMultiply(float scalar) {
        set(x * scalar, y * scalar, z * scalar);
    }

    public void selfNormalize() {
        selfMultiply(1.0f / length());
    }

    /*
     * Non-mutable operations.
     * Methods that do not mutate this vector.
     */
    public Vector3f negate() {
        return new Vector3f(-x, -y, -z);
    }

    public Vector3f add(Vector3f other) {
        return new Vector3f(x + other.x, y + other.y, z + other.z);
    }

    public Vector3f subtract(Vector3f other) {
        return new Vector3f(x - other.x, y - other.y, z - other.z);
    }

    public Vector3f multiply(Vector3f other) {
        return new Vector3f(x * other.x, y * other.y, z * other.z);
    }

    public Vector3f multiply(float scalar) {
        return new Vector3f(x * scalar, y * scalar, z * scalar);
    }

    public Vector3f normalize() {
        return multiply(1.0f / length());
    }

    public Vector3f cross(Vector3f other) {
        float vv_x = y * other.z - other.y * z;
        float vv_y = -1 * (x * other.z - other.x * z);
        float vv_z = x * other.y - other.x * y;

        return new Vector3f(vv_x, vv_y, vv_z);
    }

    /*
     * Static Vector Operations.
     */
    public static Vector3f cross(Vector3f u,  Vector3f v) {
        float vv_x = u.y * v.z - v.y * u.z;
        float vv_y = -1 * (u.x * v.z - v.x * u.z);
        float vv_z = u.x * v.y - v.x * u.y;

        return new Vector3f(vv_x, vv_y, vv_z);
    }

    /*
     * Non-Vector Operations.
     * We do not return a vector result.
     */
    public float length() {
        return (float) Math.sqrt((double) (x * x + y * y + z * z));
    }

    public float dot(Vector3f other) {
        return x * other.x + y * other.y + z * other.z;
    }

    /*
     * Static Non-Vector Operations.
     */
    public static float dot(Vector3f u, Vector3f v) {
        return u.x * v.x + u.y * v.y + u.z * v.z;
    }

    public static float cross_value(Vector3f u, Vector3f v) {
        float vv_x = u.y * v.z - v.y * u.z;
        float vv_y = -1 * (u.x * v.z - v.x * u.z);
        float vv_z = u.x * v.y - v.x * u.y;

        return vv_x + vv_y + vv_z;
    }
}