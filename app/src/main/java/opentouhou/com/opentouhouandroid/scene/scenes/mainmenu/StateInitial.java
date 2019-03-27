package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

import android.util.Log;
import android.view.MotionEvent;

import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
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

                Vector4f ndc = scene.getCamera().convertToNDC(x, y, renderer.getScreenWidth(), renderer.getScreenHeight());

                Vector3f position = scene.getCamera().unProject(ndc, 0);

                Log.d("World Coord Conversion", "Pos: " + position.x + " " + position.y + " " + position.z);

                // TEST
                //Vector4f teste1 = Matrix4f.multiply(scene.getCamera().getViewMatrix(), new Vector4f(1, 1, 9, 1));

                //Log.d("World Coord Conversion", "Eye: " + teste1.x + " " + teste1.y + " " + teste1.z + " " + teste1.w);

                //Vector4f teste2 = Matrix4f.multiply(scene.getCamera().getInvViewMatrix(), teste1);

                //Log.d("World Coord Conversion", "Model: " + teste2.x + " " + teste2.y + " " + teste2.z + " " + teste2.w);

                //Vector4f testt2 = Matrix4f.multiply(scene.getCamera().getProjectionMatrix(), testtl);

                //Log.d("World Coord Conversion", "Proj: " + testt2.x + " " + testt2.y + " " + testt2.z + " " + testt2.w);

                //Vector4f testt3 = Matrix4f.multiply(scene.getCamera().getInvProjectionMatrix(), testt2);

                //Log.d("World Coord Conversion", "Eye: " + testt3.x + " " + testt3.y + " " + testt3.z + " " + testtl.w);
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