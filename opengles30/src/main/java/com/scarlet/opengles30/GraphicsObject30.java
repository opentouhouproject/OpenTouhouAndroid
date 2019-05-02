package com.scarlet.opengles30;

import android.opengl.GLES30;

import com.scarlet.graphics.opengl.Camera;
import com.scarlet.graphics.opengl.GraphicsObject;
import com.scarlet.graphics.opengl.GraphicsOptions;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector4f;

/*
 * GraphicsObject implemented in OpenGL ES 3.0
 */
public class GraphicsObject30 extends GraphicsObject {
    // Constructor(s)
    public GraphicsObject30() { }

    public GraphicsObject30(GraphicsOptions option) { super(option, GraphicsObject.Version.OPENGL_ES_30); }

    // Draw method
    public void draw(Renderer renderer) {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES30.glUseProgram(shaderHandle);

        // Set the transformation matrices.
        setTransformationMatrices(shaderHandle, renderer.getCamera());

        // Set the light source(s).
        if (option.lightingSetting()) setLightPosition(shaderHandle, renderer.getCamera(), renderer.getLight());

        // Set the texture.
        if (option.textureSetting()) setTexture(shaderHandle);

        // Set the mesh.
        setMesh();

        // Draw the object.
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, mesh.getVertexCount());
    }

    protected void setTransformationMatrices(int handle, Camera camera) {
        // Get the handles.
        int modelViewMatrixHandle = GLES30.glGetUniformLocation(handle, "uMVMatrix");
        int modelViewProjectionMatrixHandle = GLES30.glGetUniformLocation(handle, "uMVPMatrix");

        // Get the matrices.
        Matrix4f viewMatrix = camera.getViewMatrix();
        Matrix4f projectionMatrix = camera.getProjectionMatrix();

        // Compute the matrices.
        Matrix4f mvMatrix = Matrix4f.multiply(viewMatrix, modelMatrix);
        Matrix4f mvpMatrix = Matrix4f.multiply(projectionMatrix, mvMatrix);

        // Send the matrices to the GPU.
        GLES30.glUniformMatrix4fv(modelViewMatrixHandle, 1, false, mvMatrix.getArray(), 0);
        GLES30.glUniformMatrix4fv(modelViewProjectionMatrixHandle, 1, false, mvpMatrix.getArray(), 0);
    }

    protected void setLightPosition(int handle, Camera camera, Vector4f light) {
        // Get the handle.
        int lightPositionHandle = GLES30.glGetUniformLocation(handle, "uLightSource");

        // Compute the light position in Eye Space.
        Vector4f lightPosition = Matrix4f.multiply(camera.getViewMatrix(), light);

        // Send the light position to the GPU.
        GLES30.glUniform3f(lightPositionHandle, lightPosition.x, lightPosition.y, lightPosition.z);
    }

    protected void setTexture(int handle) {
        // Get the handle.
        int textureHandle = GLES30.glGetUniformLocation(handle, "uTexture");

        // Set the active texture unit to texture unit 0.
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
        // Bind the texture to this unit.
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture.getTextureHandle());

        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES30.glUniform1i(textureHandle, 0);
    }

    protected void setMesh() {
        // Bind Vertex Array Object.
        GLES30.glBindVertexArray(mesh.getVAO().getHandle());
    }
}