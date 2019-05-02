package com.scarlet.loader;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;

import static android.os.Process.setThreadPriority;

public class LoadRunnable implements Runnable {
    // OpenGL ES
    private EGLDisplay eglDisplay;
    private EGLConfig eglConfig;
    private EGLContext eglContext;
    private EGLSurface eglSurface;

    private LoadTask task;

    /*
     * Constructor(s).
     */
    public LoadRunnable(LoadTask task) {
        this.task = task;
    }

    /*
     * Defines the code to run for this task.
     */
    @Override
    public void run() {
        /*
         * Moves the current thread into the background.
         */
        setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        /*
         * Stores the current Thread in the task instance.
         * Allows the task instance to interrupt the thread.
         */
        task.setLoadThread(Thread.currentThread());

        /*
         * Update the task state.
         */
        task.state = LoadTask.State.IN_PROGRESS;

        //setupEGL(EGL14.EGL_NO_CONTEXT);
        setupEGL(task.getContext());

        /*
         * Create the scene.
         */
        task.createScene();

        cleanupEGL();

        /*
         * Update state.
         */
        task.state = LoadTask.State.FINISHED;

        /*
         * Notify that the task is completed.
         */
        task.handleState();
    }

    private void setupEGL(EGLContext sharedContext) {
        /*
         * eglGetDisplay obtains the EGL display connection to the native display (display_id).
         * EGL_DEFAULT_DISPLAY returns the default display connection.
         * EGL_NO_DISPLAY is returned when no matching display connection is found.
         */
        eglDisplay = EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY);

        if (eglDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new RuntimeException("No display connection found.");
        }

        /*
         * eglInitialize initializes the display connection obtained from eglGetDisplay.
         * Pass in the major and minor versions of the EGL implementation.
         */
        int[] eglMajorVersions = { 3 };
        int[] eglMinorVersions = { 0 };

        boolean result = EGL14.eglInitialize(eglDisplay, eglMajorVersions, 0, eglMinorVersions, 0);

        if (!result) {
            throw new RuntimeException("Failed to initialize display connection.");
        }

        /*
         * eglChooseConfig returns in configs a list of all EGL frame buffer configurations that match the attributes specified.
         */
        int configAttributes[] = {
                EGL14.EGL_RENDERABLE_TYPE, EGL14.EGL_OPENGL_ES2_BIT,    // very important!
                EGL14.EGL_SURFACE_TYPE, EGL14.EGL_PBUFFER_BIT,          // we will create a pixelbuffer surface
                EGL14.EGL_RED_SIZE,   8,
                EGL14.EGL_GREEN_SIZE, 8,
                EGL14.EGL_BLUE_SIZE,  8,
                EGL14.EGL_ALPHA_SIZE, 8,     // if you need the alpha channel
                EGL14.EGL_DEPTH_SIZE, 16,    // if you need the depth buffer
                EGL14.EGL_NONE
        };

        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfigs = new int[1];
        boolean configResult = EGL14.eglChooseConfig(eglDisplay, configAttributes, 0,
                configs, 0, 1,
                numConfigs, 0);

        if (!configResult) {
            throw new RuntimeException("Failed to find configurations.");
        }

        eglConfig = configs[0];

        /*
         * eglCreateContext creates an EGL rendering context for the current rendering API.
         */
        int[] contextAttributes = {
                EGL14.EGL_CONTEXT_CLIENT_VERSION, 3,              // very important!
                EGL14.EGL_NONE
        };

        eglContext = EGL14.eglCreateContext(eglDisplay, eglConfig, sharedContext, contextAttributes, 0);

        /*
         * eglCreatePbufferSurface creates an off-screen pixel buffer surface and returns its handle.
         */
        int[] surfaceAttributes = {
                EGL14.EGL_WIDTH, 255,
                EGL14.EGL_HEIGHT, 255,
                EGL14.EGL_NONE
        };

        eglSurface = EGL14.eglCreatePbufferSurface(eglDisplay, eglConfig, surfaceAttributes, 0);

        /*
         * eglMakeCurrent binds context to the current rendering thread and to the draw and read surfaces.
         */
        EGL14.eglMakeCurrent(eglDisplay, eglSurface, eglSurface, eglContext);
    }

    private void cleanupEGL() {
        EGL14.eglMakeCurrent(eglDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
        EGL14.eglDestroyContext(eglDisplay, eglContext);
        EGL14.eglDestroySurface(eglDisplay, eglSurface);

        /*
         * Release the resources associated with the EGL display connection.
         */
        EGL14.eglTerminate(eglDisplay);

        /*
         * Reset values.
         */
        eglDisplay = EGL14.EGL_NO_DISPLAY;
        eglSurface = EGL14.EGL_NO_SURFACE;
        eglContext = EGL14.EGL_NO_CONTEXT;
    }
}