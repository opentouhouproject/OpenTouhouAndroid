package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.shader;

import android.opengl.GLES30;

import com.scarlet.graphics.opengl.shader.FragmentShader;
import com.scarlet.graphics.opengl.shader.ShaderProgram;
import com.scarlet.graphics.opengl.shader.VertexShader;

public class ShaderProgram30 extends ShaderProgram
{
    public ShaderProgram30(String name)
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

    public void link(VertexShader vertexShader, FragmentShader fragmentShader)
    {
        link(vertexShader.getHandle(), fragmentShader.getHandle());
    }

    // Implement AutoCloseable
    public void close()
    {
        GLES30.glDeleteProgram(handle);
    }
}