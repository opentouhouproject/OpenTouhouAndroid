package opentouhou.com.opentouhouandroid.scene;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;

/**
 * Manages a set of scenes.
 * Manages objects that persist between scenes. ex. the audio engine
 */

public abstract class Stage
{
    private String name;

    private Renderer renderer;

    private Scene currentScene;

    // Constructor(s)
    public Stage(String name, Renderer renderer)
    {
        this.name = name;
        this.renderer = renderer;
    }

    // Getter(s)
    public Renderer getRenderer()
    {
        return renderer;
    }

    public Scene getCurrentScene()
    {
        return currentScene;
    }

    // Setter(s)
    public void setCurrentScene(Scene scene)
    {
        currentScene = scene;
    }

    public abstract void setup();
    public abstract void draw();

    @Override
    public String toString()
    {
        return name;
    }
}