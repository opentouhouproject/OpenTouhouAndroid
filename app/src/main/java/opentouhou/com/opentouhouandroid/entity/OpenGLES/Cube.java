package opentouhou.com.opentouhouandroid.entity.OpenGLES;

import android.opengl.GLES20;

import java.nio.FloatBuffer;

import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.math.Vector3f;

/**
 * It's a cube.
 */

public class Cube extends GraphicsObject
{
    private static int VERTEX_DATA_SIZE = 3;
    private static int COLOR_DATA_SIZE = 4;
    private static int NORMAL_DATA_SIZE = 3;

    final float[] vertexData;
    final float[] colorData;
    final float[] normalData;

    final FloatBuffer vertexBuffer;
    final FloatBuffer colorBuffer;
    final FloatBuffer normalBuffer;

    int programHandle = -1;

    public Cube(float[] vertices, float[] colors, float[] normals, int shader)
    {
        vertexData = vertices;
        colorData = colors;
        normalData = normals;

        vertexBuffer = createBuffer(vertexData.length * BYTES_PER_FLOAT, vertexData);
        colorBuffer = createBuffer(colorData.length * BYTES_PER_FLOAT, colorData);
        normalBuffer = createBuffer(normalData.length * BYTES_PER_FLOAT, normalData);

        programHandle = shader;
    }

    public void draw(Matrix4f modelMatrix, Matrix4f viewMatrix, Matrix4f projectionMatrix, Vector3f lightPos)
    {
        // Set the shader program.
        GLES20.glUseProgram(programHandle);

        // Set program handles for cube drawing.
        int mMVPMatrixHandle = GLES20.glGetUniformLocation(programHandle, "uMVPMatrix");
        int mMVMatrixHandle = GLES20.glGetUniformLocation(programHandle, "uMVMatrix");
        int mLightPosHandle = GLES20.glGetUniformLocation(programHandle, "uLightSource");

        int mPositionHandle = GLES20.glGetAttribLocation(programHandle, "aVertex");
        int mColorHandle = GLES20.glGetAttribLocation(programHandle, "aColor");
        int mNormalHandle = GLES20.glGetAttribLocation(programHandle, "aNormal");

        // compute the mvp and mv matrices
        Matrix4f mvMatrix = Matrix4f.multiply(viewMatrix, modelMatrix);
        Matrix4f mvpMatrix = Matrix4f.multiply(projectionMatrix, mvMatrix);

        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mvMatrix.getArray(), 0);
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix.getArray(), 0);

        // pass in the light source vector
        GLES20.glUniform3f(mLightPosHandle, lightPos.x, lightPos.y, lightPos.z);

        // Pass in the vertex information
        vertexBuffer.position(0);
        GLES20.glVertexAttribPointer(mPositionHandle, VERTEX_DATA_SIZE, GLES20.GL_FLOAT,false,0, vertexBuffer);
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Pass in the color information
        colorBuffer.position(0);
        GLES20.glVertexAttribPointer(mColorHandle, COLOR_DATA_SIZE, GLES20.GL_FLOAT,false,0, colorBuffer);
        GLES20.glEnableVertexAttribArray(mColorHandle);

        // Pass in the normal information
        normalBuffer.position(0);
        GLES20.glVertexAttribPointer(mNormalHandle, NORMAL_DATA_SIZE, GLES20.GL_FLOAT,false,0, normalBuffer);
        GLES20.glEnableVertexAttribArray(mNormalHandle);

        // Draw the cube.
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 36);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mColorHandle);
        GLES20.glDisableVertexAttribArray(mNormalHandle);
    }
}