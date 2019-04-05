package com.scarlet.graphics.opengl.animation;

import com.scarlet.graphics.opengl.texture.Texture;

public class SpriteAnimation extends Animation<Texture> {
    private float[] stretch;

    /*
     * Constructor(s).
     */
    public SpriteAnimation(String name) {
        super(name);
    }

    public void setStretch(float[] newStretch) {
        stretch = new float[newStretch.length];

        for (int i = 0; i < newStretch.length; i++) {
            stretch[i] = newStretch[i];
        }
    }

    /*
     * Return the strech factor for the sprite image.
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
        for (int i = 0; i < frameCount; i++) {
            sequence[i] = newSequence[i];
        }
    }
}