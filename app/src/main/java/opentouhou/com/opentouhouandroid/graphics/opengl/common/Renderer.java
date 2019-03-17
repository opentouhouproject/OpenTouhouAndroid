package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import android.content.Context;
import android.opengl.GLSurfaceView;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;

/**
 * Represents a renderer.
 *
 */

public abstract class Renderer implements GLSurfaceView.Renderer
{
    // Activity Context
    protected Context context;

    // Shader manager.
    protected ShaderManager shaderManager;

    // Texture manager.
    protected TextureManager textureManager;

    // Font manager.
    protected FontManager fontManager;

    // FPS tracking.
    protected int numberOfFrames;
    protected long lastTime;
    protected int lastFPS;

    // Constructor
    public Renderer(Context context)
    {
        this.context = context;

        shaderManager = null;
        textureManager = null;
        fontManager = null;

        numberOfFrames = 0;
        lastTime = System.currentTimeMillis();
        lastFPS = 0;
    }

    // Getters
    public Context getContext() { return context; }

    public ShaderManager getShaderManager() { return shaderManager; }

    public TextureManager getTextureManager() { return textureManager; }

    public FontManager getFontManager() { return fontManager; }
}