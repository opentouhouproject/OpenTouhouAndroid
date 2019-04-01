package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.Texture;

/*
 * Represents a framebuffer for rendering.
 * Version specific implementations not provided.
 */
public abstract class FrameBuffer implements AutoCloseable {
    protected int[] handle;
    protected Texture[] textures;
    protected int renderBufferHandle;
    protected int numberOfRenderTargets;

    /*
     * Constructor(s).
     */
    protected FrameBuffer(int numRenderTargets) {
        handle = new int[1];
        textures = new Texture[numRenderTargets];
        renderBufferHandle = 0;

        numberOfRenderTargets = numRenderTargets;
    }

    public Texture getTexture(int index) {
        return textures[index];
    }

    public abstract void bind();

    public abstract void unbind();
}