package com.opentouhou.opentouhouandroid.scene.scenes.startmenu;

import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.scene.State;

public class StateInitial implements State<StartMenuScreen30> {
    /*
     * Constructor(s).
     */
    StateInitial() { }

    /*
     * Implement State<T> interface.
     */
    @Override
    public void enter(StartMenuScreen30 scene) {

    }

    @Override
    public State<StartMenuScreen30> handleInput(StartMenuScreen30 scene, MotionEvent event) {
        scene.characterMenu.handleInput(event, scene.getRenderer());
        scene.difficultyMenu.handleInput(event, scene.getRenderer());
        scene.stageMenu.handleInput(event, scene.getRenderer());
        scene.playButton.handleInput(event, scene.getRenderer());

        if (scene.playButton.clicked) {
          scene.startGame = true;
        }

        return null;
    }

    @Override
    public State<StartMenuScreen30> update(StartMenuScreen30 scene) {
        scene.characterMenu.update();
        scene.difficultyMenu.update();
        scene.stageMenu.update();

        return null;
    }

    @Override
    public void draw(StartMenuScreen30 scene) {
        Renderer renderer = scene.getRenderer();

        scene.background.draw(renderer);
        scene.characterMenu.draw(renderer);
        scene.difficultyMenu.draw(renderer);
        scene.stageMenu.draw(renderer);
        scene.playButton.draw(renderer);
    }

    @Override
    public void exit(StartMenuScreen30 scene) {
        // do nothing
    }
}