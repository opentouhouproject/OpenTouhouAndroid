package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30;

import android.opengl.EGL14;
import android.opengl.GLES30;
import android.opengl.GLU;
import android.util.Log;

import com.scarlet.math.Vector3f;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Text;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.shader.ShaderManager30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.texture.TextureManager30;
import opentouhou.com.opentouhouandroid.scene.Stage;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/*
 * Renderer implemented with OpenGL ES 3.0.
 */
public class Renderer30 extends Renderer {
    private Text fpsCounter;
    private RenderQuad renderQuad;

    /*
     * Constructor(s).
     */
    public Renderer30(Stage stage) {
        super();

        shaderManager = new ShaderManager30();
        textureManager = new TextureManager30();
        fontManager = new FontManager();

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
        stage.draw();

        frameBuffer.unbind();

        // Post-processing
        //postProcessing();

        // Rebind the deafult framebuffer.


        // Draw the background color.
        GLES30.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        GLES30.glDisable(GLES30.GL_DEPTH_TEST);
        //renderQuad.setTexture(postProcessingBuffer1.getTexture(0));
        //renderQuad.setTexture(frameBuffer.getTexture(1));
        //renderQuad.setShader(getShaderManager().getShaderProgram("GaussianBlur"));
        //renderQuad.setShader(getShaderManager().getShaderProgram("RenderQuad"));
        //renderQuad.draw(stage.getCurrentScene());

        postProcessing();

        frameBuffer.unbind();

        renderQuad.setShader(getShaderManager().getShaderProgram("Combine"));
        renderQuad.useShader();
        //renderQuad.setupTexture(frameBuffer.getTexture(0), GLES30.GL_TEXTURE0, "uTexture");
        renderQuad.setupTexture(frameBuffer.getTexture(0), GLES30.GL_TEXTURE0, "scene");
        renderQuad.setupTexture(postProcessingBuffer2.getTexture(0), GLES30.GL_TEXTURE1, "bloomBlur");
        renderQuad.draw(stage.getCurrentScene());

        // Update fps.
        sysInfo.updateFPS();

        // Draw fps counter.
        fpsCounter.setText(String.valueOf(sysInfo.getFPS()));
        fpsCounter.draw(stage.getCurrentScene());

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
        Camera camera = stage.getCurrentScene().getCamera();

        if (camera != null) {
            camera.setPerspectiveProjectionMatrix(-aspectRatio, aspectRatio, -1, 1, 1, 10);
            camera.setInversePerspectiveProjectionMatrix(-aspectRatio, aspectRatio, -1, 1, 1, 10);
            //camera.setSymmetricPerspectiveProjectionMatrix(48, aspectRatio, 1, 10);
            //camera.setOrthographicProjection(-aspectRatio, aspectRatio, -1, 1, 1, 10);
        }
    }

    public void setProjection() {
        // Update the projection matrix.
        Camera camera = stage.getCurrentScene().getCamera();

        if (camera != null) {
            camera.setPerspectiveProjectionMatrix(-aspectRatio, aspectRatio, -1, 1, 1, 10);
            camera.setInversePerspectiveProjectionMatrix(-aspectRatio, aspectRatio, -1, 1, 1, 10);
            //camera.setSymmetricPerspectiveProjectionMatrix(48, aspectRatio, 1, 10);
            //camera.setOrthographicProjection(-aspectRatio, aspectRatio, -1, 1, 1, 10);
        }
    }

    private void postProcessing() {
        boolean isHorizontal = true;
        boolean isFirstIteration = true;
        int amount = 2;

        for (int i = 0; i < amount; i++) {
            if (isHorizontal) {
                //Log.d("Post Processing", "BIND PP 1");
                postProcessingBuffer1.bind();
            } else {
                postProcessingBuffer2.bind();
            }

            renderQuad.setShader(getShaderManager().getShaderProgram("GaussianBlur"));
            renderQuad.useShader();

            int handle = GLES30.glGetUniformLocation(getShaderManager().getShaderProgram("GaussianBlur").getHandle(), "isHorizontal");
            float val = 1.0f;
            if (isHorizontal) {
                val = 0.0f;
            }
            GLES30.glUniform1f(handle, val);

            if (isFirstIteration) {
                //Log.d("Post Processing", "TEXTURE FB 01");
                renderQuad.setupTexture(frameBuffer.getTexture(1), GLES30.GL_TEXTURE0, "image");
            } else {
                if (isHorizontal) {
                    //Log.d("Post Processing", "TEXTURE pp2 00");
                    renderQuad.setupTexture(postProcessingBuffer2.getTexture(0), GLES30.GL_TEXTURE0, "image");
                } else {
                    //Log.d("Post Processing", "TEXTURE pp1 00");
                    renderQuad.setupTexture(postProcessingBuffer1.getTexture(0), GLES30.GL_TEXTURE0, "image");
                }
            }

            renderQuad.draw(stage.getCurrentScene());

            if (isHorizontal) {
                //Log.d("Post Processing", "BIND PP 1");
                postProcessingBuffer1.unbind();
            } else {
                postProcessingBuffer2.unbind();
            }

            isHorizontal = !isHorizontal;

            if (isFirstIteration) {
                isFirstIteration = false;
            }
        }
    }
}