package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.shader;

import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractFragmentShader;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractShaderProgram;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractVertexShader;

public class ShaderProgram extends AbstractShaderProgram
{
    public ShaderProgram(String name)
    {
        super(name);

        handle = GLES30.glCreateProgram();

        if (handle == 0)
        {
            throw new RuntimeException("Error creating shader program. (glCreateProgram)");
        }
    }

    // Implement Linkable
    public void link()
    {
        // Link the shaders together into a program.
        GLES30.glLinkProgram(handle);

        // Get the link status.
        final int[] linkStatus = new int[1];
        GLES30.glGetProgramiv(handle, GLES30.GL_LINK_STATUS, linkStatus, 0);

        // Check if the linking failed.
        if (linkStatus[0] == 0)
        {
            String err = GLES30.glGetProgramInfoLog(handle);
            throw new RuntimeException("Error linking shader program. " + err);
        }
    }

    public void link(int vertexShaderHandle, int fragmentShaderHandle)
    {
        // Bind the vertex shader to the program.
        GLES30.glAttachShader(handle, vertexShaderHandle);

        // Bind the fragment shader to the program.
        GLES30.glAttachShader(handle, fragmentShaderHandle);

        link();
    }

    public void link(AbstractVertexShader vertexShader, AbstractFragmentShader fragmentShader)
    {
        link(vertexShader.getHandle(), fragmentShader.getHandle());
    }

    // Implement AutoCloseable
    public void close()
    {
        GLES30.glDeleteProgram(handle);
    }
}