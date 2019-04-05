package com.scarlet.graphics.opengl.animation;

import android.os.SystemClock;

public abstract class FrameAnimation<T> extends Animation {
    protected T[] sequence;

    protected int currentFrame = 0;
    protected int frameCount = 0;

    protected long maxDuration;
    protected long curDuration;
    protected long lastTime;
    protected long curTime;

    public FrameAnimation(String name) {
        super(name);

        maxDuration = 67;
        curDuration = 0;

        lastTime = SystemClock.uptimeMillis();
        curTime = SystemClock.uptimeMillis();
    }

    /*
     * Setter(s).
     */
    public void setMaxDuration(long time) {
        maxDuration = time;
    }

    /*
     * Retrieve the current sequence item.
     */
    public T currentFrame() {
        return sequence[currentFrame];
    }

    /*
     * Move to the next sequence item.
     */
    public void update() {
        curTime = SystemClock.uptimeMillis();
        curDuration += (curTime - lastTime);

        if (curDuration >= maxDuration) {
            if (currentFrame < (frameCount - 1)) {
                currentFrame++;
            } else {
                if (repeat) currentFrame = 0;
            }

            curDuration = 0;
        }

        lastTime = curTime;
    }

    abstract void setSequence(T[] sequence);
}