package opentouhou.com.opentouhouandroid.scene.stages.Compatible30;

import android.view.MotionEvent;

import com.scarlet.concurrent.BatchLoadTask;
import com.scarlet.concurrent.LoadManager;
import com.scarlet.scene.Scene;

import com.scarlet.scene.State;
import opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen.LoadingScreen30;
import opentouhou.com.opentouhouandroid.scene.scenes.mainmenu.MainMenuScreen30;
import opentouhou.com.opentouhouandroid.scene.scenes.startmenu.StartMenuScreen30;

public class StateLoadScreen implements State<OpenGLES30Test> {
    /*
     * Constructor(s).
     */
    StateLoadScreen() { }

    /*
     * Implement State<T> interface.
     */
    @Override
    public void enter(OpenGLES30Test stage) {
        // setup loading screen
        stage.loadingScreen30 = new LoadingScreen30("TEST", stage);
        stage.loadingScreen30.setup();
        stage.loadingScreen30.init();

        // Set the current scene.
        stage.setCurrentScene(stage.loadingScreen30);

        // LOAD BABY LOAD
        stage.mainMenuScreen30 = new MainMenuScreen30("MM", stage);
        stage.startMenuScreen30 = new StartMenuScreen30("StartMenu", stage);

        Scene[] scenes = { stage.mainMenuScreen30, stage.startMenuScreen30 };
        LoadManager.startBatchSceneLoad(new BatchLoadTask(scenes, stage.loadingScreen30));
    }

    @Override
    public State<OpenGLES30Test> handleInput(OpenGLES30Test stage, MotionEvent event) {
        stage.getCurrentScene().handleInput(event);

        if (((LoadingScreen30) stage.getCurrentScene()).userContinue) {
            return States.MAIN_MENU;
        }

        return null;
    }

    @Override
    public State<OpenGLES30Test> update(OpenGLES30Test stage) {
        stage.getCurrentScene().update();

        return null;
    }

    @Override
    public void draw(OpenGLES30Test stage) {
        stage.getCurrentScene().draw();
    }

    @Override
    public void exit(OpenGLES30Test stage) {
        // do nothing
    }
}