package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.texture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import com.scarlet.graphics.opengl.texture.TextureManager;
import com.scarlet.graphics.opengl.texture.Texture;

/**
 * Loads bitmap into GPU memory using OpenGL ES 2.0.
 */

public class TextureManager20 extends TextureManager
{
    @Override
    protected Texture createTexture(Bitmap bitmap, BitmapFactory.Options options)
    {
        // Create TextureGL class.
        Texture tex = new Texture20(options);

        // Bind to the texture in OpenGL.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.getTextureHandle());

        // Set the filtering.
        // GL_TEXTURE_MIN_FILTER — This tells OpenGL what type of filtering to apply when drawing the texture smaller than the original size in pixels.
        // GL_TEXTURE_MAG_FILTER — This tells OpenGL what type of filtering to apply when magnifying the texture beyond its original size in pixels.
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

        // Load the bitmap into the bound texture.
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

        return tex;
    }
}