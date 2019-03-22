package opentouhou.com.opentouhouandroid.scene;

import android.view.MotionEvent;

/*
 * Defines methods that a state in a FSM for the game should implement.
 */
public interface State<T> {
    void enter(T scene);
    State<T> handleInput(T scene, MotionEvent e);
    State<T> update(T scene);
    void exit(T scene);
}