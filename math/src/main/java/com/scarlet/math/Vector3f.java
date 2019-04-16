package com.scarlet.math;

/*
 * 3 dimensional vector of floats.
 */
public final class Vector3f {
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

    public Vector3f(Vector3f other) {
        x = other.x;
        y = other.y;
        z = other.z;
    }

    public Vector3f(Vector4f other) {
        x = other.x;
        y = other.y;
        z = other.z;
    }

    /*
     * Setter(s).
     */
    public final void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final void set(Vector3f vector) {
        x = vector.x;
        y = vector.y;
        z = vector.z;
    }

    /*
     * Mutable operations.
     * Methods that mutate this vector.
     */
    public final Vector3f selfNegate() {
        return selfMultiply(-1.0f);
    }

    public final Vector3f selfNormalize() {
        return selfMultiply(1.0f / length());
    }

    public final Vector3f selfAdd(Vector3f vector) {
        x += vector.x;
        y += vector.y;
        z += vector.z;

        return this;
    }

    public final Vector3f selfSubtract(Vector3f vector) {
        x -= vector.x;
        y -= vector.y;
        z -= vector.z;

        return this;
    }

    public final Vector3f selfMultiply(Vector3f vector) {
        x *= vector.x;
        y *= vector.y;
        z *= vector.z;

        return this;
    }

    public final Vector3f selfMultiply(float scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;

        return this;
    }

    /*
     * Non-mutable operations.
     * Methods that do not mutate this vector.
     */
    public final Vector3f negate() {
        return copy().selfNegate();
    }

    public final Vector3f normalize() {
        return copy().selfNormalize();
    }

    public final Vector3f add(Vector3f vector) {
        return copy().selfAdd(vector);
    }

    public final Vector3f subtract(Vector3f vector) {
        return copy().selfSubtract(vector);
    }

    public final Vector3f multiply(Vector3f vector) {
        return copy().selfMultiply(vector);
    }

    public final Vector3f multiply(float scalar) {
        return copy().selfMultiply(scalar);
    }

    public final Vector3f cross(Vector3f vector) {
        return cross(this, vector);
    }

    /*
     * Non-Vector Operations.
     * We do not return a vector result.
     */
    public final float length() {
        return (float) Math.sqrt((double) (x * x + y * y + z * z));
    }

    public final float dot(Vector3f vector) {
        return dot(this, vector);
    }

    /*
     * Static Operations.
     */
    public static float dot(Vector3f u, Vector3f v) {
        return u.x * v.x + u.y * v.y + u.z * v.z;
    }

    public static Vector3f cross(Vector3f u,  Vector3f v) {
        float vv_x = u.y * v.z - v.y * u.z;
        float vv_y = -1 * (u.x * v.z - v.x * u.z);
        float vv_z = u.x * v.y - v.x * u.y;

        return new Vector3f(vv_x, vv_y, vv_z);
    }

    public static float crossValue(Vector3f u, Vector3f v) {
        float vv_x = u.y * v.z - v.y * u.z;
        float vv_y = -1 * (u.x * v.z - v.x * u.z);
        float vv_z = u.x * v.y - v.x * u.y;

        return vv_x + vv_y + vv_z;
    }

    /*
     * Copy method.
     */
    public final Vector3f copy() {
        return new Vector3f(this);
    }

    @Override
    public final String toString() {
        return x + " " + y + " " + z;
    }
}