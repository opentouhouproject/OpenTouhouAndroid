package opentouhou.com.opentouhouandroid.scene.stages.Compatible30;

import android.content.Context;

import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.Renderer30;
import opentouhou.com.opentouhouandroid.io.FileManager;
import opentouhou.com.opentouhouandroid.scene.Scene;
import opentouhou.com.opentouhouandroid.scene.Stage;
import opentouhou.com.opentouhouandroid.scene.State;
import opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen.LoadingScreen30;
import opentouhou.com.opentouhouandroid.scene.scenes.mainmenu.MainMenuScreen30;
import opentouhou.com.opentouhouandroid.sound.opensl.AudioPlayer;

/*
 * Testing OpenGL ES 3.0 implementations.
 */

public class OpenGLES30Test extends Stage {
    // State
    State<OpenGLES30Test> state;

    // Scenes
    LoadingScreen30 loadingScreen30;
    MainMenuScreen30 mainMenuScreen30;

    /*
     * Constructor(s).
     */
    public OpenGLES30Test(String name, Context context) {
        super(name);

        renderer = new Renderer30(this);
        audioPlayer = new AudioPlayer(context);
        fileManager = new FileManager(context);

        state = null;
    }

    // Implement Stage.
    public void setup() {
        state = States.LOADING_SCREEN;
        state.enter(this);
    }

    /*
     * Update the current scene.
     */
    public void update() {
        state.update(this);
    }

    /*
     * Draw the current scene.
     */
    public void draw() {
        getCurrentScene().draw();
    }

    /*
     * Scene Loading Methods.
     */
    private void loadMainMenu() {
        mainMenuScreen30 = new MainMenuScreen30("MM", this);
    }
}