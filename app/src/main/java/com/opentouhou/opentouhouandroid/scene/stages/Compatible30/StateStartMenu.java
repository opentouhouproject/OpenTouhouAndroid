package com.opentouhou.opentouhouandroid.scene.stages.Compatible30;

import android.view.MotionEvent;

import com.scarlet.opengles30.Renderer30;
import com.scarlet.scene.State;

import com.opentouhou.opentouhouandroid.scene.scenes.startmenu.StartMenuScreen30;

public class StateStartMenu implements State<OpenGLES30Test> {
    /*
     * Constructor(s).
     */
    StateStartMenu() { }

    /*
     * Implement State<T> interface.
     */
    @Override
    public void enter(OpenGLES30Test stage) {
        // Set the current scene.
        stage.setCurrentScene(stage.startMenuScreen30);

        stage.getCurrentScene().init();

        ((Renderer30)stage.getRenderer()).setProjection();
    }

    @Override
    public State<OpenGLES30Test> handleInput(OpenGLES30Test stage, MotionEvent event) {
      stage.getCurrentScene().handleInput(event);

      if (((StartMenuScreen30) stage.getCurrentScene()).startGame) {
        return States.GAME_SCENE;
      }

      return null;
    }

    @Override
    public State<OpenGLES30Test> update(OpenGLES30Test stage) {
        stage.getCurrentScene().update();

        return null;
    }

    @Override
    public void draw(OpenGLES30Test stage) {
        stage.getCurrentScene().draw();
    }

    @Override
    public void exit(OpenGLES30Test stage) {
        // do nothing
    }
}