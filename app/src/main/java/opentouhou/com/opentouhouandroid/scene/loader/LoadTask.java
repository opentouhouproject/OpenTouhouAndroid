package opentouhou.com.opentouhouandroid.scene.loader;

import android.opengl.EGLContext;

import com.scarlet.scene.Scene;

/*
 * Represents a task to load a single scene.
 */
public class LoadTask {
    // State
    public enum State {
        NOT_STARTED, IN_PROGRESS, FINISHED
    }
    public State state;

    // Thread
    private Thread thread;

    // A handle to the object that creates the thread pools.
    private LoadManager loadManager;

    // The scene to load/setup.
    private Scene scene;

    // The batch job associated with this task.
    public BatchLoadTask batchTask;

    /*
     * Constructor(s).
     */
    public LoadTask(Scene scene, BatchLoadTask batchTask) {
        state = State.NOT_STARTED;
        thread = null;
        loadManager = LoadManager.instance;

        this.scene = scene;
        this.batchTask = batchTask;
    }

    /*
     * Setter(s).
     */
    public void setLoadThread(Thread thread) {
        this.thread = thread;
    }

    /*
     * Creates a new runnable.
     */
    public LoadRunnable getLoadRunnable() {
        return new LoadRunnable(this);
    }

    /*
     * Creates the new scene.
     */
    public void createScene() {
        scene.setup();
    }

    /*
     * Sends a message to the thread pool manager on the UI thread.
     */
    public void handleState() {
        loadManager.handleState(this, this.state);
    }

    public EGLContext getContext() { return scene.getRenderer().getContext(); }
}