package opentouhou.com.opentouhouandroid.scene.stages;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.scene.Stage;
import opentouhou.com.opentouhouandroid.scene.scenes.LoadingScreen20;
import opentouhou.com.opentouhouandroid.sound.opensl.AudioPlayer;

public class OpenGLES20Test extends Stage
{
    // Scenes
    private LoadingScreen20 loadingScreen;

    // Audio Manager
    private AudioPlayer audioPlayer;

    // Constructor(s)
    public OpenGLES20Test(String name, Renderer renderer)
    {
        super(name, renderer);
    }

    public void setup()
    {
        // Load the scenes.
        loadingScreen = new LoadingScreen20("TEST", this);
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