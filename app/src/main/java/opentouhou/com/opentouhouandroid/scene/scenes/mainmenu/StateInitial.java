package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.scene.State;

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
        scene.menu.handleInput(event, scene.getRenderer());

        if (scene.menu.getClickValue().equals("Start")) {
            scene.selectedValue = "Start";
        }

        return null;
    }

    @Override
    public State<MainMenuScreen30> update(MainMenuScreen30 scene) {
        scene.menu.update();

        return null;
    }

    @Override
    public void draw(MainMenuScreen30 scene) {
        Renderer renderer = scene.getRenderer();

        scene.background.draw(renderer);
        scene.menu.draw(renderer);
        scene.title.draw(renderer);
    }

    @Override
    public void exit(MainMenuScreen30 scene) {
        // do nothing
    }
}