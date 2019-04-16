package com.scarlet.graphics.opengl.animation;

import android.os.SystemClock;

public abstract class InterpolatedAnimation extends Animation {
    private long currentTime;
    private long startTime;
    private long duration;

    public float t;

    public InterpolatedAnimation(String name) {
        super(name);

        currentTime = 0;
        startTime = 0;
        duration = 0;
    }

    public void setDuration(long time) {
        duration = time;
    }

    public void start() {
        startTime = SystemClock.uptimeMillis();
        currentTime = SystemClock.uptimeMillis();
        t = 0;
    }

    public void update() {
        // Get the current time.
        currentTime = SystemClock.uptimeMillis();

        // Compute the normalised duration.
        t = (float) (currentTime - startTime) / (float) duration;

        if (t > 1.0f) {
            t = 1.0f;
        }

        // Set the updated values.
        update(t);
    }

    public boolean isDone() {
        return t >= 1.0f;
    }

    protected abstract void update(float t);
}