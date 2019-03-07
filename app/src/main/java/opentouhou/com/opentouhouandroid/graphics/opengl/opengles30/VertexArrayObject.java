package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30;

import android.opengl.GLES30;

import java.nio.IntBuffer;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.AbstractVertexArrayObject;

public class VertexArrayObject extends AbstractVertexArrayObject
{
    public VertexArrayObject(int buffer, int shader)
    {
        // Bind the vertex buffer object
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, buffer);

        // Create a VAO.
        handles = IntBuffer.allocate(1);
        GLES30.glGenVertexArrays(1, handles);

        // Bind the VAO.
        GLES30.glBindVertexArray(handles.get(0));

        // Set the vertex attributes.
        int mPositionHandle = GLES30.glGetAttribLocation(shader, "aVertex");
        GLES30.glEnableVertexAttribArray( mPositionHandle );
        GLES30.glVertexAttribPointer( mPositionHandle, VERTEX_DATA_SIZE, GLES30.GL_FLOAT, false, stride, 0 );

        // Pass in the color information.
        int mColorHandle = GLES30.glGetAttribLocation(shader, "aColor");
        GLES30.glEnableVertexAttribArray(mColorHandle);
        GLES30.glVertexAttribPointer(mColorHandle, COLOR_DATA_SIZE, GLES30.GL_FLOAT, false, stride, VERTEX_DATA_SIZE * BYTES_PER_FLOAT);

        // Pass in the normal information.
        int mNormalHandle = GLES30.glGetAttribLocation(shader, "aNormal");
        GLES30.glEnableVertexAttribArray(mNormalHandle);
        GLES30.glVertexAttribPointer(mNormalHandle, NORMAL_DATA_SIZE, GLES30.GL_FLOAT, false, stride, (VERTEX_DATA_SIZE + COLOR_DATA_SIZE) * BYTES_PER_FLOAT);

        // Pass in the texture coordinate information.
        int mTextureCoordinateHandle = GLES30.glGetAttribLocation(shader, "aTexCoordinate");
        GLES30.glEnableVertexAttribArray(mTextureCoordinateHandle);
        GLES30.glVertexAttribPointer(mTextureCoordinateHandle, TEXTURE_COORDINATE_DATA_SIZE, GLES30.GL_FLOAT, false, stride, (VERTEX_DATA_SIZE + COLOR_DATA_SIZE + NORMAL_DATA_SIZE) * BYTES_PER_FLOAT);

        // Unbind the VAO.
        GLES30.glBindVertexArray(0);
    }
}