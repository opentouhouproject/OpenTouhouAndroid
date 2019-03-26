package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

import opentouhou.com.opentouhouandroid.scene.State;

/*
 * Holds the possible states for the loading screen.
 * Visibility should be package-private.
 */
class States {
    static final State<LoadingScreen30> LOADING_STATE = new StateLoading();
    static final State<LoadingScreen30> FINISHED_STATE = new StateFinished();

    /*
     * Constructor(s).
     */
    private States() { }

    @Override
    public String toString() {
        return "Loading Screen States.";
    }
}