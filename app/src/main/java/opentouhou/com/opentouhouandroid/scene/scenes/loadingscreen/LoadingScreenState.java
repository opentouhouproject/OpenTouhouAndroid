package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

public abstract class LoadingScreenState {
    public static final LoadingScreenState LOADING_STATE = new LoadingState();
    public static final LoadingScreenState FINISHED_STATE = new FinishedState();

    public abstract void enter(LoadingScreen30 scene);
    public abstract void handleInput(LoadingScreen30 scene);
    public abstract LoadingScreenState update(LoadingScreen30 scene);
    public abstract void exit(LoadingScreen30 scene);
}