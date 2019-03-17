package opentouhou.com.opentouhouandroid.graphics.opengl.common.animation;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.Texture;

public class SpriteAnimation extends Animation<Texture> {
    public float[] stretch;

    /*
     * Constructor(s).
     */
    public SpriteAnimation(String name) {
        super(name);
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