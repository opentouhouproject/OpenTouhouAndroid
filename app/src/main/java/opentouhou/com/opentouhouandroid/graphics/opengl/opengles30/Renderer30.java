package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30;

import android.opengl.EGL14;
import android.opengl.GLES30;
import android.opengl.GLU;
import android.util.Log;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Text;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.shader.ShaderManager30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.texture.TextureManager30;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.scene.Stage;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Renderer implemented with OpenGL ES 3.0.
 */

public class Renderer30 extends Renderer {
    private float ratio;

    // Scene(s)

    private Text fpsCounter;

    public Renderer30(Stage stage) {
        super();

        shaderManager = new ShaderManager30();
        textureManager = new TextureManager30();
        fontManager = new FontManager();

        this.stage = stage;
    }

    // Implement GLSurfaceView.Renderer interface.
    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {
        // Log the OpenGL ES version.
        Log.e("KJ_Debug", GLES30.glGetString(GLES30.GL_VERSION));

        // Set the background frame color.
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Enable culling to remove back faces.
        GLES30.glEnable(GLES30.GL_CULL_FACE);

        // Enable depth testing.
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);

        // Enable blending.
        GLES30.glEnable(GLES30.GL_BLEND);
        GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA );

        // ccc
        eglContext = EGL14.eglGetCurrentContext();

        // load the scene.
        stage.setup();

        fpsCounter = new Text(this.getFontManager().getFont("fonts/popstar/popstar16.xml"));
    }

    public void onDrawFrame(GL10 unused)
    {
        /**
         * Temporary Solution.
         */
        stage.handleInput();
        stage.update();

        // Redraw background color.
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);

        // Draw the scene.
        stage.draw();

        // Measure fps.
        long currentTime = System.currentTimeMillis();
        numberOfFrames++;
        if (currentTime - lastTime >= 1000 )
        {
            lastFPS = numberOfFrames;
            numberOfFrames = 0;
            lastTime = System.currentTimeMillis();
        }

        // Draw fps counter.
        fpsCounter.render(String.valueOf(lastFPS), new Vector3f(2.4f, 5.4f, 4), 40f, "Font2", stage.getCurrentScene());

        // Error handling.
        int errorCode = GLES30.glGetError();
        if (errorCode != 0)
        {
            Log.e("KJ_Debug", GLU.gluErrorString(errorCode));
        }
    }

    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        // Set the Viewport.
        GLES30.glViewport(0, 0, width, height);

        // Calculate the screen ratio.
        ratio = (float) width / (float) height;

        // Update the projection matrix.
        Camera camera = stage.getCurrentScene().getCamera();

        if (camera != null) {
            camera.setPerspectiveProjectionMatrix(-ratio, ratio, -1, 1, 1, 10);
            //camera.setSymmetricPerspectiveProjectionMatrix(48, ratio, 1, 10);
            //camera.setOrthographicProjection(-ratio, ratio, -1, 1, 1, 10);
        }
    }

    public void setProjection() {
        // Update the projection matrix.
        Camera camera = stage.getCurrentScene().getCamera();

        if (camera != null) {
            camera.setPerspectiveProjectionMatrix(-ratio, ratio, -1, 1, 1, 10);
            //camera.setSymmetricPerspectiveProjectionMatrix(48, ratio, 1, 10);
            //camera.setOrthographicProjection(-ratio, ratio, -1, 1, 1, 10);
        }
    }
}