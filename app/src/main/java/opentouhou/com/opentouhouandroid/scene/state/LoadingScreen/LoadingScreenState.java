package opentouhou.com.opentouhouandroid.scene.state.LoadingScreen;

import opentouhou.com.opentouhouandroid.scene.scenes.LoadingScreen30;

public abstract class LoadingScreenState {
    public static LoadingScreenState LOADING_STATE = new LoadingState();
    public static LoadingScreenState FINISHED_STATE = new FinishedState();

    /*
     * Constructor(s).
     */
    public LoadingScreenState() { }

    public abstract void enter(LoadingScreen30 scene);
    public abstract LoadingScreenState update(LoadingScreen30 scene);
    public abstract void exit(LoadingScreen30 scene);
}