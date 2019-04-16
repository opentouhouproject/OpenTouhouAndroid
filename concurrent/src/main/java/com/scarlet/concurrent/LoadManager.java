package com.scarlet.concurrent;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * Constructs thread pools and work queues.
 */
public class LoadManager {
    /*
     * Create a single static instance of the load manager.
     */
    public static LoadManager instance;

    /*
     * Gets the number of available cores.
     * (not always the same as the maximum number of cores)
     */
    private static final int NUMBER_OF_CORES;

    /*
     * Sets the amount of time an idle thread waits before terminating.
     */
    private static final int KEEP_ALIVE_TIME;

    /*
     * Sets the Time Unit to seconds.
     */
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT;

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

    // Thread pool.
    private ThreadPoolExecutor sceneLoadThreadPool;

    // A queue of Runnables.
    private final BlockingQueue<Runnable> sceneLoadWorkQueue;

    /*
     * Constructor(s).
     */
    static {
        /*
         * Ensure fields are initialised BEFORE we call the private constructor.
         */
        NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        KEEP_ALIVE_TIME = 1;
        KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

        instance = new LoadManager();
    }

    private LoadManager() {
        Log.d("Load Manager", "NUM CORES: " + NUMBER_OF_CORES);
        Log.d("Load Manager", "KEEP ALIVE TIME: " + KEEP_ALIVE_TIME);
        Log.d("Load Manager", "KEEP ALIVE UNIT: " + KEEP_ALIVE_TIME_UNIT);

        // Instantiate the queue.
        sceneLoadWorkQueue = new LinkedBlockingQueue<>();

        // Creates a thread pool manager.
        sceneLoadThreadPool = new ThreadPoolExecutor(
                NUMBER_OF_CORES, // initial pool size
                NUMBER_OF_CORES, // maximum pool size
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                sceneLoadWorkQueue);
    }

    /*
     * Load a batch job.
     */
    public static void startBatchSceneLoad(BatchLoadTask batchTask) {
        for (LoadTask task : batchTask.tasks) {
            instance.sceneLoadThreadPool.execute(task.getLoadRunnable());
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