package opentouhou.com.opentouhouandroid.actor;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.Texture;

/**
 * Represents a sequence of sprites in an animation.
 */

public class Animation
{
    private String name;

    private int count = 0;
    private int currentFrame = 0;
    private int frameCount = 0;
    private Texture[] textureHandles;
    public float[] stretch;


    public Animation(String name)
    {
        this.name = name;
    }

    public String getName() { return name; }

    public float currentStretch()
    {
        return stretch[currentFrame];
    }

    public Texture currentFrame()
    {
        return textureHandles[currentFrame];
    }

    public void nextFrame()
    {
        if (currentFrame < (frameCount - 1))
        {
            if (count < 4)
            {
                count++;
            }
            else
            {
                currentFrame++;
                count = 0;
            }
        }
        else
        {
            if (count < 4)
            {
                count++;
            }
            else
            {
                count = 0;
                currentFrame = 0;
            }
        }
    }

    public void addSequence(Texture[] textures)
    {
        currentFrame = 0;
        frameCount = textures.length;

        textureHandles = new Texture[frameCount];
        for (int i = 0; i < frameCount; i++)
        {
            textureHandles[i] = textures[i];
        }
    }

    @Override
    public String toString()
    {
        return "Animation: " + name;
    }
}