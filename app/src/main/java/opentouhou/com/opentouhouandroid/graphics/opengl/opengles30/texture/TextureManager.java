package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.texture;

import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTextureManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTexture;

/**
 * Loads bitmap into GPU memory using OpenGL ES 3.0.
 */

public class TextureManager extends AbstractTextureManager
{
    @Override
    protected AbstractTexture createTexture(Bitmap bitmap)
    {
        // Create TextureGL class.
        AbstractTexture tex = new Texture();

        // Bind to the texture in OpenGL.
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, tex.getTextureHandle());

        // Set the filtering.
        // GL_TEXTURE_MIN_FILTER — This tells OpenGL what type of filtering to apply when drawing the texture smaller than the original size in pixels.
        // GL_TEXTURE_MAG_FILTER — This tells OpenGL what type of filtering to apply when magnifying the texture beyond its original size in pixels.
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_NEAREST);

        // Load the bitmap into the bound texture.
        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0);

        return tex;
    }
}