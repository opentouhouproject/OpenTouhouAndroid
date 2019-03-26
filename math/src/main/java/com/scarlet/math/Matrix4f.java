package com.scarlet.math;

/**
 * 4x4 matrix of floats.
 */

public class Matrix4f
{
    private static final int m11 = 0;
    private static final int m21 = 1;
    private static final int m31 = 2;
    private static final int m41 = 3;

    private static final int m12 = 4;
    private static final int m22 = 5;
    private static final int m32 = 6;
    private static final int m42 = 7;

    private static final int m13 = 8;
    private static final int m23 = 9;
    private static final int m33 = 10;
    private static final int m43 = 11;

    private static final int m14 = 12;
    private static final int m24 = 13;
    private static final int m34 = 14;
    private static final int m44 = 15;

    private float[] values;

    // Default Constructor
    public Matrix4f() {
        values = new float[16]; // 4x4 matrix, column order, default value to 0.0
    }

    // Pass in column values as vectors
    public Matrix4f(Vector4f v0, Vector4f v1, Vector4f v2, Vector4f v3) {
        values = new float[16];

        values[m11] = v0.x;
        values[m21] = v0.y;
        values[m31] = v0.z;
        values[m41] = v0.w;

        values[m12] = v1.x;
        values[m22] = v1.y;
        values[m32] = v1.z;
        values[m42] = v1.w;

        values[m13] = v2.x;
        values[m23] = v2.y;
        values[m33] = v2.z;
        values[m43] = v2.w;

        values[m14] = v3.x;
        values[m24] = v3.y;
        values[m34] = v3.z;
        values[m44] = v3.w;
    }

    // Copy Constructor
    public Matrix4f(Matrix4f matrix) {
        values = new float[16];

        values[m11] = matrix.values[m11];
        values[m21] = matrix.values[m21];
        values[m31] = matrix.values[m31];
        values[m41] = matrix.values[m41];

        values[m12] = matrix.values[m12];
        values[m22] = matrix.values[m22];
        values[m32] = matrix.values[m32];
        values[m42] = matrix.values[m42];

        values[m13] = matrix.values[m13];
        values[m23] = matrix.values[m23];
        values[m33] = matrix.values[m33];
        values[m43] = matrix.values[m43];

        values[m14] = matrix.values[m14];
        values[m24] = matrix.values[m24];
        values[m34] = matrix.values[m34];
        values[m44] = matrix.values[m44];
    }

    // Setters
    public void setColumn(Vector4f v, int column) {
        values[4 * column] = v.x;
        values[4 * column + 1] = v.y;
        values[4 * column + 2] = v.z;
        values[4 * column + 3] = v.w;
    }

    public void setValue(float value, int row, int column) {
        values[4 * column + row] = value;
    }

    public float[] getArray() {
        return values.clone();
    }

    // Static Methods

    /**
     * Matrix Creation Methods
     * All methods return a new matrix
     */

    // Return a new identity matrix.
    public static Matrix4f getIdentity()
    {
        Matrix4f matrix = new Matrix4f();

        matrix.values[m11] = 1;
        matrix.values[m22] = 1;
        matrix.values[m33] = 1;
        matrix.values[m44] = 1;

        return matrix;
    }

    // Return a new scale matrix.
    public static Matrix4f scaleMatrix(float scaleX, float scaleY, float scaleZ)
    {
        Matrix4f matrix = new Matrix4f();

        matrix.values[m11] = scaleX;
        matrix.values[m22] = scaleY;
        matrix.values[m33] = scaleZ;
        matrix.values[m44] = 1;

        return matrix;
    }

    // Return a new translation matrix.
    public static Matrix4f translateMatrix(float x, float y, float z)
    {
        Matrix4f matrix = Matrix4f.getIdentity();

        matrix.values[m14] = x;
        matrix.values[m24] = y;
        matrix.values[m34] = z;

        return matrix;
    }

    /**
     * Return a new pitch rotation matrix.
     * If isDegrees is true, then the angle is given in degrees.
     * Otherwise the angle is given in radians.
     */
    public static Matrix4f getXAxisRotation(float angle, boolean isDegrees)
    {
        if (isDegrees) angle = MathUtil.degreesToRadians(angle);

        float alpha = (float) Math.sin(angle);
        float beta = (float) Math.cos(angle);

        Matrix4f matrix = Matrix4f.getIdentity();

        matrix.values[m22] = beta;
        matrix.values[m32] = -alpha;
        matrix.values[m23] = alpha;
        matrix.values[m33] = beta;

        return matrix;
    }

