package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import android.opengl.EGLContext;
import android.opengl.GLSurfaceView;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;
import opentouhou.com.opentouhouandroid.scene.Stage;
import opentouhou.com.opentouhouandroid.sys.SystemInfo;

/*
 * Represents a renderer.
 */
public abstract class Renderer implements GLSurfaceView.Renderer {
    // OpenGL context.
    protected EGLContext eglContext;

    // framebuffers
    protected FrameBuffer frameBuffer;
    protected FrameBuffer postProcessingBuffer1;
    protected FrameBuffer postProcessingBuffer2;

    // Shader manager.
    protected ShaderManager shaderManager;

    // Texture manager.
    protected TextureManager textureManager;

    // Font manager.
    protected FontManager fontManager;

    // Stage.
    protected Stage stage;

    // Track system info.
    protected SystemInfo sysInfo;

    // Screen info.
    protected int screenWidth;
    protected int screenHeight;
    protected float aspectRatio;

    // Constructor
    public Renderer() {
        frameBuffer = null;

        shaderManager = null;
        textureManager = null;
        fontManager = null;

        sysInfo = new SystemInfo();

        screenWidth = 0;
        screenHeight = 0;
        aspectRatio = 0;
    }

    // Getters
    public ShaderManager getShaderManager() { return shaderManager; }

    public TextureManager getTextureManager() { return textureManager; }

    public FontManager getFontManager() { return fontManager; }

    public EGLContext getContext() { return eglContext; }

    public int getScreenWidth() { return screenWidth; }

    public int getScreenHeight() { return screenHeight; }

    /*
     * Queues a runnable to execute on the OpenGL thread.
     */
    public void queue(Runnable r) {
        stage.view.queueEvent(r);
    }
}