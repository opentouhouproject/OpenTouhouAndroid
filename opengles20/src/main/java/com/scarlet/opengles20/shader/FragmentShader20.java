package com.scarlet.opengles20.shader;

import android.opengl.GLES20;

import com.scarlet.graphics.opengl.shader.FragmentShader;

public class FragmentShader20 extends FragmentShader
{
    public FragmentShader20(String name)
    {
        super(name);

        // Create a shader.
        handle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

        if (handle == 0)
        {
            throw new RuntimeException("Could not create shader. (glCreateShader)");
        }
    }

    // Implement Compilable
    public void compile()
    {
        // Compile the shader.
        GLES20.glCompileShader(handle);

        // Get the compilation status.
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(handle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

        // Check if the compilation failed.
        if (compileStatus[0] == 0)
        {
            String err = GLES20.glGetShaderInfoLog(handle);
            throw new RuntimeException("Error compiling shader. " + err);
        }
    }

    public void compile(String code)
    {
        // Pass in the code.
        GLES20.glShaderSource(handle, code);

        if (handle == 0)
        {
            throw new RuntimeException("Could not set shader source. (glShaderSource)");
        }

        compile();
    }

    public void compile(StringBuffer codeBuffer)
    {
        compile(codeBuffer.toString());
    }

    // Implement AutoCloseable
    public void close()
    {
        GLES20.glDeleteShader(handle);
    }
}