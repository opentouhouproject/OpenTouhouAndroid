package com.scarlet.graphics.opengl.shader;

public abstract class FragmentShader extends Shader implements Compilable {
    protected int handle;

    public FragmentShader(String name) {
        super(name);

        handle = -1;
    }

    public int getHandle()
    {
        return handle;
    }
}