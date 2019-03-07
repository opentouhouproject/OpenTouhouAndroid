package opentouhou.com.opentouhouandroid.graphics.opengl.common.shader;

import opentouhou.com.opentouhouandroid.graphics.opengl.Linkable;

public abstract class AbstractShaderProgram implements AutoCloseable, Linkable
{
    protected String name;
    protected int handle;

    public AbstractShaderProgram(String name)
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