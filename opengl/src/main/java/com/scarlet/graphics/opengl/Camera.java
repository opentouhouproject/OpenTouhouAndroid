package com.scarlet.graphics.opengl;

import android.util.Log;

import com.scarlet.math.MathUtil;
import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

/*
 * Represents a camera for 3D graphics.
 */
public class Camera {
    // Lazy Update of matrices.
    private boolean dirty = false;

    // Matrices.
    private Matrix4f viewMatrix;
    private Matrix4f projectionMatrix;

    private Matrix4f invViewMatrix;
    private Matrix4f invProjectionMatrix;

    // Vectors
    private Vector4f cameraPosition;
    private Vector4f lookAtPosition;
    private Vector4f rightDirection;

    /*
     * Constructor(s).
     */
    public Camera(float pX, float pY, float pZ, float lX, float lY, float lZ) {
        // Set the camera position.
        cameraPosition = new Vector4f(pX, pY, pZ, 0);

        // Set the position we are looking at.
        lookAtPosition = new Vector4f(lX, lY, lZ, 0);

        // Set the positive x axis direction.
        rightDirection = new Vector4f(1.0f, 0.0f, 0.0f, 0.0f); // x coordinate is right

        // Initialize the view matrix.
        viewMatrix = Matrix4f.getIdentity();
        invViewMatrix = Matrix4f.getIdentity();
        // Compute the view matrix.
        updateViewMatrix(cameraPosition, lookAtPosition, rightDirection);
        updateInverseViewMatrix(cameraPosition, lookAtPosition, rightDirection);

        // Initialize the projection matrix.
        projectionMatrix = Matrix4f.getIdentity();
        invProjectionMatrix = Matrix4f.getIdentity();
    }

    public Camera(float pX, float pY, float pZ, float lX, float lY, float lZ, float rX, float rY, float rZ) {
        // Set the camera position.
        cameraPosition = new Vector4f(pX, pY, pZ, 0);

        // Set the position we are looking at.
        lookAtPosition = new Vector4f(lX, lY, lZ, 0);

        // Set the positive x axis direction.
        rightDirection = new Vector4f(rX, rY, rZ, 0);

        // Initialize the view matrix.
        viewMatrix = Matrix4f.getIdentity();
        invViewMatrix = Matrix4f.getIdentity();
        // Compute the view matrix.
        updateViewMatrix(cameraPosition, lookAtPosition, rightDirection);
        updateInverseViewMatrix(cameraPosition, lookAtPosition, rightDirection);

        // Initialize the projection matrix.
        projectionMatrix = Matrix4f.getIdentity();
        invProjectionMatrix = Matrix4f.getIdentity();
    }

    /*
     * Getter(s).
     */
    Vector4f getCameraPosition() {
        return cameraPosition;
    }

    Vector4f getLookAtPosition() {
        return lookAtPosition;
    }

    Vector4f getRight() {
        return rightDirection;
    }

    public Matrix4f getInvProjectionMatrix() { return invProjectionMatrix; }

    public Matrix4f getInvViewMatrix() { return invViewMatrix; }

    /*
     * Setter(s).
     */
    public void setCameraPosition(float x, float y, float z) {
        // Set the camera position.
        cameraPosition.set(x, y, z, 0);

        // Flag the view matrix for an update.
        dirty = true;
    }

    public void setLookAtPosition(float x, float y, float z) {
        // Set the look at position.
        lookAtPosition.set(x, y, z, 0);

        // Flag the view matrix for an update.
        dirty = true;
    }

    public void setRightDirection(float x, float y, float z) {
        // Update the up vector.
        rightDirection.set(x, y, z, 0);

        // Flag the view matrix for an update.
        dirty = true;
    }

    /*
     * View Matrix
     */
    public Matrix4f getViewMatrix() {
        if (dirty) {
            updateViewMatrix(cameraPosition, lookAtPosition, rightDirection);
            updateInverseViewMatrix(cameraPosition, lookAtPosition, rightDirection);

            dirty = false;
        }

        return viewMatrix;
    }

