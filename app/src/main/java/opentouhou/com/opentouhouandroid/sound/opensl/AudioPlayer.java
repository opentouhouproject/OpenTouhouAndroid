package opentouhou.com.opentouhouandroid.sound.opensl;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * Java does not have a library for OpenSL ES.
 * We need use JNI to call the C++ libraries that we created for OpenSL ES.
 */

public class AudioPlayer implements AutoCloseable
{
    // Load JNI .so on initialisation.
    static
    {
        System.loadLibrary("audio-engine-jni");
    }

    private long audioManagerPtr = 0;

    private AssetManager assetManager;

    // Constructor(s)
    public AudioPlayer(Context context)
    {
        // Start Engine.
        startAudioManager();

        assetManager = context.getAssets();
        setAssetManager(assetManager);
    }

    // Play BGM
    public void play(String file)
    {
        playBGM(file);
    }

    // Close BGM
    public void stop()
    {
        stopBGM();
    }

    // Implement AutoCloseable
    public void close()
    {
        closeAudioManager();
        this.audioManagerPtr = 0;
    }

    // Native
    private native void startAudioManager();

    private native void closeAudioManager();

    private native void setAssetManager(AssetManager assetManager);

    private native void playBGM(String file);

    private native void stopBGM();
}