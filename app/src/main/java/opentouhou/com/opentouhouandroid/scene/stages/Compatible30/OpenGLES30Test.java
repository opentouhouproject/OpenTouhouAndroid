package opentouhou.com.opentouhouandroid.scene.stages.Compatible30;

import android.content.Context;
import android.view.MotionEvent;

import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.Renderer30;
import opentouhou.com.opentouhouandroid.io.FileManager;
import opentouhou.com.opentouhouandroid.io.eventqueue.MotionEventQueue;
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
    private State<OpenGLES30Test> state;

    // Scenes
    public LoadingScreen30 loadingScreen30;
    MainMenuScreen30 mainMenuScreen30;

    /*
     * Constructor(s).
     */
    public OpenGLES30Test(String name, Context context) {
        super(name);

        renderer = new Renderer30(this);
        audioPlayer = new AudioPlayer(context);
        fileManager = new FileManager(context);
        motionEventQueue = new MotionEventQueue();

        state = null;
    }

    /*
     * Implement the Stage abstract class.
     */
    @Override
    public void setup() {
        state = States.LOADING_SCREEN;
        state.enter(this);
    }

    @Override
    public void handleInput() {
        while (!motionEventQueue.isEmpty()) {
            State<OpenGLES30Test> result = state.handleInput(this, motionEventQueue.dequeue());

            if (result != null) {
                // Exit the current state.
                state.exit(this);

                // Enter the new state.
                state = result;
                state.enter(this);
            }
        }
    }

    @Override
    public void update() {
        State<OpenGLES30Test> result = state.update(this);

        if (result != null) {
            // Exit the current state.
            state.exit(this);

            // Enter the new state.
            state = result;
            state.enter(this);
        }
    }

    @Override
    public void draw() {
        getCurrentScene().draw();
    }
}