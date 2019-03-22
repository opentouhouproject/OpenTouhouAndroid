package opentouhou.com.opentouhouandroid.io.eventqueue;

import android.view.MotionEvent;

public class MotionEventQueue extends EventQueue<MotionEvent> {
    /*
     * Constructor(s).
     */
    public MotionEventQueue() {
        super();

        data = new MotionEvent[DEFAULT_CAPACITY];
    }
}