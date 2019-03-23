package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.GLSurfaceView;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Mesh30;
import opentouhou.com.opentouhouandroid.scene.Stage;

/*
 * Represents a renderer.
 */

public abstract class Renderer implements GLSurfaceView.Renderer {
    // EGLContext.
    protected EGLContext eglContext;

    // Shader manager.
    protected ShaderManager shaderManager;

    // Texture manager.
    protected TextureManager textureManager;

    // Font manager.
    protected FontManager fontManager;

    protected Stage stage;

    // FPS tracking.
    protected int numberOfFrames;
    protected long lastTime;
    protected int lastFPS;

    // Constructor
    public Renderer() {
        shaderManager = null;
        textureManager = null;
        fontManager = null;

        numberOfFrames = 0;
        lastTime = System.currentTimeMillis();
        lastFPS = 0;
    }

    // Getters
    public ShaderManager getShaderManager() { return shaderManager; }

    public TextureManager getTextureManager() { return textureManager; }

    public FontManager getFontManager() { return fontManager; }

    public EGLContext getContext() { return eglContext; }

    public void queue(Runnable r) {
        stage.view.queueEvent(r);
    }
}