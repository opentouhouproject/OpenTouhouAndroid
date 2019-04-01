package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30;

import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.FrameBuffer;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.texture.Texture30;

/*
 * Implements a frame buffer with OpenGL ES 3.0 .
 */
public class FrameBuffer30 extends FrameBuffer {
    /*
     * Constructor(s).
     */
    public FrameBuffer30(int width, int height) {
        setup(width, height);
    }

    private void setup(int width, int height) {
        // Create a framebuffer.
        GLES30.glGenFramebuffers(1, handle, 0);

        // Bind the framebuffer
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, handle[0]);

        // Create render targets.
        setupRenderTarget(0, width, height, GLES30.GL_COLOR_ATTACHMENT0);
        setupRenderTarget(1, width, height, GLES30.GL_COLOR_ATTACHMENT1);
        int attachments[] = { GLES30.GL_COLOR_ATTACHMENT0, GLES30.GL_COLOR_ATTACHMENT1 };
        GLES30.glDrawBuffers(2, attachments, 0);

        // Create a render buffer.
        int[] rbo = new int[1];
        GLES30.glGenRenderbuffers(1, rbo, 0);
        renderBufferHandle = rbo[0];

        // Bind the render buffer.
        GLES30.glBindRenderbuffer(GLES30.GL_RENDERBUFFER, renderBufferHandle);

        // Setup the render buffer.
        GLES30.glRenderbufferStorage(GLES30.GL_RENDERBUFFER, GLES30.GL_DEPTH24_STENCIL8, width, height);

        // Unbind the render buffer.
        GLES30.glBindRenderbuffer(GLES30.GL_RENDERBUFFER, 0);

        // Bind the render buffer to the frame buffer.
        GLES30.glFramebufferRenderbuffer(GLES30.GL_FRAMEBUFFER, GLES30.GL_DEPTH_STENCIL_ATTACHMENT, GLES30.GL_RENDERBUFFER, renderBufferHandle);

        // Check the result.
        int result = GLES30.glCheckFramebufferStatus(GLES30.GL_FRAMEBUFFER);
        if (result != GLES30.GL_FRAMEBUFFER_COMPLETE) {
            throw new RuntimeException("Framebuffer failed to complete.");
        }

        // Make default framebuffer active again.
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0);
    }

    /*
     * Setup a render target.
     */
    private void setupRenderTarget(int index, int width, int height, int attachment) {
        // Create a texture.
        int[] texHandle = new int[1];
        GLES30.glGenTextures(1, texHandle, 0);
        textures[index] = new Texture30(texHandle[0]);
        int handle = textures[index].getTextureHandle();

        // Bind the texture.
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, handle);

        // Setup the texture.
        GLES30.glTexImage2D(GLES30.GL_TEXTURE_2D, 0, GLES30.GL_RGB, width, height, 0,
                GLES30.GL_RGB, GLES30.GL_UNSIGNED_BYTE, null);

        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);

        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_S, GLES30.GL_CLAMP_TO_EDGE);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_T, GLES30.GL_CLAMP_TO_EDGE);

        // Unbind the texture.
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, 0);

        // Attach the texture to the framebuffer.
        GLES30.glFramebufferTexture2D(GLES30.GL_FRAMEBUFFER, attachment, GLES30.GL_TEXTURE_2D, handle, 0);
    }

    /*
     * Bind this frame buffer.
     */
    public void bind() {
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, handle[0]);
    }

    /*
     * Unbind this frame buffer.
     */
    public void unbind() {
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0);
    }

    /*
     * Destroy this frame buffer.
     */
    private void destroy() {
        GLES30.glDeleteFramebuffers(1, handle, 0);
    }

    /*
     * Implement AutoCloseable.
     */
    @Override
    public void close() throws Exception {
        destroy();
    }
}