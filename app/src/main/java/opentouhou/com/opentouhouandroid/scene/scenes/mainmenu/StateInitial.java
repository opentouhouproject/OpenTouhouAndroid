package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

import opentouhou.com.opentouhouandroid.scene.State;

public class StateInitial implements State<MainMenuScreen30> {
    /*
     * Constructor(s).
     */
    StateInitial() { }

    /*
     * Implement the State<T> interface.
     */
    @Override
    public void enter(MainMenuScreen30 scene) {
        scene.getAudioPlayer().stop();
        scene.getAudioPlayer().play("audio/music/bgm3.mp3");
    }

    @Override
    public void handleInput(MainMenuScreen30 scene) {
        // do nothing
    }

    @Override
    public State<MainMenuScreen30> update(MainMenuScreen30 scene) {
        return null;
    }

    @Override
    public void exit(MainMenuScreen30 scene) {
        // do nothing
    }
}