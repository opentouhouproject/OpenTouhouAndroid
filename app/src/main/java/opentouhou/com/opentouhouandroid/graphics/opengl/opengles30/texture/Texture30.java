package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.texture;

import android.graphics.BitmapFactory;
import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTexture;

public class Texture30 extends AbstractTexture
{
    public Texture30(BitmapFactory.Options options)
    {
        super(options);

        GLES30.glGenTextures(1, textureHandle, 0);
    }
}