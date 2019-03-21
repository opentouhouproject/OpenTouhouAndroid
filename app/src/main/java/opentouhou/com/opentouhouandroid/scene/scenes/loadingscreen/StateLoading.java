package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

import opentouhou.com.opentouhouandroid.scene.State;

public final class StateLoading implements State<LoadingScreen30> {
    /*
     * Constructor(s).
     */
    StateLoading() { }

    /*
     * Implement the State<T> interface.
     */
    public void enter(LoadingScreen30 scene) {
        scene.getAudioPlayer().play("audio/music/loadingMusic.mp3");
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

        // Check if loading is done.
        if (scene.finishedLoading) {
           return States.FINISHED_STATE;
        }

        return null;
    }

    public void exit(LoadingScreen30 scene) {
        // do nothing.
    }
}