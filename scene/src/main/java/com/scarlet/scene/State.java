package com.scarlet.scene;

import android.view.MotionEvent;

/*
 * Defines methods that a state in a FSM for the game should implement.
 */
public interface State<T> {
    /*
     * Execute the enter method when the scene enters the state.
     */
    void enter(T scene);

    /*
     * Execute the handleInput method once per event in the eent queue.
     */
    State<T> handleInput(T scene, MotionEvent e);

    /*
     * Execute the update method once per frame.
     */
    State<T> update(T scene);

    /*
     * Execute the draw method once per frame.
     */
    void draw(T scene);

    /*
     * Execute the exit method when leaving the state.
     */
    void exit(T scene);
}