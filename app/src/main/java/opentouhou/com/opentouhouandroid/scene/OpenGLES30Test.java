package opentouhou.com.opentouhouandroid.scene;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.scene.LoadingScreen;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class OpenGLES30Test extends Scene
{
    private LoadingScreen loadingScreen;

    // Constructor
    public OpenGLES30Test(String name, Renderer renderer)
    {
        super(name, renderer);
    }

    public void setup(Renderer renderer)
    {
        // Load the scenes.
        loadingScreen = new LoadingScreen("TEST", renderer);
        loadingScreen.setup(renderer);

        // Set the current scene and ready flag.
        currentScene = loadingScreen;
        ready = true;
    }

    public void draw()
    {
        if (currentScene.isReady()) currentScene.draw();
    }
}