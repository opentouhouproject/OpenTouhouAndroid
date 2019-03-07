package opentouhou.com.opentouhouandroid.graphics.opengl.common.shader;

import opentouhou.com.opentouhouandroid.graphics.opengl.Compilable;

public abstract class AbstractFragmentShader extends AbstractShader implements Compilable
{
    protected int handle;

    public AbstractFragmentShader(String name)
    {
        super(name);

        handle = -1;
    }

    public int getHandle()
    {
        return handle;
    }
}
