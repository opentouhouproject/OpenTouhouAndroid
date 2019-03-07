package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.shader.ShaderManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.texture.TextureManager;
import opentouhou.com.opentouhouandroid.scene.OpenGLES20Test;
import opentouhou.com.opentouhouandroid.scene.Scene;

/**
 * Renderer implemented with OpenGL ES 2.0.
 */

public class Renderer20 extends Renderer
{
    // Scene(s)
    private Scene testScene;

    public Renderer20(Context context)
    {
        super(context);

        shaderManager = new ShaderManager(context.getAssets());
        textureManager = new TextureManager();
        fontManager = new FontManager();
    }

    // Implement GLSurfaceView.Renderer interface.
    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {
        Log.e("KJ_Debug", GLES20.glGetString(GLES20.GL_VERSION));

        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Use culling to remove back faces.
        GLES20.glEnable(GLES20.GL_CULL_FACE);

        // Enable depth testing.
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        // Enable blending.
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA );

        // load scene
        testScene = new OpenGLES20Test("ES20Test");
        testScene.setup(this);
    }

    public void onDrawFrame(GL10 unused)
    {
        // Redraw background color.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Draw the scene.
        testScene.draw();

        // Error handling.
        int errorCode;
        boolean first = true;

        do {
            errorCode = GLES20.glGetError();

            if (errorCode != 0)
            {
                if (first)
                {
                    Log.e("KJ_Debug", "FRAME");
                    first = false;
                }
                Log.e("KJ_Debug", GLU.gluErrorString(errorCode));
            }
        } while(errorCode != 0);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        // Set the Viewport.
        GLES20.glViewport(0, 0, width, height);

        // Calculate the screen ratio.
        float ratio = (float) width / height;

        // Update the projection matrix.
        // This projection matrix is applied to object coordinates in the onDrawFrame() method.
        testScene.getCamera().setFrustumMatrix(-ratio, ratio, -1, 1, 1, 10);
    }
}