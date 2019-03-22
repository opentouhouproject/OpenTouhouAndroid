package opentouhou.com.opentouhouandroid.scene.loader;

import android.opengl.EGLConfig;
import android.opengl.EGLDisplay;

import static android.opengl.EGL14.EGL_CONTEXT_CLIENT_VERSION;
import static android.opengl.EGL14.EGL_DEFAULT_DISPLAY;
import static android.opengl.EGL14.EGL_NONE;
import static android.opengl.EGL14.EGL_NO_CONTEXT;
import static android.opengl.EGL14.eglCreateContext;
import static android.opengl.EGL14.eglGetDisplay;
import static android.os.Process.setThreadPriority;

public class LoadRunnable implements Runnable {
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

        /*
         * Create the scene.
         */
        task.createScene();

        /*
         * Update state.
         */
        task.state = LoadTask.State.FINISHED;

        /*
         * Notify that the task is completed.
         */
        task.handleState();
    }
}