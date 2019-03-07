package opentouhou.com.opentouhouandroid.scene;

import android.os.SystemClock;

import opentouhou.com.opentouhouandroid.actor.OpenGLES.Triangle;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTextureManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.math.Vector3f;

/**
 * Deprecated.
 * Testing.
 */

public class SceneExample1 extends Scene
{
    private Triangle mTriangle;

    public SceneExample1(String name) {
        super(name);
    }

    public void setup(Renderer renderer) {
        camera = new Camera(0, 0, 1.5f, 0, 0, -5, 0, 1, 0);

        float[] vertices2 = {
                -0.5f,  -0.25f, 0.0f,
                0.5f, -0.25f, 0.0f,
                0.5f, -0.559016994f, 0.0f
        };

        float[] colors2 = {
                1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f
        };

        // initialize a triangle
        mTriangle = new Triangle(renderer.getShaderManager().getShaderProgramHandle("Triangle"), vertices2, colors2);
    }

    public void loadTextures(AbstractTextureManager textures) { }

    public void draw() {
        long time = SystemClock.uptimeMillis() % 10000L;
        float angle = (360.0f / 10000.0f) * ((int) time);

        // model view matrix
        Matrix4f mvMat = Matrix4f.multiply(camera.getViewMatrix(), Matrix4f.rotateVector(new Vector3f(0, 0, 1.0f), angle, true));

        // model view projection matrix
        Matrix4f mvpMat = Matrix4f.multiply(camera.getProjectionMatrix(), mvMat);

        // draw triangle
        mTriangle.draw(mvpMat.getArray());
    }
}
