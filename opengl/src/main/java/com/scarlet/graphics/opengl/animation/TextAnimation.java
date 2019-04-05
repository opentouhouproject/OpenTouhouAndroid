package com.scarlet.graphics.opengl.animation;

/*
 * Tracks text that changes dynamically.
 */

public class TextAnimation extends FrameAnimation<String> {
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
        System.arraycopy(newSequence, 0, sequence, 0, frameCount);
    }
}