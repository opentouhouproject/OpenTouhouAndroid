package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

/*
 * A main menu state.
 */

public abstract class State {
    public abstract void enter(MainMenuScreen30 scene);
    public abstract void handleInput(MainMenuScreen30 scene);
    public abstract State update(MainMenuScreen30 scene);
    public abstract void exit(MainMenuScreen30 scene);
}