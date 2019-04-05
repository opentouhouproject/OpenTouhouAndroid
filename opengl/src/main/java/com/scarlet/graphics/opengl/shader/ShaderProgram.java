package com.scarlet.graphics.opengl.shader;

public abstract class ShaderProgram implements AutoCloseable, Linkable {
    protected String name;
    protected int handle;

    public ShaderProgram(String name) {
        this.name = name;
        handle = -1;
    }

    public String getName() { return name; }

    public int getHandle()
    {
        return handle;
    }
}