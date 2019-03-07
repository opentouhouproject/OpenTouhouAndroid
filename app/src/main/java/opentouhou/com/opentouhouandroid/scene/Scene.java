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

    protected Camera camera;

    protected Vector4f light;

    // Constructor
    public Scene(String name)
    {
        this.name = name;
    }

    // Getters
    public Camera getCamera()
    {
        return camera;
    }

    public Vector4f getLight() { return light; }

    abstract public void setup(Renderer renderer);
    abstract public void draw();
}