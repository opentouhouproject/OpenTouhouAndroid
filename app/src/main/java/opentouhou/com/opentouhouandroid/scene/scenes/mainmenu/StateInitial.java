package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

import android.util.Log;
import android.view.MotionEvent;

import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.scene.State;

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

                Vector4f ndc = scene.getCamera().convertToNDC(x, y, renderer.getScreenWidth(), renderer.getScreenHeight());

                Vector3f position = scene.getCamera().unProject(ndc);

                Vector3f intersection = scene.getCamera().intersectionPoint(new Vector3f(0, 0, 3), new Vector3f(0, 0, 1), position);

                Log.d("Intersection", "Pos: " + intersection.x + " " + intersection.y + " " + intersection.z);
        }

        scene.menu.handleInput(event, scene.getRenderer());

        return null;
    }

    @Override
    public State<MainMenuScreen30> update(MainMenuScreen30 scene) {
        scene.menu.update();

        return null;
    }

    @Override
    public void exit(MainMenuScreen30 scene) {
        // do nothing
    }
}