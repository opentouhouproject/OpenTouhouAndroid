package opentouhou.com.opentouhouandroid.scene;

import android.content.Context;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.io.FileManager;
import opentouhou.com.opentouhouandroid.sound.opensl.AudioPlayer;

/*
 * Manages a set of scenes.
 * Manages objects that persist between scenes. ex. the audio engine
 */

public abstract class Stage {
    private String name;

    private Renderer renderer;

    private AudioPlayer audioPlayer;

    private FileManager fileManager;

    private Scene currentScene;

    /*
     * Constructor(s).
     */
    public Stage(String name, Renderer renderer, Context context) {
        this.name = name;
        this.renderer = renderer;
        audioPlayer = new AudioPlayer(context);
        fileManager = new FileManager(context);
    }

    /*
     * Getter(s).
     */
    public String getName() {
        return name;
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

    public Scene getCurrentScene() {
        return currentScene;
    }

    /*
     * Setter(s).
     */
    protected void setCurrentScene(Scene scene) {
        currentScene = scene;
    }

    public abstract void setup();
    public abstract void draw();

    @Override
    public String toString() {
        return "Scene " + name;
    }
}