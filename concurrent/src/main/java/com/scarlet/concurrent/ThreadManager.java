package com.scarlet.concurrent;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * Constructs thread pools and work queues.
 */
public class ThreadManager {
    /*
     * Create a single static instance of the load manager.
     */
    public static ThreadManager instance;

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

    // Thread pool.
    private ThreadPoolExecutor threadPool;

    // A queue of Runnables.
    private final BlockingQueue<Runnable> workQueue;

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

        instance = new ThreadManager();
    }

    private ThreadManager() {
        Log.d("Load Manager", "NUM CORES: " + NUMBER_OF_CORES);
        Log.d("Load Manager", "KEEP ALIVE TIME: " + KEEP_ALIVE_TIME);
        Log.d("Load Manager", "KEEP ALIVE UNIT: " + KEEP_ALIVE_TIME_UNIT);

        // Instantiate the queue.
        workQueue = new LinkedBlockingQueue<>();

        // Creates a thread pool manager.
        threadPool = new ThreadPoolExecutor(
                NUMBER_OF_CORES, // initial pool size
                NUMBER_OF_CORES, // maximum pool size
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                workQueue);
    }

    /*
     * Load a generic task.
     */
    public static void queueJob(Runnable runnable) {
        instance.threadPool.execute(runnable);
    }
}