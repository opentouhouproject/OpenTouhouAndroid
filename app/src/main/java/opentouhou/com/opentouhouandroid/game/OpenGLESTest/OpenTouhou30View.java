package opentouhou.com.opentouhouandroid.game.OpenGLESTest;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

import opentouhou.com.opentouhouandroid.scene.Stage;

public class OpenTouhou30View extends GLSurfaceView {
    private volatile boolean isPlaying;

    /*
     * Constructor(s).
     */
    public OpenTouhou30View(Context context, Stage stage) {
        super(context);

        /*
         * Create an OpenGL ES 3.0 context.
         */
        setEGLContextClientVersion(3);

        /*
         * Set a custom EGLConfigChooser.
         */
        setEGLConfigChooser(new MyConfigChooser());

        /*
         * Set the renderer.
         */
        setRenderer(stage.getRenderer());

        /*
         * Set the render mode.
         * Android caps the framerate at the screen refresh rate.
         *
         * GLSurfaceView.RENDERMODE_WHEN_DIRTY
         * GLSurfaceView.RENDERMODE_CONTINUOUSLY
         */
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onPause() {
        isPlaying = false;
    }

    @Override
    public void onResume() {
        isPlaying = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return true;
    }

    private class MyConfigChooser implements GLSurfaceView.EGLConfigChooser {
        @Override
        public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
            /*
             * Set the attributes/options.
             */
            int attribs[] = {
                    EGL10.EGL_LEVEL, 0,
                    EGL10.EGL_RENDERABLE_TYPE, 4,  // EGL_OPENGL_ES2_BIT
                    EGL10.EGL_COLOR_BUFFER_TYPE, EGL10.EGL_RGB_BUFFER,
                    EGL10.EGL_RED_SIZE, 8,
                    EGL10.EGL_GREEN_SIZE, 8,
                    EGL10.EGL_BLUE_SIZE, 8,
                    EGL10.EGL_DEPTH_SIZE, 16,
                    EGL10.EGL_SAMPLE_BUFFERS, 1,
                    EGL10.EGL_SAMPLES, 4,  // This is for 4x MSAA.
                    EGL10.EGL_NONE
            };

            /*
             * Set the configuration.
             */
            EGLConfig[] configs = new EGLConfig[1];
            int[] configCounts = new int[1];
            egl.eglChooseConfig(display, attribs, configs, 1, configCounts);

            /*
             * Do error checking.
             */
            if (configCounts[0] == 0) {
                return null;
            }
            else {
                return configs[0];
            }
        }
    }
}