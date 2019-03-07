package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.texture;

import android.opengl.GLES20;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTexture;

public class Texture extends AbstractTexture
{
    public Texture()
    {
        GLES20.glGenTextures(1, textureHandle, 0);
    }
}