package opentouhou.com.opentouhouandroid.scene.stages;

import android.content.Context;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.scene.Stage;
import opentouhou.com.opentouhouandroid.scene.scenes.LoadingScreen;

/*
 * Testing OpenGL ES 3.0 implementations.
 */

public class OpenGLES30Test extends Stage
{
    // Scenes
    private LoadingScreen loadingScreen;

    // Constructor
    public OpenGLES30Test(String name, Renderer renderer, Context context)
    {
        super(name, renderer, context);
    }

    public void setup()
    {
        // Load the scenes.
        loadingScreen = new LoadingScreen("TEST", this);
        loadingScreen.setup();

        // Set the current scene.
        setCurrentScene(loadingScreen);

        // Start the audio.
        getAudioPlayer().play("audio/music/loadingMusic.mp3");
    }

    public void draw()
    {
        getCurrentScene().draw();
    }
}