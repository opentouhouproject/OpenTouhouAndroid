package opentouhou.com.opentouhouandroid.scene;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;
import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.sound.opensl.AudioPlayer;

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

    public AudioPlayer getAudioPlayer() { return stage.getAudioPlayer(); }

    /*
     * Asset load methods.
     */
    public void loadVertexShader(String name, String path) {
        stage.getRenderer().getShaderManager().createVertexShader(name, path, stage.getFileManager());
    }

    public void loadFragmentShader(String name, String path) {
        stage.getRenderer().getShaderManager().createFragmentShader(name, path, stage.getFileManager());
    }

    public void loadShaderProgram(String name, String vertex, String fragment) {
        stage.getRenderer().getShaderManager().createShaderProgram(name, vertex, fragment);
    }

    public void loadTexture(String path, TextureManager.Options option) {
        stage.getRenderer().getTextureManager().loadAssetBitmap(path, option, stage.getFileManager());
    }

    public void loadFont(String path) {
        stage.getRenderer().getFontManager().loadFont(path, stage.getRenderer(), stage.getFileManager());
    }

    // Implemented by sub classes.
    abstract public void setup();
    abstract public void update();
    abstract public void draw();

    @Override
    public String toString()
    {
        return "Scene: " + name;
    }
}