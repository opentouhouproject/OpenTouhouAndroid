package com.opentouhou.opentouhouandroid.scene.stages.CompatibleVulkan;

import android.content.Context;

import com.opentouhou.opentouhouandroid.scene.scenes.loadingscreen.LoadingScreen20;
import com.scarlet.audio.opensles.AudioPlayer;
import com.scarlet.io.FileManager;
import com.scarlet.opengles20.Renderer20;
import com.scarlet.scene.Stage;

public class VulkanTest extends Stage {
    // Scenes
    private LoadingScreen20 loadingScreen;

    /*
     * Constructor(s).
     */
    public VulkanTest(String name, Context context) {
        super(name);

        renderer = new Renderer20(this);
        audioPlayer = new AudioPlayer(context);
        fileManager = new FileManager(context);
    }

    // Implement Stage.
    public void setup() {
        // Load the scenes.
        loadingScreen = new LoadingScreen20("TEST", this);
        loadingScreen.setup();

        // Set the current scene.
        setCurrentScene(loadingScreen);

        // Start the audio.
        getAudioPlayer().play("audio/music/loadingMusic.mp3");
    }

    /*
     * Implement the handleInput method.
     */
    public void handleInput() {
        // do nothing
    }

    public void update() {
        getCurrentScene().update();
    }

    public void draw() {
        getCurrentScene().draw();
    }
}