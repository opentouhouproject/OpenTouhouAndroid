package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.texture;

import android.graphics.BitmapFactory;
import android.opengl.GLES20;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.Texture;

public class Texture20 extends Texture
{
    public Texture20(BitmapFactory.Options options)
    {
        super(options);

        GLES20.glGenTextures(1, textureHandle, 0);
    }
}