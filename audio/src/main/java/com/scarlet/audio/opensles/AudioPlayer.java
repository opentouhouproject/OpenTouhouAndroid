package com.scarlet.audio.opensles;

import android.content.Context;
import android.content.res.AssetManager;

import com.scarlet.concurrent.ThreadManager;

/*
 * Android does not have a Java library for calling OpenSL ES functions.
 * We need use JNI to call the C++ libraries that we created to use OpenSL ES.
 */
public class AudioPlayer implements AutoCloseable {
    private AssetManager assetManager;

    // The value of this long is an actual pointer.
    // It is a pointer to a C++ object that we created.
    // If you overwrite this, then you will have created a memory leak with JAVA.
    private long audioManagerPtr = 0;

    /*
     * Load JNI .so on initialisation.
     */
    static {
        System.loadLibrary("audio-engine-jni");
    }

    /*
     * Constructor(s).
     */
    public AudioPlayer(Context context) {
        // Start Engine.
        startAudioManager();

        assetManager = context.getAssets();
        setAssetManager(assetManager);
    }

    // Play BGM
    public void play(String file) {
        //playBGM(file);

        final String myfile = file;
        Runnable runnable = new Runnable() {
            String p = myfile;

            public void run() {
                playBGM(p);
            };
        };

        ThreadManager.queueJob(runnable);
    }

    // Close BGM
    public void stop() {
        //stopBGM();

        Runnable runnable = new Runnable() {
            public void run() {
                stopBGM();
            }
        };

        ThreadManager.queueJob(runnable);
    }

    public void register(String file) {
        /*
        final String myfile = file;
        Runnable runnable = new Runnable() {
            String p = myfile;

            public void run() { registerSound(p); }
        };

        ThreadManager.queueJob(runnable);
        */
        registerSound(file);
    }

    public void queue(String file) {
        /*
        final String myfile = file;
        Runnable runnable = new Runnable() {
            String p = myfile;

            public void run() { playSound(p); }
        };

        ThreadManager.queueJob(runnable);
        */
        playSound(file);
    }

    /*
     * Implement AutoCloseable.
     */
    public void close() {
        closeAudioManager();
        this.audioManagerPtr = 0;
    }

    /*
     * Native methods.
     */
    private native void startAudioManager();

    private native void closeAudioManager();

    private native void setAssetManager(AssetManager assetManager);

    private native void playBGM(String file);

    private native void stopBGM();

    private native void registerSound(String file);

    private native void playSound(String file);
}