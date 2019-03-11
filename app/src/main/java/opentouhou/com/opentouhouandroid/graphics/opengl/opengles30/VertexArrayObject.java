package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30;

import android.opengl.GLES30;

import java.nio.IntBuffer;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.AbstractVertexArrayObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;

public class VertexArrayObject extends AbstractVertexArrayObject
{
    public VertexArrayObject(int buffer, int shader, MeshLayout.Layout layout)
    {
        // Bind the vertex buffer object
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, buffer);

        // Create a VAO.
        handles = IntBuffer.allocate(1);
        GLES30.glGenVertexArrays(1, handles);

        // Bind the VAO.
        GLES30.glBindVertexArray(handles.get(0));

        switch (layout)
        {
            case PCNT:
                initialisePCNT(shader);
                break;

            case PCN:
                initialisePCN(shader);
                break;
        }

        // Unbind the VAO.
        GLES30.glBindVertexArray(0);
    }

    // Set VAO for a PCNT layout.
    private void initialisePCNT(int shader)
    {
        setPosition(shader, MeshLayout.PCNT_Stride, MeshLayout.PCNT_POSITION_OFFSET);

        setColor(shader, MeshLayout.PCNT_Stride, MeshLayout.PCNT_COLOR_OFFSET);

        setNormal(shader, MeshLayout.PCNT_Stride, MeshLayout.PCNT_NORMAL_OFFSET);

        setTextureCoordinate(shader, MeshLayout.PCNT_Stride, MeshLayout.PCNT_TEXTURE_COORDINATE_OFFSET);
    }

    // Set VAO for a PCN layout.
    private void initialisePCN(int shader)
    {
        setPosition(shader, MeshLayout.PCN_Stride, MeshLayout.PCN_POSITION_OFFSET);

        setColor(shader, MeshLayout.PCN_Stride, MeshLayout.PCN_COLOR_OFFSET);

        setNormal(shader, MeshLayout.PCN_Stride, MeshLayout.PCN_NORMAL_OFFSET);
    }

    // Set the position attribute.
    private void setPosition(int shader, int stride, int offset)
    {
        int positionHandle = GLES30.glGetAttribLocation(shader, "aVertex");
        GLES30.glEnableVertexAttribArray(positionHandle);
        GLES30.glVertexAttribPointer(positionHandle, MeshLayout.POSITION_DATA_SIZE, GLES30.GL_FLOAT, false, stride, offset);
    }

    // Set the color attribute.
    private void setColor(int shader, int stride, int offset)
    {
        int colorHandle = GLES30.glGetAttribLocation(shader, "aColor");
        GLES30.glEnableVertexAttribArray(colorHandle);
        GLES30.glVertexAttribPointer(colorHandle, MeshLayout.COLOR_DATA_SIZE, GLES30.GL_FLOAT, false, stride, offset);
    }

    // Set the normal attribute.
    private void setNormal(int shader, int stride, int offset)
    {
        int normalHandle = GLES30.glGetAttribLocation(shader, "aNormal");
        GLES30.glEnableVertexAttribArray(normalHandle);
        GLES30.glVertexAttribPointer(normalHandle, MeshLayout.NORMAL_DATA_SIZE, GLES30.GL_FLOAT, false, stride, offset);
    }

    // Set the texture coordinate attribute.
    private void setTextureCoordinate(int shader, int stride, int offset)
    {
        int textureCoordinateHandle = GLES30.glGetAttribLocation(shader, "aTexCoordinate");
        GLES30.glEnableVertexAttribArray(textureCoordinateHandle);
        GLES30.glVertexAttribPointer(textureCoordinateHandle, MeshLayout.TEXTURE_COORDINATE_DATA_SIZE, GLES30.GL_FLOAT, false, stride, offset);
    }
}