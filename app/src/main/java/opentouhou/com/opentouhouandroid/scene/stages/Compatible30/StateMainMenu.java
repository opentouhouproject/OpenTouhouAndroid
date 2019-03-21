package opentouhou.com.opentouhouandroid.scene.stages.Compatible30;

import opentouhou.com.opentouhouandroid.scene.State;

public class StateMainMenu implements State<OpenGLES30Test> {
    /*
     * Constructor(s).
     */
    StateMainMenu() { }

    /*
     * Implement State<T> interface.
     */
    @Override
    public void enter(OpenGLES30Test stage) {
        // Set the current scene.
        stage.setCurrentScene(stage.mainMenuScreen30);
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