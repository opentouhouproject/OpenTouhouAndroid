package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

import android.view.MotionEvent;

import opentouhou.com.opentouhouandroid.scene.State;

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
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case ACTION_DOWN:
                scene.userContinue = true;
        }

        return null;
    }

    public State<LoadingScreen30> update(LoadingScreen30 scene) {
        //scene.background.update();
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