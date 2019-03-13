package opentouhou.com.opentouhouandroid.graphics.opengl.common.shader;

import opentouhou.com.opentouhouandroid.graphics.opengl.Linkable;

public abstract class ShaderProgram implements AutoCloseable, Linkable
{
    protected String name;
    protected int handle;

    public ShaderProgram(String name)
    {
        this.name = name;
        handle = -1;
    }

    public String getName() { return name; }

    public int getHandle()
    {
        return handle;
    }
}