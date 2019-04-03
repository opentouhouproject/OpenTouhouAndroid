package com.scarlet.graphics.opengl.shader;

public abstract class Shader implements AutoCloseable {
    private String name;

    public Shader(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}