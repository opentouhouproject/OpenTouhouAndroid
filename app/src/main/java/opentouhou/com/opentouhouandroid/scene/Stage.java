package opentouhou.com.opentouhouandroid.scene;

/**
 * Manages a set of scenes.
 */

public abstract class Stage
{
    private String name;

    private int currentScene = -1;
    private Scene[] scenes;

    public Stage(String name)
    {
        this.name = name;
    }

    public abstract void setup();
    public abstract void draw();

    @Override
    public String toString()
    {
        return name;
    }
}