    /**
     * Returns the yaw rotation matrix.
     * If isDegrees is true, then angle is given in degrees.
     * Otherwise the angle is given in radians.
     */
    public static Matrix4f getYAxisRotation(float angle, boolean isDegrees)
    {
        if (isDegrees) angle = MathUtil.degreesToRadians(angle);

        float alpha = (float) Math.sin(angle);
        float beta = (float) Math.cos(angle);

        Matrix4f matrix = Matrix4f.getIdentity();

        matrix.values[m11] = beta;
        matrix.values[m31] = alpha;
        matrix.values[m13] = -alpha;
        matrix.values[m33] = beta;

        return matrix;
    }

    /**
     * Returns the roll rotation matrix.
     * If isDegrees is true, then angle is given in degrees.
     * Otherwise the angle is given in radians.
     */
    public static Matrix4f getZAxisRotation(float angle, boolean isDegrees)
    {
        if (isDegrees) angle = MathUtil.degreesToRadians(angle);

        float alpha = (float) Math.sin(angle);
        float beta = (float) Math.cos(angle);

        Matrix4f matrix = Matrix4f.getIdentity();

        matrix.values[m11] = beta;
        matrix.values[m21] = -alpha;
        matrix.values[m12] = alpha;
        matrix.values[m22] = beta;

        return matrix;
    }

    /**
     * Set the matrix as a roll, pitch and yaw rotation matrix.
     * The resulting matrix is M = Mat(yaw) * mat(pitch) * mat(roll) .
     * If degree is true, then all angles are given in degrees.
     * Otherwise angle is given in radians.
     */
    public static Matrix4f rotateRollPitchYaw(float angleRoll, float anglePitch, float angleYaw, boolean isDegrees)
    {
        if (isDegrees)
        {
            angleRoll = MathUtil.degreesToRadians(angleRoll);
            anglePitch = MathUtil.degreesToRadians(anglePitch);
            angleYaw = MathUtil.degreesToRadians(angleYaw);
        }

        Matrix4f matrix = multiply(getYAxisRotation(angleYaw, isDegrees), multiply(getXAxisRotation(anglePitch, isDegrees), getZAxisRotation(angleRoll, isDegrees)));

        return matrix;
    }

    /**
     * Returns the rotation matrix around a given vector.
     * If isDegrees is true, then angle is given in degrees.
     * Otherwise the angle is given in radians.
     */
    public static Matrix4f rotateVector(Vector3f v, float angle, boolean isDegrees)
    {
        if (isDegrees) angle = MathUtil.degreesToRadians(angle);

        v.normalize();
        float x = v.x;
        float y = v.y;
        float z = v.z;
        float s = (float) Math.sin(angle);
        float c = (float) Math.cos(angle);
        float r = (1 - c);

        Matrix4f matrix = Matrix4f.getIdentity();

        matrix.values[m11] = x * x * r + c;
        matrix.values[m21] = x * y * r + z * s;
        matrix.values[m31] = x * z * r - y * s;
        matrix.values[m41] = 0;

        matrix.values[m12] = y * x * r - z * s;
        matrix.values[m22] = y * y * r + c;
        matrix.values[m32] = y * z * r + x * s;
        matrix.values[m42] = 0;

        matrix.values[m13] = z * x * r + y * s;
        matrix.values[m23] = z * y * r - x * s;
        matrix.values[m33] = z * z * r + c;
        matrix.values[m43] = 0;

        matrix.values[m14] = 0;
        matrix.values[m24] = 0;
        matrix.values[m34] = 0;
        matrix.values[m44] = 1;

        return matrix;
    }

    /**
     * Set the matrix as a rotation around a vector where only a rotation vector is given.
     * If isDegrees is true, then all angles are given in degrees.
     * Otherwise the angle is given in radians.
     */
    public static Matrix4f rotateVector(Vector4f v, float angle, boolean isDegrees)
    {
        Vector3f u = new Vector3f(v.x, v.y, v.z);

        return Matrix4f.rotateVector(u, angle, isDegrees);
    }


