package com.scarlet.concurrent;

import com.scarlet.scene.LoadingScreen;
import com.scarlet.scene.Scene;

/*
 * Represents a task to load multiple scenes.
 */
public class BatchLoadTask {
    // State
    public enum State {
        NOT_STARTED, IN_PROGRESS, FINISHED
    }
    public BatchLoadTask.State state;

    // Finished count
    private volatile int completionCount = 0;

    // Info needed to create new scene.
    public LoadTask[] tasks;
    public Scene load;

    /*
     * Constructor(s).
     */
    public BatchLoadTask(Scene[] scenes, Scene load) {
        state = State.NOT_STARTED;

        this.load = load;

        tasks = new LoadTask[scenes.length];
        for (int i = 0; i < scenes.length; i++) {
            if (scenes[i] == null) {
                throw new RuntimeException("Attempted to load a null scene!");
            }

            tasks[i] = new LoadTask(scenes[i], this);
        }
    }

    /*
     * Update status.
     */
    public synchronized void update() {
        completionCount++;

        if (completionCount == tasks.length) {
            ((LoadingScreen) load).finishedLoading = true;
        }
    }
}