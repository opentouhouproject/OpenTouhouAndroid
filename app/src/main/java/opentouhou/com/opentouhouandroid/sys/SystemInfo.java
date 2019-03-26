package opentouhou.com.opentouhouandroid.sys;

import android.os.SystemClock;

/*
 * Tracks information related to the device / system.
 */
public class SystemInfo {
    // FPS tracking.
    private int fps;
    private int drawnFrames;
    private long previousTime;

    /*
     * Constructor(s).
     */
    public SystemInfo() {
        fps = 0;
        drawnFrames = 0;
        previousTime = SystemClock.uptimeMillis();
    }

    /*
     * Getter(s).
     */
    public int getFPS() {
        return fps;
    }

    /*
     * Update system information.
     */
    public void updateFPS() {
        // Get the uptime.
        long currentTime = SystemClock.uptimeMillis();

        // Update number of drawn frames.
        drawnFrames++;

        // Update the fps.
        if (currentTime - previousTime >= 1000 ) {
            fps = drawnFrames;
            drawnFrames = 0;
            previousTime = SystemClock.uptimeMillis();
        }
    }
}