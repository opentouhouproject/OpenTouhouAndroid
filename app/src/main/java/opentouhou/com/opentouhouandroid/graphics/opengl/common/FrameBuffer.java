package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.Texture;

/*
 * Represents a framebuffer for rendering.
 * Version specific implementations not provided.
 */
public abstract class FrameBuffer implements AutoCloseable {
    protected int[] handle;
    protected Texture texture;
    protected int renderBufferHandle;

    /*
     * Constructor(s).
     */
    protected FrameBuffer() {
        handle = new int[1];
        texture = null;
        renderBufferHandle = 0;
    }

    public Texture getTexture() {
        return texture;
    }

    public abstract void bind();

    public abstract void unbind();
}