    private void updateViewMatrix(Vector4f position, Vector4f lookAtPoint, Vector4f rightVector) {
        // Compute the forward vector.
        Vector4f forward = lookAtPoint.subtract(position);
        forward.selfNormalize();

        // Compute the up vector.
        Vector4f up = new Vector4f(Vector4f.cross(rightVector, forward), 0);
        up.selfNormalize();

        // Compute the orthogonal right vector.
        Vector4f right = new Vector4f(Vector4f.cross(forward, up), 0);

        // column 0
        viewMatrix.setValue(right.x, 0, 0);
        viewMatrix.setValue(up.x, 1, 0);
        viewMatrix.setValue(-forward.x, 2, 0);
        viewMatrix.setValue(0, 3, 0);

        // column 1
        viewMatrix.setValue(right.y, 0, 1);
        viewMatrix.setValue(up.y, 1, 1);
        viewMatrix.setValue(-forward.y, 2, 1);
        viewMatrix.setValue(0, 3, 1);

        // column 2
        viewMatrix.setValue(right.z, 0, 2);
        viewMatrix.setValue(up.z, 1, 2);
        viewMatrix.setValue(-forward.z, 2, 2);
        viewMatrix.setValue(0, 3, 2);

        // column 3
        viewMatrix.setValue(-(right.x * position.x + right.y * position.y + right.z * position.z), 0, 3);
        viewMatrix.setValue(-(up.x * position.x + up.y * position.y + up.z * position.z), 1, 3);
        viewMatrix.setValue((forward.x * position.x + forward.y * position.y + forward.z * position.z), 2, 3);
        viewMatrix.setValue(1, 3, 3);
    }

    /*
     * Set inverse view matrix.
     */
    private void updateInverseViewMatrix(Vector4f position, Vector4f lookAtPoint, Vector4f rightVector) {
        // Compute the forward vector.
        Vector4f forward = lookAtPoint.subtract(position);
        forward.selfNormalize();

        // Compute the up vector.
        Vector4f up = new Vector4f(Vector4f.cross(rightVector, forward), 0);
        up.selfNormalize();

        // Compute the orthogonal right vector.
        Vector4f right = new Vector4f(Vector4f.cross(forward, up), 0);

        // column 0
        invViewMatrix.setValue(right.x, 0, 0);
        invViewMatrix.setValue(right.y, 1, 0);
        invViewMatrix.setValue(right.z, 2, 0);
        invViewMatrix.setValue(0, 3, 0);

        // column 1
        invViewMatrix.setValue(up.x, 0, 1);
        invViewMatrix.setValue(up.y, 1, 1);
        invViewMatrix.setValue(up.z, 2, 1);
        invViewMatrix.setValue(0, 3, 1);

        // column 2
        invViewMatrix.setValue(-forward.x, 0, 2);
        invViewMatrix.setValue(-forward.y, 1, 2);
        invViewMatrix.setValue(-forward.z, 2, 2);
        invViewMatrix.setValue(0, 3, 2);

        // column 3
        invViewMatrix.setValue(position.x, 0, 3);
        invViewMatrix.setValue(position.y, 1, 3);
        invViewMatrix.setValue(position.z, 2, 3);
        invViewMatrix.setValue(1, 3, 3);
    }


    /*
     * Projection Matrix
     */

    /*
     * Retrieves the current projection matrix.
     */
    public Matrix4f getProjectionMatrix()
    {
        return projectionMatrix;
    }

    /*
     * Defines a perspective projection matrix using FoV and the aspect ration.
     */
    public void setSymmetricPerspectiveProjectionMatrix(float fieldOfView, float aspectRatio, float nearPlane, float farPlane) {
        projectionMatrix.reset(0);

        float cot = (float) (1 / Math.tan(MathUtil.degreesToRadians(fieldOfView / 2)));

        // column 0
        projectionMatrix.setValue(cot / aspectRatio, 0, 0);

        // column 1
        projectionMatrix.setValue(cot, 1, 1);

        // column 2
        projectionMatrix.setValue((nearPlane + farPlane) / (nearPlane - farPlane), 2, 2);
        projectionMatrix.setValue((2 * nearPlane * farPlane) / (nearPlane - farPlane), 3, 2);

        // column 3
        projectionMatrix.setValue(-1, 2, 3);
        projectionMatrix.setValue(0, 3, 3);
    }

