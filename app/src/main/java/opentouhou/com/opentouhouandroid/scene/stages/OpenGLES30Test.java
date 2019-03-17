package opentouhou.com.opentouhouandroid.scene.stages;

import android.content.Context;

import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.Renderer30;
import opentouhou.com.opentouhouandroid.io.FileManager;
import opentouhou.com.opentouhouandroid.scene.Stage;
import opentouhou.com.opentouhouandroid.scene.scenes.LoadingScreen30;
import opentouhou.com.opentouhouandroid.scene.scenes.MainMenuScreen30;
import opentouhou.com.opentouhouandroid.sound.opensl.AudioPlayer;

/*
 * Testing OpenGL ES 3.0 implementations.
 */

public class OpenGLES30Test extends Stage {
    // States
    private enum States {
        Loading, MainMenu
    }

    private States state;

    // Scenes
    private LoadingScreen30 loadingScreen30;
    private MainMenuScreen30 mainMenuScreen30;

    /*
     * Constructor(s).
     */
    public OpenGLES30Test(String name, Context context) {
        super(name);

        renderer = new Renderer30(this);
        audioPlayer = new AudioPlayer(context);
        fileManager = new FileManager(context);
    }

    // Implement Stage.
    public void setup() {
        mainMenuScreen30 = new MainMenuScreen30("MM", this);

        // Load the scenes.
        loadingScreen30 = new LoadingScreen30("TEST", this);
        loadingScreen30.setup();

        state = States.Loading;

        // Set the current scene.
        setCurrentScene(loadingScreen30);

        // Start the audio.
        getAudioPlayer().play("audio/music/loadingMusic.mp3");
    }

    /*
     * Update the current scene.
     */
    public void update() {
        getCurrentScene().update();
    }

    /*
     * Draw the current scene.
     */
    public void draw() {
        getCurrentScene().draw();
    }
}