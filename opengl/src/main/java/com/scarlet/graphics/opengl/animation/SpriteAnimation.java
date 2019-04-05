package com.scarlet.graphics.opengl.animation;

import com.scarlet.graphics.opengl.texture.Texture;

/*
 * Represents a sprite animation.
 */
public class SpriteAnimation extends FrameAnimation<Texture> {
    // Holds the scale values for an image.
    private float[] stretch;

    /*
     * Constructor(s).
     */
    public SpriteAnimation(String name) {
        super(name);
    }

    /*
     * Setter(s).
     */
    public void setStretch(float[] newStretch) {
        stretch = new float[newStretch.length];

        System.arraycopy(newStretch, 0, stretch, 0, newStretch.length);
    }

    /*
     * Return the strech factor for the current sprite image.
     */
    public float currentStretch() {
        return stretch[currentFrame];
    }

    /*
     * Implement setSequence.
     */
    public void setSequence(Texture[] newSequence) {
        currentFrame = 0;
        frameCount = newSequence.length;

        sequence = new Texture[frameCount];
        System.arraycopy(newSequence, 0, sequence, 0, frameCount);
    }
}