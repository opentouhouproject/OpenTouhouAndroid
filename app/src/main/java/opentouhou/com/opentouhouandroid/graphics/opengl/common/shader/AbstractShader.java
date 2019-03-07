package opentouhou.com.opentouhouandroid.graphics.opengl.common.shader;

public abstract class AbstractShader implements AutoCloseable
{
    private String name;

    public AbstractShader(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}