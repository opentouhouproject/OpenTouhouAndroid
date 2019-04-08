package com.scarlet.ui;

import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.animation.UIAnimation;

public abstract class UIEntity {
    protected boolean isVisible;

    protected boolean isAnimated = false;
    protected UIAnimation[] animations;
    protected int currentAnim = -1;
    private int count = 0;

    private OnEventListener listener;

    /*
     * Check the visibility of the UI item.
     */
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /*
     * Add an animation.
     */
    public void addAnimation(UIAnimation animation) {
        animations[count] = animation;
        count++;
    }

    /*
     * Add a listener.
     */
    public void registerOnEventListener(OnEventListener listener) {
        this.listener = listener;
    }

    /*
     * Execute a listener.
     */
    public void executeSynchronousListener() {
        // Check if the listener is registered.
        if (listener != null) {
            // Invoke the callback method.
            listener.onEvent();
        }
    }

    public void executeAsynchronousListener() {
        // An Async task always executes in new thread.
        new Thread(new Runnable() {
            public void run() {
                // Check if listener is registered.
                if (listener != null) {
                    // Invoke the callback method.
                    listener.onEvent();
                }
            }
        }).start();
    }

    /*
     * Abstract methods to implement.
     */
    public abstract void handleInput(MotionEvent event, Renderer renderer);
    public abstract void update();
    public abstract void draw(Renderer renderer);
}