    /**
     * Matrix Operation Methods
     * All methods return a new matrix.
     */

    // Return the transpose of the given matrix.
    public static Matrix4f transpose(Matrix4f matrix)
    {
        Matrix4f result = new Matrix4f();

        for (int i = 0; i < 4; i++)
        {
            result.values[4 * i + 0] = matrix.values[0 + i];
            result.values[4 * i + 1] = matrix.values[4 + i];
            result.values[4 * i + 2] = matrix.values[8 + i];
            result.values[4 * i + 3] = matrix.values[12 + i];
        }

        return result;
    }

    // Multiply two matrices.
    public static Matrix4f multiply(Matrix4f lhs, Matrix4f rhs)
    {
        Matrix4f result = new Matrix4f();

        // Column 1
        result.values[m11] = lhs.values[m11] * rhs.values[m11] + lhs.values[m12] * rhs.values[m21] + lhs.values[m13] * rhs.values[m31] + lhs.values[m14] * rhs.values[m41];
        result.values[m21] = lhs.values[m21] * rhs.values[m11] + lhs.values[m22] * rhs.values[m21] + lhs.values[m23] * rhs.values[m31] + lhs.values[m24] * rhs.values[m41];
        result.values[m31] = lhs.values[m31] * rhs.values[m11] + lhs.values[m32] * rhs.values[m21] + lhs.values[m33] * rhs.values[m31] + lhs.values[m34] * rhs.values[m41];
        result.values[m41] = lhs.values[m41] * rhs.values[m11] + lhs.values[m42] * rhs.values[m21] + lhs.values[m43] * rhs.values[m31] + lhs.values[m44] * rhs.values[m41];

        // Column 2
        result.values[m12] = lhs.values[m11] * rhs.values[m12] + lhs.values[m12] * rhs.values[m22] + lhs.values[m13] * rhs.values[m32] + lhs.values[m14] * rhs.values[m42];
        result.values[m22] = lhs.values[m21] * rhs.values[m12] + lhs.values[m22] * rhs.values[m22] + lhs.values[m23] * rhs.values[m32] + lhs.values[m24] * rhs.values[m42];
        result.values[m32] = lhs.values[m31] * rhs.values[m12] + lhs.values[m32] * rhs.values[m22] + lhs.values[m33] * rhs.values[m32] + lhs.values[m34] * rhs.values[m42];
        result.values[m42] = lhs.values[m41] * rhs.values[m12] + lhs.values[m42] * rhs.values[m22] + lhs.values[m43] * rhs.values[m32] + lhs.values[m44] * rhs.values[m42];

        // Column 3
        result.values[m13] = lhs.values[m11] * rhs.values[m13] + lhs.values[m12] * rhs.values[m23] + lhs.values[m13] * rhs.values[m33] + lhs.values[m14] * rhs.values[m43];
        result.values[m23] = lhs.values[m21] * rhs.values[m13] + lhs.values[m22] * rhs.values[m23] + lhs.values[m23] * rhs.values[m33] + lhs.values[m24] * rhs.values[m43];
        result.values[m33] = lhs.values[m31] * rhs.values[m13] + lhs.values[m32] * rhs.values[m23] + lhs.values[m33] * rhs.values[m33] + lhs.values[m34] * rhs.values[m43];
        result.values[m43] = lhs.values[m41] * rhs.values[m13] + lhs.values[m42] * rhs.values[m23] + lhs.values[m43] * rhs.values[m33] + lhs.values[m44] * rhs.values[m43];

        // Column 4
        result.values[m14] = lhs.values[m11] * rhs.values[m14] + lhs.values[m12] * rhs.values[m24] + lhs.values[m13] * rhs.values[m34] + lhs.values[m14] * rhs.values[m44];
        result.values[m24] = lhs.values[m21] * rhs.values[m14] + lhs.values[m22] * rhs.values[m24] + lhs.values[m23] * rhs.values[m34] + lhs.values[m24] * rhs.values[m44];
        result.values[m34] = lhs.values[m31] * rhs.values[m14] + lhs.values[m32] * rhs.values[m24] + lhs.values[m33] * rhs.values[m34] + lhs.values[m34] * rhs.values[m44];
        result.values[m44] = lhs.values[m41] * rhs.values[m14] + lhs.values[m42] * rhs.values[m24] + lhs.values[m43] * rhs.values[m34] + lhs.values[m44] * rhs.values[m44];

        return result;
    }

