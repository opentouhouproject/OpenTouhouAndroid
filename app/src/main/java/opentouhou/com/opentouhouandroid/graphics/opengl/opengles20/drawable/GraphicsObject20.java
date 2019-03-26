package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.drawable;

import android.opengl.GLES20;

import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector4f;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class GraphicsObject20 extends GraphicsObject
{
    // Constructor
    public GraphicsObject20() { }

    // Draw
    public void draw(Scene scene)
    {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES20.glUseProgram(shaderHandle);

        // Set the transformation matrices.
        setTransformationMatrices(shaderHandle, scene);

        // Set the light source(s).
        setLightPosition(shaderHandle, scene);

        // Set the texture.
        setTexture(shaderHandle);

        // Bind to the buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mesh.getBuffer());

        // Get the handles.
        int positionHandle = GLES20.glGetAttribLocation(shaderHandle, "aVertex");
        int colorHandle = GLES20.glGetAttribLocation(shaderHandle, "aColor");
        int normalHandle = GLES20.glGetAttribLocation(shaderHandle, "aNormal");
        int textureCoordinateHandle = GLES20.glGetAttribLocation(shaderHandle, "aTexCoordinate");

        // Enable arrays.
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glEnableVertexAttribArray(colorHandle);
        GLES20.glEnableVertexAttribArray(normalHandle);
        GLES20.glEnableVertexAttribArray(textureCoordinateHandle);

        // Pass in the vertex information.
        GLES20.glVertexAttribPointer(positionHandle, MeshLayout.POSITION_DATA_SIZE, GLES20.GL_FLOAT, false, MeshLayout.PCNT_Stride, MeshLayout.PCNT_POSITION_OFFSET);

        // Pass in the color information.
        GLES20.glVertexAttribPointer(colorHandle, MeshLayout.COLOR_DATA_SIZE, GLES20.GL_FLOAT, false, MeshLayout.PCNT_Stride, MeshLayout.PCNT_COLOR_OFFSET);

        // Pass in the normal information.
        GLES20.glVertexAttribPointer(normalHandle, MeshLayout.NORMAL_DATA_SIZE, GLES20.GL_FLOAT, false, MeshLayout.PCNT_Stride, MeshLayout.PCNT_NORMAL_OFFSET);

        // Pass in the texture coordinate information.
        GLES20.glVertexAttribPointer(textureCoordinateHandle, MeshLayout.TEXTURE_COORDINATE_DATA_SIZE, GLES20.GL_FLOAT, false, MeshLayout.PCNT_Stride, MeshLayout.PCNT_TEXTURE_COORDINATE_OFFSET);

        // Draw the object.
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

        // Disable arrays.
        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(colorHandle);
        GLES20.glDisableVertexAttribArray(normalHandle);
        GLES20.glDisableVertexAttribArray(textureCoordinateHandle);

        // Unbind from the buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    protected void setTransformationMatrices(int handle, Scene scene)
    {
        // Get the handles.
        int modelViewMatrixHandle = GLES20.glGetUniformLocation(handle, "uMVMatrix");
        int modelViewProjectionMatrixHandle = GLES20.glGetUniformLocation(handle, "uMVPMatrix");

        // Get the matrices.
        Matrix4f viewMatrix = scene.getCamera().getViewMatrix();
        Matrix4f projectionMatrix = scene.getCamera().getProjectionMatrix();

        // Compute the matrices.
        Matrix4f mvMatrix = Matrix4f.multiply(viewMatrix, modelMatrix);
        Matrix4f mvpMatrix = Matrix4f.multiply(projectionMatrix, mvMatrix);

        // Send the matrices to the GPU.
        GLES20.glUniformMatrix4fv(modelViewMatrixHandle, 1, false, mvMatrix.getArray(), 0);
        GLES20.glUniformMatrix4fv(modelViewProjectionMatrixHandle, 1, false, mvpMatrix.getArray(), 0);
    }

    protected void setLightPosition(int handle, Scene scene)
    {
        // Get the handle.
        int lightPositionHandle = GLES20.glGetUniformLocation(handle, "uLightSource");

        // Compute the light position in Eye Space.
        Vector4f lightPosition = Matrix4f.multiply(scene.getCamera().getViewMatrix(), scene.getLight());

        // Send the light position to the GPU.
        GLES20.glUniform3f(lightPositionHandle, lightPosition.x, lightPosition.y, lightPosition.z);
    }

    protected void setTexture(int handle)
    {
        // Get the handle.
        int textureHandle = GLES20.glGetUniformLocation(handle, "uTexture");

        // Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        // Bind the texture to this unit.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture.getTextureHandle());

        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES20.glUniform1i(textureHandle, 0);
    }
}