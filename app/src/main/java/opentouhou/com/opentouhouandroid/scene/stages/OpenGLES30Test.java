package opentouhou.com.opentouhouandroid.scene.stages;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.scene.Stage;
import opentouhou.com.opentouhouandroid.scene.scenes.LoadingScreen;
import opentouhou.com.opentouhouandroid.sound.opensl.AudioPlayer;

/**
 * Testing OpenGL ES 3.0 implementations.
 */

public class OpenGLES30Test extends Stage
{
    // Scenes
    private LoadingScreen loadingScreen;

    // Audio Manager
    private AudioPlayer audioPlayer;

    // Constructor
    public OpenGLES30Test(String name, Renderer renderer)
    {
        super(name, renderer);
    }

    public void setup()
    {
        // Load the scenes.
        loadingScreen = new LoadingScreen("TEST", this);
        loadingScreen.setup();

        // Setup the audio.
        audioPlayer = new AudioPlayer(getRenderer().getContext());

        // Set the current scene.
        setCurrentScene(loadingScreen);
        // Start the audio.
        audioPlayer.play("audio/music/loadingMusic.mp3");
    }

    public void draw()
    {
        getCurrentScene().draw();
    }
}