    /*
     * Defines a perspective projection matrix using the aspect ratio.
     */
    public void setPerspectiveProjectionMatrix(float left, float right, float bottom, float top, float near, float far) {
        projectionMatrix.reset(0);

        // column 0
        projectionMatrix.setValue(2 * near / (right - left), 0, 0);

        // column 1
        projectionMatrix.setValue(2 * near / (top - bottom), 1, 1);

        // column 2
        projectionMatrix.setValue((right + left) / (right - left), 0, 2);
        projectionMatrix.setValue((top + bottom) / (top - bottom), 1, 2);
        projectionMatrix.setValue(-(far + near) / (far - near), 2, 2);
        projectionMatrix.setValue(-1, 3, 2);

        // column 3
        projectionMatrix.setValue(-2 * far * near / (far - near), 2, 3);
    }

    /*
     * Defines an orthographic projection using the aspect ratio.
     */
    public void setOrthographicProjection(float left, float right, float bottom, float top, float near, float far) {
        projectionMatrix.reset(0);

        // column 0
        projectionMatrix.setValue(2 / (right - left), 0, 0);

        // column 1
        projectionMatrix.setValue(2 / (top - bottom), 1, 1);

        // column 2
        projectionMatrix.setValue(-2 / (far - near), 2, 2);

        // column 3
        projectionMatrix.setValue(-(right + left) / (right - left), 0, 3);
        projectionMatrix.setValue(-(top + bottom) / (top - bottom), 1, 3);
        projectionMatrix.setValue(-(far + near) / (far - near), 2, 3);
        projectionMatrix.setValue(1, 3, 3);
    }

    /*
     * Inverse Projection Matrix
     */
    public void setInversePerspectiveProjectionMatrix(float left, float right, float bottom, float top, float near, float far) {
        invProjectionMatrix.reset(0);

        // column 0
        invProjectionMatrix.setValue((right - left) / (2 * near), 0, 0);

        // column 1
        invProjectionMatrix.setValue((top - bottom) / (2 * near), 1, 1);

        // column 2
        invProjectionMatrix.setValue((near - far) / (2 * far * near), 3, 2);

        // column 3
        invProjectionMatrix.setValue((right + left) / (2 * near), 0, 3);
        invProjectionMatrix.setValue((top + bottom) / (2 * near), 1, 3);
        invProjectionMatrix.setValue(-1, 2, 3);
        invProjectionMatrix.setValue((far + near) / (2 * far * near), 3, 3);
    }

    /*
     * Convert screen coordinates to normalised device coordinates.
     */
    public Vector4f convertToNDC(float x, float y, int screenWidth, int screenHeight) {
        float nX = 2 * (x / (float) screenWidth) - 1;
        float nY = 1 - 2 * (y / (float) screenHeight);

        //Log.d("NDC Conversion","Point: " + x + " " + y);
        //Log.d("NDC Conversion", "NDC: " + nX + " " + nY + " " + -1.0f);

        return new Vector4f(nX, nY, -1.0f, 1.0f);
    }

    /*
     * Convert NDC coordinates to World Coordinates.
     * Compute intersection of ray and xy-plane.
     */
    public Vector3f unProject(Vector4f ndc, float z) {
        Vector4f viewSpaceCoord = Matrix4f.multiply(getInvProjectionMatrix(), ndc);
        //Log.d("unProject", "P: " + pCoord.x + " " + pCoord.y + " " + pCoord.z + " " + pCoord.w);

        Vector4f worldSpaceCoord = Matrix4f.multiply(getInvViewMatrix(), viewSpaceCoord);
        //Log.d("unProject", "V: " + vCoord.x + " " + vCoord.y + " " + vCoord.z + " " + vCoord.w);

        Vector3f p0 = new Vector3f(0, 0, z); // position inside plane
        Vector3f n = new Vector3f(0, 0, 1); // normal to plane
        Vector3f e = new Vector3f(cameraPosition); // camera position
        Vector3f w = new Vector3f(worldSpaceCoord.x, worldSpaceCoord.y, worldSpaceCoord.z); // loaction of screen touch

        // Compute intersection of the plane and ray.
        Vector3f r = w.subtract(e);
        float t = p0.subtract(e).dot(n) / r.dot(n);
        return r.multiply(t).add(e);
    }
}