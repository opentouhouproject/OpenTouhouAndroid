package com.scarlet.io.event;

import android.view.MotionEvent;

import com.scarlet.io.event.EventQueue;

public class MotionEventQueue extends EventQueue<MotionEvent> {
    /*
     * Constructor(s).
     */
    public MotionEventQueue() {
        super();

        data = new MotionEvent[DEFAULT_CAPACITY];
    }
}