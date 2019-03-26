package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import opentouhou.com.opentouhouandroid.math.MathUtil;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.math.Vector4f;

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
    private Vector4f lookDirection;
    private Vector4f upDirection;

    /*
     * Constructor(s).
     */
    public Camera(float pX, float pY, float pZ, float lX, float lY, float lZ) {
        // Set the camera position.
        cameraPosition = new Vector4f(pX, pY, pZ, 0);

        // Set the position we are looking at.
        lookAtPosition = new Vector4f(lX, lY, lZ, 0);

        // Compute the look vector using the camera position and look at position.
        lookDirection = new Vector4f(lX - pX, lY - pY, lZ - pZ, 0);
        lookDirection.selfNormalize();

        // Set the up direction.
        upDirection = new Vector4f(0.0f, 1.0f, 0.0f, 0.0f); // y coordinate is up

        // Initialize the view matrix.
        viewMatrix = Matrix4f.getIdentity();
        // Compute the view matrix.
        updateViewMatrix(cameraPosition, lookAtPosition, upDirection);

        // Initialize the projection matrix.
        projectionMatrix = Matrix4f.getIdentity();
        invProjectionMatrix = Matrix4f.getIdentity();
    }

    public Camera(float pX, float pY, float pZ, float lX, float lY, float lZ, float upX, float upY, float upZ) {
        // Set the camera position.
        cameraPosition = new Vector4f(pX, pY, pZ, 0);

        // Set the position we are looking at.
        lookAtPosition = new Vector4f(lX, lY, lZ, 0);

        // Compute the look vector using the camera position and look at position.
        lookDirection = new Vector4f(lX - pX, lY - pY, lZ - pZ, 0);
        lookDirection.selfNormalize();

        // Set the up direction.
        upDirection = new Vector4f(upX, upY, upZ, 0);

        // Initialize the view matrix.
        viewMatrix = Matrix4f.getIdentity();
        // Compute the view matrix.
        updateViewMatrix(cameraPosition, lookAtPosition, upDirection);

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

    Vector4f getLook() {
        return lookDirection;
    }

    Vector4f getUp() {
        return upDirection;
    }

    /*
     * Setter(s).
     */
    public void setCameraPosition(float x, float y, float z) {
        // Set the camera position.
        cameraPosition.set(x, y, z, 0);

        // Update the look vector.
        lookDirection.set(lookAtPosition.x - x, lookAtPosition.y - y, lookAtPosition.z - z, 0);
        lookDirection.selfNormalize();

        // Flag the view matrix for an update.
        dirty = true;
    }

    public void setLookAtPosition(float x, float y, float z) {
        // Set the look at position.
        lookAtPosition.set(x, y, z, 0);

        // Update the look vector.
        lookDirection.set(x - cameraPosition.x, y - cameraPosition.y, z - cameraPosition.z, 0);
        lookDirection.selfNormalize();

        // Flag the view matrix for an update.
        dirty = true;
    }

    public void setUpDirection(float x, float y, float z) {
        // Update the up vector.
        upDirection.set(x, y, z, 0);

        // Flag the view matrix for an update.
        dirty = true;
    }

    /*
     * Movement
     */
    public void moveForward(float unit) {
        cameraPosition.set(cameraPosition.x + unit, cameraPosition.y + unit, cameraPosition.z + unit, 0);
        lookAtPosition.set(lookAtPosition.x + unit, lookAtPosition.y + unit, lookAtPosition.z + unit, 0);

        dirty = true;
    }

    public void rotateCamZ(float angle) {
        lookDirection = Matrix4f.multiply(Matrix4f.getZAxisRotation(angle, true), lookDirection);
        lookDirection.selfNormalize();

        lookAtPosition.set(cameraPosition.x + lookDirection.x,cameraPosition.y + lookDirection.y, cameraPosition.z + lookDirection.z, 0);

        Vector4f up = Matrix4f.multiply(Matrix4f.getZAxisRotation(angle, true), upDirection);
        up.selfNormalize();

        upDirection.set(up.x, up.y, up.z, 0);

        dirty = true;
    }

    public void rotateCamX(float angle) {
        lookDirection = Matrix4f.multiply(Matrix4f.getXAxisRotation(angle, true), lookDirection);
        lookDirection.selfNormalize();

        lookAtPosition.set(cameraPosition.x + lookDirection.x,cameraPosition.y + lookDirection.y, cameraPosition.z + lookDirection.z, 0);

        dirty = true;
    }

    public void rotateCamY(float angle) {
        lookDirection = Matrix4f.multiply(Matrix4f.getYAxisRotation(angle, true), lookDirection);
        lookDirection.selfNormalize();

        lookAtPosition.set(cameraPosition.x + lookDirection.x, cameraPosition.y + lookDirection.y, cameraPosition.z + lookDirection.z, 0);

        dirty = true;
    }

    /*
     * View Matrix
     */
    public Matrix4f getViewMatrix() {
        if (dirty) {
            updateViewMatrix(cameraPosition, lookAtPosition, upDirection);

            dirty = false;
        }

        return viewMatrix;
    }

    private void updateViewMatrix(Vector4f position, Vector4f lookAtPoint, Vector4f upVector) {
        // Compute the forward vector.
        Vector4f forward = lookAtPoint.subtract(position);
        forward.selfNormalize();

        // Compute the right vector.
        Vector4f right = new Vector4f(Vector4f.cross(forward, upVector), 0);
        right.selfNormalize();

        // Compute the orthogonal up vector.
        Vector4f up = new Vector4f(Vector4f.cross(right, forward), 0);

        // column 0
        viewMatrix.setValue(right.x, 0, 0);
        viewMatrix.setValue(right.y, 1, 0);
        viewMatrix.setValue(right.z, 2, 0);
        viewMatrix.setValue(0, 3, 0);

        // column 1
        viewMatrix.setValue(up.x, 0, 1);
        viewMatrix.setValue(up.y, 1, 1);
        viewMatrix.setValue(up.z, 2, 1);
        viewMatrix.setValue(0, 3, 1);

        // column 2
        viewMatrix.setValue(-forward.x, 0, 2);
        viewMatrix.setValue(-forward.y, 1, 2);
        viewMatrix.setValue(-forward.z, 2, 2);
        viewMatrix.setValue(0, 3, 2);

        // column 3
        viewMatrix.setValue(-position.x, 0, 3);
        viewMatrix.setValue(-position.y, 1, 3);
        viewMatrix.setValue(-position.z, 2, 3);
        viewMatrix.setValue(1, 3, 3);
    }

    // Projection Matrix

    // Retrieves the current projection matrix.
    public Matrix4f getProjectionMatrix()
    {
        return projectionMatrix;
    }

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

    public void setFrustumMatrix(float left, float right, float bottom, float top, float near, float far) {
        projectionMatrix.reset(0);

        // column 0
        projectionMatrix.setValue(2 * near / (right - left), 0, 0);

        // column 1
        projectionMatrix.setValue(2 * near / (top - bottom), 1, 1);

        // column 2
        projectionMatrix.setValue((right + left) / (right - left), 0, 2);
        projectionMatrix.setValue((top + bottom) / (top - bottom), 1, 2);
        projectionMatrix.setValue((far + near) / (near - far), 2, 2);
        projectionMatrix.setValue(-1, 3, 2);

        // column 3
        projectionMatrix.setValue(2 * far * near / (near - far), 2, 3);
    }

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
    public void setInverseFrustumMatrix(float left, float right, float bottom, float top, float near, float far) {
        projectionMatrix.reset(0);

        // column 0
        projectionMatrix.setValue(2 * near / (right - left), 0, 0);

        // column 1
        projectionMatrix.setValue(2 * near / (top - bottom), 1, 1);

        // column 2
        projectionMatrix.setValue((right + left) / (right - left), 0, 2);
        projectionMatrix.setValue((top + bottom) / (top - bottom), 1, 2);
        projectionMatrix.setValue((far + near) / (near - far), 2, 2);
        projectionMatrix.setValue(-1, 3, 2);

        // column 3
        projectionMatrix.setValue(2 * far * near / (near - far), 2, 3);
    }
}