package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLU;
import android.util.Log;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.shader.ShaderManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.texture.TextureManager;
import opentouhou.com.opentouhouandroid.scene.Scene;
import opentouhou.com.opentouhouandroid.scene.OpenGLES30Test;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Renderer implemented with OpenGL ES 3.0.
 */

public class Renderer30 extends Renderer
{
    // Scene(s)
    private Scene ex3;

    public Renderer30(Context context)
    {
        super(context);

        shaderManager = new ShaderManager(context.getAssets());
        textureManager = new TextureManager();
        fontManager = new FontManager();
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

        // load the scene.
        ex3 = new OpenGLES30Test("example3");
        ex3.setup(this);
    }

    public void onDrawFrame(GL10 unused)
    {
        // Redraw background color.
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);

        // Draw the scene.
        ex3.draw();

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
        float ratio = (float) width / height;

        // Update the projection matrix.
        ex3.getCamera().setFrustumMatrix(-ratio, ratio, -1, 1, 1, 10);
    }
}