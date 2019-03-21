package opentouhou.com.opentouhouandroid.scene.stages.Compatible30;

import opentouhou.com.opentouhouandroid.scene.Scene;
import opentouhou.com.opentouhouandroid.scene.State;
import opentouhou.com.opentouhouandroid.scene.loader.BatchLoadTask;
import opentouhou.com.opentouhouandroid.scene.loader.LoadManager;
import opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen.LoadingScreen30;
import opentouhou.com.opentouhouandroid.scene.scenes.mainmenu.MainMenuScreen30;

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
        Scene[] scenes = { stage.mainMenuScreen30 };
        LoadManager.startBatchSceneLoad(new BatchLoadTask(scenes, stage.loadingScreen30));
    }

    @Override
    public void handleInput(OpenGLES30Test stage) {
        // do nothing
    }

    @Override
    public State<OpenGLES30Test> update(OpenGLES30Test stage) {
        stage.getCurrentScene().update();

        return null;
    }

    @Override
    public void exit(OpenGLES30Test stage) {
        // do nothing
    }
}