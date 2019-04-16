package com.scarlet.scene;

import com.scarlet.audio.opensles.AudioPlayer;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.io.FileManager;
import com.scarlet.io.event.MotionEventQueue;
import com.scarlet.math.Vector4f;

/*
 * Manages a set of scenes.
 * Manages objects and components that persist between scenes.
 */
public abstract class Stage {
    private String name;
    private Scene currentScene;

    protected Renderer renderer;
    protected AudioPlayer audioPlayer;
    protected FileManager fileManager;
    protected MotionEventQueue motionEventQueue;

    /*
     * Constructor(s).
     */
    public Stage(String name) {
        this.name = name;
        currentScene = null;

        renderer = null;
        audioPlayer = null;
        fileManager = null;
        motionEventQueue = null;
    }

    /*
     * Getter(s).
     */
    public String getName() {
        return name;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public MotionEventQueue getMotionEventQueue() { return motionEventQueue; }

    public Vector4f getLight() { return currentScene.getLight(); }

    /*
     * Setter(s).
     */
    public void setCurrentScene(Scene scene) {
        currentScene = scene;
    }

    /*
     * OpenGL ES version dependant.
     */
    public abstract void setup();
    public abstract void handleInput();
    public abstract void update();
    public abstract void draw();

    @Override
    public String toString() {
        return "Scene " + name;
    }
}