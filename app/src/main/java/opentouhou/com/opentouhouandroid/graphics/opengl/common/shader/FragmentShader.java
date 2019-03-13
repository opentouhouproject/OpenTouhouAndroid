package opentouhou.com.opentouhouandroid.graphics.opengl.common.shader;

import opentouhou.com.opentouhouandroid.graphics.opengl.Compilable;

public abstract class FragmentShader extends Shader implements Compilable
{
    protected int handle;

    public FragmentShader(String name)
    {
        super(name);

        handle = -1;
    }

    public int getHandle()
    {
        return handle;
    }
}
