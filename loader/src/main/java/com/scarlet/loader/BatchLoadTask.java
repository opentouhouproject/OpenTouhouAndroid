package com.scarlet.loader;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.scarlet.concurrent.ThreadManager;
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

    // Defines a Handler object that's attached to the UI thread.
    private Handler handler = new Handler(Looper.getMainLooper()) {
        /*
         * handleMessage() defines the operations to perform when
         * the Handler receives a new Message to process.
         */
        @Override
        public void handleMessage(Message inputMessage) {
            // Gets the task from the incoming Message object.
            LoadTask loadTask = (LoadTask) inputMessage.obj;
            BatchLoadTask batchTask = loadTask.batchTask;

            switch (inputMessage.what) {
                case 2: // FINISHED
                    batchTask.update();
                    break;

                default:
                    /*
                     * Pass along other messages from the UI
                     */
                    super.handleMessage(inputMessage);
                    break;
            }

        }
    };

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

    /*
     * Queue the batch job.
     */
    public void startJob() {
        for (LoadTask task : tasks) {
            ThreadManager.queueJob(task.getLoadRunnable());
        }
    }

    /*
     * Handle message from load task thread.
     */
    public void handleState(LoadTask task, LoadTask.State state) {
        switch (state) {
            case FINISHED:
                Message completeMessage = handler.obtainMessage(state.ordinal(), task);
                completeMessage.sendToTarget();
                break;
        }
    }
}