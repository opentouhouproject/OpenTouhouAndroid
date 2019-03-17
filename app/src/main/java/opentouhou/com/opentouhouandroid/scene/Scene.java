package opentouhou.com.opentouhouandroid.scene;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.math.Vector4f;

/**
 * Manages a logical set of game objects.
 */

public abstract class Scene
{
    private final String name;

    protected Stage stage;

    protected boolean ready;

    protected Camera camera;

    protected Vector4f light;

    // Constructor(s)
    public Scene(String name, Stage stage)
    {
        this.name = name;
        this.stage = stage;

        // Set initial values.
        ready = false;
        camera = new Camera(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        light = new Vector4f();
    }

    // Getter(s)
    public Renderer getRenderer() { return stage.getRenderer(); }

    public boolean isReady() { return ready; }

    public Camera getCamera() { return camera; }

    public Vector4f getLight() { return light; }

    abstract public void setup();
    abstract public void update();
    abstract public void draw();

    @Override
    public String toString()
    {
        return "Scene: " + name;
    }
}