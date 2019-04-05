package com.scarlet.graphics.opengl.shader;

public abstract class VertexShader extends Shader implements Compilable {
    protected int handle;

    public VertexShader(String name) {
        super(name);

        handle = -1;
    }

    public int getHandle()
    {
        return handle;
    }
}