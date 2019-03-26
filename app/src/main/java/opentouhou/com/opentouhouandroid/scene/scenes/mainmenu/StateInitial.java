package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

import android.view.MotionEvent;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.scene.State;

import static android.view.MotionEvent.ACTION_DOWN;

public class StateInitial implements State<MainMenuScreen30> {
    /*
     * Constructor(s).
     */
    StateInitial() { }

    /*
     * Implement the State<T> interface.
     */
    @Override
    public void enter(MainMenuScreen30 scene) {
        scene.getAudioPlayer().stop();
        scene.getAudioPlayer().play("audio/music/soundcloud_bgm1.mp3");
    }

    @Override
    public State<MainMenuScreen30> handleInput(MainMenuScreen30 scene, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case ACTION_DOWN:
                Renderer renderer = scene.getRenderer();
                Vector3f ndc = scene.getCamera().convertToNDC(x, y, renderer.getScreenWidth(), renderer.getScreenHeight());
        }

        return null;
    }

    @Override
    public State<MainMenuScreen30> update(MainMenuScreen30 scene) {
        return null;
    }

    @Override
    public void exit(MainMenuScreen30 scene) {
        // do nothing
    }
}