package opentouhou.com.opentouhouandroid.scene;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.math.Vector4f;

/**
 * Manages all graphical objects.
 */

public abstract class Scene
{
    private final String name;

    protected boolean ready;

    protected Scene currentScene;

    protected Camera camera;

    protected Vector4f light;

    public Renderer renderer;

    // Constructor
    public Scene(String name, Renderer renderer)
    {
        this.name = name;

        ready = false;

        currentScene = null;

        this.renderer = renderer;
    }

    // Getter(s)
    public Scene getCurrentScene()
    {
        return currentScene;
    }

    public Camera getCamera()
    {
        if (currentScene != null)
        {
            return currentScene.camera;
        }
        else
        {
            return null;
        }
    }

    public Vector4f getLight()
    {
        if (currentScene != null)
        {
            return currentScene.light;
        }
        else
        {
            return null;
        }
    }

    public boolean isReady() { return ready; }

    abstract public void setup(Renderer renderer);
    abstract public void draw();
}