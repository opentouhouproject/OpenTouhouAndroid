package opentouhou.com.opentouhouandroid.actor;

import android.util.Log;

public class TextAnimation
{
    private String name;

    private int count = 0;
    private int currentFrame = 0;
    private int frameCount = 0;
    private String[] strings;
    public float[] stretch;


    public TextAnimation(String name)
    {
        this.name = name;
    }

    public String getName() { return name; }

    public float currentStretch()
    {
        return stretch[currentFrame];
    }

    public String currentFrame()
    {
        return strings[currentFrame];
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

    public void addSequence(String[] sequence)
    {
        currentFrame = 0;
        frameCount = sequence.length;

        strings = new String[frameCount];
        for (int i = 0; i < frameCount; i++)
        {
            strings[i] = sequence[i];
        }
    }

    @Override
    public String toString()
    {
        return "Animation: " + name;
    }
}