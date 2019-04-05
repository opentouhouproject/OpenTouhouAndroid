package com.scarlet.graphics.opengl.animation;

/*
 * Tracks text that changes dynamically.
 */

public class TextAnimation extends Animation<String> {
    /*
     * Constructor(s).
     */
    public TextAnimation(String name) {
        super(name);
    }

    /*
     * Implement setSequence.
     */
    public void setSequence(String[] newSequence) {
        currentFrame = 0;
        frameCount = newSequence.length;

        sequence = new String[frameCount];
        for (int i = 0; i < frameCount; i++) {
            sequence[i] = newSequence[i];
        }
    }
}