package com.scarlet.opengles30;

import android.opengl.EGL14;
import android.opengl.GLES30;
import android.opengl.GLU;
import android.util.Log;

import com.scarlet.graphics.opengl.Text;
import com.scarlet.math.Vector3f;

import com.scarlet.graphics.opengl.Camera;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.opengles30.font.FontManager30;
import com.scarlet.opengles30.postprocessing.PostProcessing;
import com.scarlet.opengles30.shader.ShaderManager30;
import com.scarlet.opengles30.texture.TextureManager30;
import com.scarlet.scene.Stage;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/*
 * Renderer implemented with OpenGL ES 3.0.
 */
public class Renderer30 extends Renderer {
    private Text fpsCounter;
    public RenderQuad renderQuad;

    public Stage stage;

    /*
     * Constructor(s).
     */
    public Renderer30(Stage stage) {
        super();

        shaderManager = new ShaderManager30();
        textureManager = new TextureManager30();
        fontManager = new FontManager30();

        this.stage = stage;
    }

    /*
     * Implement GLSurfaceView.Renderer interface.
     */
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
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

        // Get the current OpenGL context.
        eglContext = EGL14.eglGetCurrentContext();

        // load the stage.
        stage.setup();

        // Load the fps counter.
        fpsCounter = new Text(this.getFontManager().getFont("fonts/popstar/popstarpop128.xml"));
        fpsCounter.setPosition(new Vector3f(2.4f, 5.4f, 4))
                  .setScaling(280f)
                  .setShader("Font2");
    }

    public void onDrawFrame(GL10 unused) {
        /*
         * Temporary Solution.
         */
        stage.handleInput();
        stage.update();

        // Bind the framebuffer.
        frameBuffer.bind();

        // Draw the background color.
        GLES30.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);

        // Enable depth test.
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);

        // Draw the scene.
        light = stage.getLight();
        stage.draw();

        frameBuffer.unbind();

        // Draw the background color.
        GLES30.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        GLES30.glDisable(GLES30.GL_DEPTH_TEST);

        postProcessing();

        renderQuad.setShader(getShaderManager().getShaderProgram("Combine"));
        renderQuad.useShader();
        renderQuad.setupTexture(frameBuffer.getTexture(0), GLES30.GL_TEXTURE0, "scene");
        renderQuad.setupTexture(postProcessingBuffer2.getTexture(0), GLES30.GL_TEXTURE1, "bloomBlur");
        renderQuad.draw(this);

        // Update fps.
        sysInfo.updateFPS();

        // Draw fps counter.
        fpsCounter.setText(String.valueOf(sysInfo.getFPS()));
        fpsCounter.draw(this);

        // Error handling.
        int errorCode = GLES30.glGetError();
        if (errorCode != 0) {
            Log.e("KJ_Debug", GLU.gluErrorString(errorCode));
        }
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // Set the Viewport.
        GLES30.glViewport(0, 0, width, height);

        // Create the framebuffer.
        frameBuffer = new FrameBuffer30(width, height, 4);
        postProcessingBuffer1 = new FrameBuffer30(width, height, 1);
        postProcessingBuffer2 = new FrameBuffer30(width, height, 1);
        renderQuad = new RenderQuad(this, (float)width, (float)height, frameBuffer.getTexture(0));

        // Update info.
        screenWidth = width;
        screenHeight = height;
        aspectRatio = (float) width / (float) height;
        Log.d("Aspect Ratio: ", "AR: " + aspectRatio);

        // Update the projection matrix.
        camera = stage.getCurrentScene().getCamera();

        if (camera != null) {
            camera.setPerspectiveProjectionMatrix(-aspectRatio, aspectRatio, -1, 1, 1, 10);
            camera.setInversePerspectiveProjectionMatrix(-aspectRatio, aspectRatio, -1, 1, 1, 10);
            //camera.setSymmetricPerspectiveProjectionMatrix(48, aspectRatio, 1, 10);
            //camera.setOrthographicProjection(-aspectRatio, aspectRatio, -1, 1, 1, 10);
        }
    }

    public void setProjection() {
        // Update the projection matrix.
        camera = stage.getCurrentScene().getCamera();

        if (camera != null) {
            camera.setPerspectiveProjectionMatrix(-aspectRatio, aspectRatio, -1, 1, 1, 10);
            camera.setInversePerspectiveProjectionMatrix(-aspectRatio, aspectRatio, -1, 1, 1, 10);
            //camera.setSymmetricPerspectiveProjectionMatrix(48, aspectRatio, 1, 10);
            //camera.setOrthographicProjection(-aspectRatio, aspectRatio, -1, 1, 1, 10);
        }
    }

    private void postProcessing() {
        PostProcessing.GaussianBlur(this);
    }
}