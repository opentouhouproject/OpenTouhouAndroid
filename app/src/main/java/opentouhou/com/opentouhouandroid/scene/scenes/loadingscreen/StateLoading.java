package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

import android.view.MotionEvent;

import com.scarlet.scene.State;

public final class StateLoading implements State<LoadingScreen30> {
    /*
     * Constructor(s).
     */
    StateLoading() { }

    /*
     * Implement the State<T> interface.
     */
    @Override
    public void enter(LoadingScreen30 scene) {
        scene.getAudioPlayer().play("audio/music/loadingMusic.mp3");
    }

    @Override
    public State<LoadingScreen30> handleInput(LoadingScreen30 scene, MotionEvent event) {
        return null;
    }

    @Override
    public State<LoadingScreen30> update(LoadingScreen30 scene) {
        //scene.background.update();
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

    @Override
    public void exit(LoadingScreen30 scene) {
        // do nothing.
    }
}