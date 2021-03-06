package com.scarlet.opengles30.texture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import com.scarlet.graphics.opengl.texture.TextureManager;
import com.scarlet.graphics.opengl.texture.Texture;

/**
 * Loads bitmap into GPU memory using OpenGL ES 3.0.
 */

public class TextureManager30 extends TextureManager
{
    @Override
    protected Texture createTexture(Bitmap bitmap, BitmapFactory.Options options)
    {
        // Create TextureGL class.
        Texture tex = new Texture30(options);

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