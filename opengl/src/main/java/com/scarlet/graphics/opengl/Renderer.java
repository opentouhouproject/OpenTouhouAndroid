package com.scarlet.graphics.opengl;

import android.opengl.EGLContext;
import android.opengl.GLSurfaceView;

import com.scarlet.graphics.opengl.font.FontManager;
import com.scarlet.graphics.opengl.shader.ShaderManager;
import com.scarlet.graphics.opengl.texture.TextureManager;
import com.scarlet.math.Vector4f;

/*
 * Represents a renderer.
 */
public abstract class Renderer implements GLSurfaceView.Renderer {
    public GLSurfaceView view;

    // OpenGL context.
    protected EGLContext eglContext;

    // framebuffers
    public FrameBuffer frameBuffer;
    public FrameBuffer postProcessingBuffer1;
    public FrameBuffer postProcessingBuffer2;

    // Shader manager.
    protected ShaderManager shaderManager;

    // Texture manager.
    protected TextureManager textureManager;

    // Font manager.
    protected FontManager fontManager;

    // Camera
    protected Camera camera;

    protected Vector4f light;

    // Stage.
    //public Stage stage;

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

    public Camera getCamera() { return camera; }

    public Vector4f getLight() { return light; }

    /*
     * Queues a runnable to execute on the OpenGL thread.
     */
    public void queue(Runnable r) {
        view.queueEvent(r);
    }
}