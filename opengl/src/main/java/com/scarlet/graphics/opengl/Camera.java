package com.scarlet.graphics.opengl;

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
    private Vector3f cameraPosition;
    private Vector3f lookAtPosition;
    private Vector3f rightDirection;

    /*
     * Constructor(s).
     */
    public Camera(float pX, float pY, float pZ, float lX, float lY, float lZ) {
        // Set the camera position.
        cameraPosition = new Vector3f(pX, pY, pZ);

        // Set the position we are looking at.
        lookAtPosition = new Vector3f(lX, lY, lZ);

        // Set the positive x axis direction.
        rightDirection = new Vector3f(1.0f, 0.0f, 0.0f); // x coordinate is right

        // Initialize the view matrix.
        viewMatrix = Matrix4f.identityMatrix();
        invViewMatrix = Matrix4f.identityMatrix();

        // Compute the view matrix.
        updateViewMatrix(cameraPosition, lookAtPosition, rightDirection);
        updateInverseViewMatrix(cameraPosition, lookAtPosition, rightDirection);

        // Initialize the projection matrix.
        projectionMatrix = Matrix4f.identityMatrix();
        invProjectionMatrix = Matrix4f.identityMatrix();
    }

    public Camera(float pX, float pY, float pZ, float lX, float lY, float lZ, float rX, float rY, float rZ) {
        // Set the camera position.
        cameraPosition = new Vector3f(pX, pY, pZ);

        // Set the position we are looking at.
        lookAtPosition = new Vector3f(lX, lY, lZ);

        // Set the positive x axis direction.
        rightDirection = new Vector3f(rX, rY, rZ);

        // Initialize the view matrix.
        viewMatrix = Matrix4f.identityMatrix();
        invViewMatrix = Matrix4f.identityMatrix();

        // Compute the view matrix.
        updateViewMatrix(cameraPosition, lookAtPosition, rightDirection);
        updateInverseViewMatrix(cameraPosition, lookAtPosition, rightDirection);

        // Initialize the projection matrix.
        projectionMatrix = Matrix4f.identityMatrix();
        invProjectionMatrix = Matrix4f.identityMatrix();
    }

    /*
     * Getter(s).
     */
    Vector3f getCameraPosition() {
        return cameraPosition;
    }

    Vector3f getLookAtPosition() {
        return lookAtPosition;
    }

    Vector3f getRight() {
        return rightDirection;
    }

    public Matrix4f getInvProjectionMatrix() { return invProjectionMatrix; }

    public Matrix4f getInvViewMatrix() { return invViewMatrix; }

    /*
     * Setter(s).
     */
    public void setCameraPosition(float x, float y, float z) {
        // Set the camera position.
        cameraPosition.set(x, y, z);

        // Flag the view matrix for an update.
        dirty = true;
    }

    public void setLookAtPosition(float x, float y, float z) {
        // Set the look at position.
        lookAtPosition.set(x, y, z);

        // Flag the view matrix for an update.
        dirty = true;
    }

    public void setRightDirection(float x, float y, float z) {
        // Update the up vector.
        rightDirection.set(x, y, z);

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

    private void updateViewMatrix(Vector3f position, Vector3f lookAtPoint, Vector3f rightVector) {
        // Compute the forward vector.
        Vector3f forward = lookAtPoint.subtract(position).selfNormalize();

        // Compute the up vector.
        Vector3f up = rightVector.cross(forward).selfNormalize();

        // Compute the orthogonal right vector.
        Vector3f right = forward.cross(up);

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
    private void updateInverseViewMatrix(Vector3f position, Vector3f lookAtPoint, Vector3f rightVector) {
        // Compute the forward vector.
        Vector3f forward = lookAtPoint.subtract(position).selfNormalize();

        // Compute the up vector.
        Vector3f up = rightVector.cross(forward).selfNormalize();

        // Compute the orthogonal right vector.
        Vector3f right = forward.cross(up);

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
    public Vector3f unProject(Vector4f ndc) {
        Vector4f viewSpaceCoord = getInvProjectionMatrix().multiply(ndc);
        //Log.d("unProject", "P: " + pCoord.x + " " + pCoord.y + " " + pCoord.z + " " + pCoord.w);

        Vector4f worldSpaceCoord = getInvViewMatrix().multiply(viewSpaceCoord);
        //Log.d("unProject", "V: " + vCoord.x + " " + vCoord.y + " " + vCoord.z + " " + vCoord.w);

        return new Vector3f(worldSpaceCoord);
    }

    public Vector3f intersectionPoint(Vector3f planePoint, Vector3f normal, Vector3f touchPoint) {
        Vector3f eye = cameraPosition.copy(); // camera position

        // Compute intersection of the plane and ray.
        Vector3f ray = touchPoint.subtract(eye);
        float t = planePoint.subtract(eye).dot(normal) / ray.dot(normal);
        return ray.multiply(t).add(eye);
    }
}