package opentouhou.com.opentouhouandroid.graphics.opengl.common.texture;

public abstract class AbstractTexture
{
    protected final int[] textureHandle;

    // Constructor
    protected AbstractTexture()
    {
        textureHandle = new int[1];
    }

    // Retrieve the handle.
    public int getTextureHandle()
    {
        return textureHandle[0];
    }
}