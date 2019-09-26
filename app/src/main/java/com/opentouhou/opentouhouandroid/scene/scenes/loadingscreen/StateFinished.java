package com.opentouhou.opentouhouandroid.scene.scenes.loadingscreen;

import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.scene.State;

import static android.view.MotionEvent.ACTION_DOWN;

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

    public State<LoadingScreen30> handleInput(LoadingScreen30 scene, MotionEvent event) {
        switch(event.getAction()) {
            case ACTION_DOWN:
                scene.userContinue = true;
        }

        return null;
    }

    public State<LoadingScreen30> update(LoadingScreen30 scene) {
        scene.petalFall.update();
        scene.loadingMessage.update();
        scene.sprite.update();

        return null;
    }

    public void draw(LoadingScreen30 scene) {
        Renderer renderer = scene.getRenderer();

        scene.background.draw(renderer);
        scene.petalFall.draw(renderer);
        scene.title.draw(renderer);
        scene.loadingMessage.draw(renderer);
        scene.sprite.draw(renderer);
        scene.loadingFinishedMsg.draw(renderer);
    }

    public void exit(LoadingScreen30 scene) {
        // do nothing
    }
}