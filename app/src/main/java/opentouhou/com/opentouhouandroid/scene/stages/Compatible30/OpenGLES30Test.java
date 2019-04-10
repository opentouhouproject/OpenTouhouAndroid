package opentouhou.com.opentouhouandroid.scene.stages.Compatible30;

import android.content.Context;

import com.scarlet.audio.opensles.AudioPlayer;

import com.scarlet.opengles30.Renderer30;

import com.scarlet.io.event.MotionEventQueue;
import com.scarlet.io.FileManager;

import com.scarlet.scene.Stage;
import com.scarlet.scene.State;

import opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen.LoadingScreen30;
import opentouhou.com.opentouhouandroid.scene.scenes.mainmenu.MainMenuScreen30;
import opentouhou.com.opentouhouandroid.scene.scenes.startmenu.StartMenuScreen30;

/*
 * Testing OpenGL ES 3.0 implementations.
 */
public class OpenGLES30Test extends Stage {
    // State
    private State<OpenGLES30Test> state;

    // Scenes
    LoadingScreen30 loadingScreen30;
    MainMenuScreen30 mainMenuScreen30;
    StartMenuScreen30 startMenuScreen30;

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
        state.draw(this);
    }
}