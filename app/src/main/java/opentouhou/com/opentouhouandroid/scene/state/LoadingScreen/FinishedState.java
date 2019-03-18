package opentouhou.com.opentouhouandroid.scene.state.LoadingScreen;

import opentouhou.com.opentouhouandroid.scene.scenes.LoadingScreen30;

public class FinishedState extends LoadingScreenState {
    /*
     * Constructor(s).
     */
    public FinishedState() { }

    /*
     * Implement LoadingScreenState.
     */
    public void enter(LoadingScreen30 scene) {
        scene.loadingMessage.selectAnimation(1);
        scene.sprite.selectAnimation("walkingForward");
    }

    public LoadingScreenState update(LoadingScreen30 scene) {
        scene.background.update();

        scene.petalFall.update();

        scene.title.update();

        scene.loadingMessage.update();

        scene.sprite.update();

        return null;
    }

    public void exit(LoadingScreen30 scene) {
        return;
    }
}