    // Multiply a vector by a matrix.
    public static Vector4f multiply(Matrix4f matrix, Vector4f vector)
    {
        Vector4f result = new Vector4f();

        result.x = matrix.values[m11] * vector.x + matrix.values[m12] * vector.y + matrix.values[m13] * vector.z + matrix.values[m14] * vector.w;
        result.y = matrix.values[m21] * vector.x + matrix.values[m22] * vector.y + matrix.values[m23] * vector.z + matrix.values[m24] * vector.w;
        result.z = matrix.values[m31] * vector.x + matrix.values[m32] * vector.y + matrix.values[m33] * vector.z + matrix.values[m34] * vector.w;
        result.w = matrix.values[m41] * vector.x + matrix.values[m42] * vector.y + matrix.values[m43] * vector.z + matrix.values[m44] * vector.w;

        return result;
    }


    /**
     * Self Matrix Operations
     * All methods mutate this matrix.
     */

    // Multiply this matrix by a translation matrix.
    public void translate(float x, float y, float z)
    {
        // Column 1
        values[m11] = values[m11] + x * values[m41];
        values[m21] = values[m21] + y * values[m41];
        values[m31] = values[m31] + z * values[m41];
        values[m41] = values[m41];

        // Column 2
        values[m12] = values[m12] + x * values[m42];
        values[m22] = values[m22] + y * values[m42];
        values[m32] = values[m32] + z * values[m42];
        values[m42] = values[m42];

        // Column 3
        values[m13] = values[m13] + x * values[m43];
        values[m23] = values[m23] + y * values[m43];
        values[m33] = values[m33] + z * values[m43];
        values[m43] = values[m43];

        // Column 4
        values[m14] = values[m14] + x * values[m44];
        values[m24] = values[m24] + y * values[m44];
        values[m34] = values[m34] + z * values[m44];
        values[m44] = values[m44];
    }

    /**
     * Multiply by the scale matrix.
     */
    public Matrix4f scale(float scaleX, float scaleY, float scaleZ)
    {
        // Column 1
        values[m11] = values[m11] * scaleX;
        values[m21] = values[m21] * scaleY;
        values[m31] = values[m31] * scaleZ;

        // Column 2
        values[m12] = values[m12] * scaleX;
        values[m22] = values[m22] * scaleY;
        values[m32] = values[m32] * scaleZ;

        // Column 3
        values[m13] = values[m13] * scaleX;
        values[m23] = values[m23] * scaleY;
        values[m33] = values[m33] * scaleZ;

        // Column 4
        values[m14] = values[m14] * scaleX;
        values[m24] = values[m24] * scaleY;
        values[m34] = values[m34] * scaleZ;

        return this;
    }

    /**
     * Returns the pitch rotation matrix.
     * If isDegrees is true, then the angle is given in degrees.
     * Otherwise the angle is given in radians.
     */
    public Matrix4f rotateX(float angle, boolean isDegrees)
    {
        if (isDegrees) angle = MathUtil.degreesToRadians(angle);

        float alpha = (float) Math.sin(angle);
        float beta = (float) Math.cos(angle);

        // Column 1
        float t21 = beta * values[m21] + alpha * values[m31];
        float t31 = -alpha * values[m21] + beta * values[m31];

        // Column 2
        float t22 = beta * values[m22] + alpha * values[m32];
        float t32 = -alpha * values[m22] + beta * values[m32];

        // Column 3
        float t23 = beta * values[m23] + alpha * values[m33];
        float t33 = -alpha * values[m23] + beta * values[m33];

        // Column 4
        float t24 = beta * values[m24] + alpha * values[m34];
        float t34 = -alpha * values[m24] + beta * values[m34];

        // Set values.
        values[m21] = t21;
        values[m31] = t31;
        values[m22] = t22;
        values[m32] = t32;
        values[m23] = t23;
        values[m33] = t33;
        values[m24] = t24;
        values[m34] = t34;

        return this;
    }

