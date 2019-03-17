package opentouhou.com.opentouhouandroid.actor.OpenGLES;

import android.opengl.GLES20;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.Texture;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.math.Vector3f;

import java.nio.FloatBuffer;

public class Rectangle extends GraphicsObject
{
    private static int VERTEX_DATA_SIZE = 3; // 3 floats covering x, y, and z coordinates of the vertex
    private static int COLOR_DATA_SIZE = 4; // 4 floats covering r, g, b, a color values
    private static int NORMAL_DATA_SIZE = 3; // 3 floats covering x, y, and z coordinates of the normal vector
    private static int TEXTURE_COORDINATE_DATA_SIZE = 2; // 2 floats covering x and y coordinates of the texture

    // How many bytes define a vertex.
    final int stride = (VERTEX_DATA_SIZE + COLOR_DATA_SIZE + NORMAL_DATA_SIZE + TEXTURE_COORDINATE_DATA_SIZE) * BYTES_PER_FLOAT;

    // Buffers.
    final int[] buffers;
    final int vertexBuffer;

    Texture texture;

    int programHandle = -1;

    public Rectangle(float[] attributes, int shader)
    {
        programHandle = shader;

        // Allocate memory on the native heap.
        FloatBuffer clientMemoryBuffer = createBuffer(attributes.length * BYTES_PER_FLOAT, attributes);

        // Generate buffer(s).
        buffers = new int[1];
        GLES20.glGenBuffers(1, buffers, 0);
        vertexBuffer = buffers[0];

        // Bind to the buffer. Future commands will affect this buffer specifically.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBuffer);

        // Transfer client memory to the GPU/buffer.
        // We can release client memory when we finish this call. (GC handled)
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER,clientMemoryBuffer.capacity() * BYTES_PER_FLOAT, clientMemoryBuffer, GLES20.GL_STATIC_DRAW);

        // Unbind from the buffer when done with it.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    public void SetTexture(Texture texture)
    {
        this.texture = texture;
    }

    public void draw(Matrix4f modelMatrix, Matrix4f viewMatrix, Matrix4f projectionMatrix, Vector3f lightPos)
    {
        // Set our shader program.
        GLES20.glUseProgram(programHandle);

        // Set program handles for drawing.
        int mMVPMatrixHandle = GLES20.glGetUniformLocation(programHandle, "uMVPMatrix");
        int mMVMatrixHandle = GLES20.glGetUniformLocation(programHandle, "uMVMatrix");
        int mLightPosHandle = GLES20.glGetUniformLocation(programHandle, "uLightSource");
        int mTextureUniformHandle = GLES20.glGetUniformLocation(programHandle, "uTexture");

        int mPositionHandle = GLES20.glGetAttribLocation(programHandle, "aVertex");
        int mColorHandle = GLES20.glGetAttribLocation(programHandle, "aColor");
        int mNormalHandle = GLES20.glGetAttribLocation(programHandle, "aNormal");
        int mTextureCoordinateHandle = GLES20.glGetAttribLocation(programHandle, "aTexCoordinate");

        // Compute the mvp and mv matrices.
        Matrix4f mvMatrix = Matrix4f.multiply(viewMatrix, modelMatrix);
        Matrix4f mvpMatrix = Matrix4f.multiply(projectionMatrix, mvMatrix);

        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mvMatrix.getArray(), 0);
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix.getArray(), 0);

        // Pass in the light source vector.
        GLES20.glUniform3f(mLightPosHandle, lightPos.x, lightPos.y, lightPos.z);

        // Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        // Bind the texture to this unit.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture.getTextureHandle());
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES20.glUniform1i(mTextureUniformHandle, 0);

        // Bind to the buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBuffer);

        // Pass in the vertex information.
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, VERTEX_DATA_SIZE, GLES20.GL_FLOAT, false, stride, 0);

        // Pass in the color information.
        GLES20.glEnableVertexAttribArray(mColorHandle);
        GLES20.glVertexAttribPointer(mColorHandle, COLOR_DATA_SIZE, GLES20.GL_FLOAT, false, stride, VERTEX_DATA_SIZE * BYTES_PER_FLOAT);

        // Pass in the normal information.
        GLES20.glEnableVertexAttribArray(mNormalHandle);
        GLES20.glVertexAttribPointer(mNormalHandle, NORMAL_DATA_SIZE, GLES20.GL_FLOAT, false, stride, (VERTEX_DATA_SIZE + COLOR_DATA_SIZE) * BYTES_PER_FLOAT);

        // Pass in the texture coordinate information.
        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle, TEXTURE_COORDINATE_DATA_SIZE, GLES20.GL_FLOAT, false, stride, (VERTEX_DATA_SIZE + COLOR_DATA_SIZE + NORMAL_DATA_SIZE) * BYTES_PER_FLOAT);

        // Draw the object.
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

        // Disable arrays.
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mColorHandle);
        GLES20.glDisableVertexAttribArray(mNormalHandle);
        GLES20.glDisableVertexAttribArray(mTextureCoordinateHandle);

        // Unbind from the buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }
}