package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.shader;

import android.opengl.GLES20;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractFragmentShader;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractShaderProgram;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractVertexShader;

public class ShaderProgram extends AbstractShaderProgram
{
    public ShaderProgram(String name)
    {
        super(name);

        handle = GLES20.glCreateProgram();

        if (handle == 0)
        {
            throw new RuntimeException("Error creating shader program. (glCreateProgram)");
        }
    }

    // Implement Linkable
    public void link()
    {
        // Link the shaders together into a program.
        GLES20.glLinkProgram(handle);

        // Get the link status.
        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(handle, GLES20.GL_LINK_STATUS, linkStatus, 0);

        // Check if the linking failed.
        if (linkStatus[0] == 0)
        {
            String err = GLES20.glGetProgramInfoLog(handle);
            throw new RuntimeException("Error linking shader program. " + err);
        }
    }

    public void link(int vertexShaderHandle, int fragmentShaderHandle)
    {
        // Bind the vertex shader to the program.
        GLES20.glAttachShader(handle, vertexShaderHandle);

        // Bind the fragment shader to the program.
        GLES20.glAttachShader(handle, fragmentShaderHandle);

        link();
    }

    public void link(AbstractVertexShader vertexShader, AbstractFragmentShader fragmentShader)
    {
        link(vertexShader.getHandle(), fragmentShader.getHandle());
    }

    // Implement AutoCloseable
    public void close()
    {
        GLES20.glDeleteProgram(handle);
    }
}