    /**
     * Returns the yaw rotation matrix.
     * If isDegrees is true, then angle is given in degrees.
     * Otherwise the angle is given in radians.
     */
    public Matrix4f rotateY(float angle, boolean isDegrees)
    {
        if (isDegrees) angle = MathUtil.degreesToRadians(angle);

        float alpha = (float) Math.sin(angle);
        float beta = (float) Math.cos(angle);

        // Column 1
        float t11 = beta * values[m11] + -alpha * values[m31];
        float t31 = alpha * values[m11] + beta * values[m31];

        // Column 2
        float t12 = beta * values[m12] + -alpha * values[m32];
        float t32 = alpha * values[m12] + beta * values[m32];

        // Column 3
        float t13 = beta * values[m13] + -alpha * values[m33];
        float t33 = alpha * values[m13] + beta * values[m33];

        // Column 4
        float t14 = beta * values[m14] + -alpha * values[m34];
        float t34 = alpha * values[m14] + beta * values[m34];

        // Set Values.
        values[m11] = t11;
        values[m31] = t31;
        values[m12] = t12;
        values[m32] = t32;
        values[m13] = t13;
        values[m33] = t33;
        values[m14] = t14;
        values[m34] = t34;

        return this;
    }

    /**
     * Returns the roll rotation matrix.
     * If isDegrees is true, then angle is given in degrees.
     * Otherwise the angle is given in radians.
     */
    public Matrix4f rotateZ(float angle, boolean isDegrees)
    {
        if (isDegrees) angle = MathUtil.degreesToRadians(angle);

        float alpha = (float) Math.sin(angle);
        float beta = (float) Math.cos(angle);

        // Column 1
        float t11 = beta * values[m11] + alpha * values[m21];
        float t21 = -alpha * values[m11] + beta * values[m21];

        // Column 2
        float t12 = beta * values[m12] + alpha * values[m22];
        float t22 = -alpha * values[m12] + beta * values[m22];

        // Column 3
        float t13 = beta * values[m13] + alpha * values[m23];
        float t23 = -alpha * values[m13] + beta * values[m23];

        // Column 4
        float t14 = beta * values[m14] + alpha * values[m24];
        float t24 = -alpha * values[m14] + beta * values[m24];

        // Set values.
        values[m11] = t11;
        values[m21] = t21;
        values[m12] = t12;
        values[m22] = t22;
        values[m13] = t13;
        values[m23] = t23;
        values[m14] = t14;
        values[m24] = t24;

        return this;
    }

    /**
     * Rotate about a given vector.
     */
    public void rotate(Vector3f v, float angle, boolean isDegrees) {
        if (isDegrees) angle = MathUtil.degreesToRadians(angle);

        v.normalize();
        float x = v.x;
        float y = v.y;
        float z = v.z;
        float s = (float) Math.sin(angle);
        float c = (float) Math.cos(angle);
        float r = (1 - c);

        Matrix4f matrix = Matrix4f.getIdentity();

        matrix.values[m11] = x * x * r + c;
        matrix.values[m21] = x * y * r + z * s;
        matrix.values[m31] = x * z * r - y * s;
        matrix.values[m41] = 0;

        matrix.values[m12] = y * x * r - z * s;
        matrix.values[m22] = y * y * r + c;
        matrix.values[m32] = y * z * r + x * s;
        matrix.values[m42] = 0;

        matrix.values[m13] = z * x * r + y * s;
        matrix.values[m23] = z * y * r - x * s;
        matrix.values[m33] = z * z * r + c;
        matrix.values[m43] = 0;

        matrix.values[m14] = 0;
        matrix.values[m24] = 0;
        matrix.values[m34] = 0;
        matrix.values[m44] = 1;

        values = Matrix4f.multiply(matrix, this).getArray().clone(); // fix later
    }

    // Sets the matrix to the identity matrix.
    public Matrix4f setIdentity()
    {
        reset(0);

        values[m11] = 1;
        values[m22] = 1;
        values[m33] = 1;
        values[m44] = 1;

        return this;
    }

    // resets all the values of the matrix to "value"
    public void reset(float value) {
        for (int i = 0; i < 16; i++) {
            values[i] = value;
        }
    }
}