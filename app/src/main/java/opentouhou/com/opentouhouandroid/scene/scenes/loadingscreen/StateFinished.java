package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

import opentouhou.com.opentouhouandroid.scene.State;

public class StateFinished implements State<LoadingScreen30> {
    /*
     * Constructor(s).
     */
    StateFinished() { }

    /*
     * Implement the State<T> interface.
     */
    public void enter(LoadingScreen30 scene) {
        scene.loadingMessage.selectAnimation(1);
        scene.sprite.selectAnimation("walkingForward");
    }

    public void handleInput(LoadingScreen30 scene) {
        // do nothing
    }

    public State<LoadingScreen30> update(LoadingScreen30 scene) {
        scene.background.update();
        scene.petalFall.update();
        scene.title.update();
        scene.loadingMessage.update();
        scene.sprite.update();

        return null;
    }

    public void exit(LoadingScreen30 scene) {
        // do nothing
    }
}