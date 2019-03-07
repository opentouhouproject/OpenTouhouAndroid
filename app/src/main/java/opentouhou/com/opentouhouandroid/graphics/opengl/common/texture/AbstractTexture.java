package opentouhou.com.opentouhouandroid.graphics.opengl.common.texture;

import android.graphics.BitmapFactory;

public abstract class AbstractTexture
{
    protected final int[] textureHandle;

    private int width;
    private int height;
    private String type;

    // Constructor
    protected AbstractTexture(BitmapFactory.Options options)
    {
        textureHandle = new int[1];

        width = options.outWidth;
        height = options.outHeight;
        type = options.outMimeType;
    }

    // Retrieve the handle.
    public int getTextureHandle()
    {
        return textureHandle[0];
    }

    // Getters
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public String getType() { return type; }
}