package com.scarlet.math;

/*
 * 4 dimensional vector of floats.
 */
public final class Vector4f {
    public float x, y, z, w;

    /*
     * Constructor(s).
     */
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

    public Vector4f(Vector4f other) {
        x = other.x;
        y = other.y;
        z = other.z;
        w = other.w;
    }

    public Vector4f(Vector3f other, float w) {
        x = other.x;
        y = other.y;
        z = other.z;
        this.w = w;
    }

    /*
     * Setter(s).
     */
    public final void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public final void set(Vector4f other) {
        x = other.x;
        y = other.y;
        z = other.z;
        w = other.w;
    }

    /*
     * Non-mutating Operations.
     */

    /*
     * Unary Operation(s)
     */
    public final Vector4f negate() {
        return copy().selfNegate();
    }

    public final Vector4f normalize() {
        return copy().selfNormalize();
    }

    /*
     * Binary Operation(s)
     */
    public final Vector4f add(Vector4f vector) {
        return copy().selfAdd(vector);
    }

    public final Vector4f subtract(Vector4f vector) {
        return copy().selfSubtract(vector);
    }

    public final Vector4f multiply(Vector4f vector) {
        return copy().selfMultiply(vector);
    }

    public final Vector4f multiply(float scalar) {
        return copy().selfMultiply(scalar);
    }

    public final float dot(Vector4f vector) {
        return dot(this, vector);
    }

    public final Vector4f cross(Vector4f vector) {
        return cross(this, vector);
    }

    public final Vector3f cross3(Vector4f vector) {
        return cross3(this, vector);
    }

    /*
     * Operations that mutate the vector.
     */

    /*
     * Unary Operation(s).
     */
    public final Vector4f selfNegate() {
        return selfMultiply(-1.0f);
    }

    public final Vector4f selfNormalize() {
        return selfMultiply(1.0f / magnitude());
    }

    public final float magnitude() {
        return (float) Math.sqrt((double) (x * x + y * y + z * z + w * w));
    }

    /*
     * Binary Operation(s).
     */
    public final Vector4f selfAdd(Vector4f vector) {
        x += vector.x;
        y += vector.y;
        z += vector.z;
        w += vector.w;

        return this;
    }

    public final Vector4f selfSubtract(Vector4f vector) {
        x -= vector.x;
        y -= vector.y;
        z -= vector.z;
        w -= vector.w;

        return this;
    }

    public final Vector4f selfMultiply(Vector4f vector) {
        x *= vector.x;
        y *= vector.y;
        z *= vector.z;
        w *= vector.w;

        return this;
    }

    public final Vector4f selfMultiply(float scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
        w *= scalar;

        return this;
    }

    /*
     * Static Method(s)
     */
    public static float dot(Vector4f u, Vector4f v) {
        return u.x * v.x + u.y * v.y + u.z * v.z + u.w * v.w;
    }

    public static float dot3(Vector4f u, Vector4f v) {
        return u.x * v.x + u.y * v.y + u.z * v.z;
    }

    public static Vector4f cross(Vector4f u, Vector4f v) {
        float vv_x = u.y * v.z - u.z * v.y;
        float vv_y = u.z * v.x - u.x * v.z;
        float vv_z = u.x * v.y - u.y * v.x;

        return new Vector4f(vv_x, vv_y, vv_z, 1.0f);
    }

    public static Vector3f cross3(Vector4f u, Vector4f v) {
        float vv_x = u.y * v.z - u.z * v.y;
        float vv_y = u.z * v.x - u.x * v.z;
        float vv_z = u.x * v.y - u.y * v.x;

        return new Vector3f(vv_x, vv_y, vv_z);
    }

    public static float crossValue(Vector4f u, Vector4f v) {
        float vv_x = u.y * v.z - u.z * v.y;
        float vv_y = u.z * v.x - u.x * v.z;
        float vv_z = u.x * v.y - u.y * v.x;

        return vv_x + vv_y + vv_z;
    }

    /*
     * Create a copy of this vector.
     */
    public final Vector4f copy() {
        return new Vector4f(this);
    }

    /*
     * toString Implementation
     */
    @Override
    public final String toString() {
        return x + " " + y + " " + z + " " + w;
    }
}
