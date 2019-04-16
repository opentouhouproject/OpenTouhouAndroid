package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Renderer;
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
        //scene.getAudioPlayer().play("audio/music/loadingMusic.mp3");
        scene.getAudioPlayer().queue("audio/music/bad_apple.wav");
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
    public void draw(LoadingScreen30 scene) {
        Renderer renderer = scene.getRenderer();

        scene.background.draw(renderer);
        scene.petalFall.draw(renderer);
        scene.title.draw(renderer);
        scene.loadingMessage.draw(renderer);
        scene.sprite.draw(renderer);
    }

    @Override
    public void exit(LoadingScreen30 scene) {
        // do nothing.
    }
}