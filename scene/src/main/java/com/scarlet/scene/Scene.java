package com.scarlet.scene;

import android.view.MotionEvent;

import com.scarlet.audio.opensles.AudioPlayer;

import com.scarlet.graphics.opengl.Camera;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.texture.TextureManager;
import com.scarlet.math.Vector4f;

import org.jetbrains.annotations.NotNull;

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

    public Renderer getRenderer() {
        return stage.getRenderer();
    }

    // Implemented by sub classes.
    abstract public void setup();
    abstract public void init();
    abstract public void handleInput(MotionEvent event);
    abstract public void update();
    abstract public void draw();

    @Override @NotNull
    public String toString() {
        return "Scene: " + name;
    }
}