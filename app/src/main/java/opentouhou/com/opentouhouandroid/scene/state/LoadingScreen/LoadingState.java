package opentouhou.com.opentouhouandroid.scene.state.LoadingScreen;

import android.os.SystemClock;

import opentouhou.com.opentouhouandroid.scene.scenes.LoadingScreen30;

public final class LoadingState extends LoadingScreenState {
    /*
     * Constructor(s).
     */
    public LoadingState() { }

    /*
     * Implement LoadingScreenState.
     */
    public void enter(LoadingScreen30 scene) {
        return;
    }

    public LoadingScreenState update(LoadingScreen30 scene) {
        scene.background.update();

        scene.petalFall.update();

        scene.title.update();

        scene.loadingMessage.update();

        scene.sprite.update();

        // Check if loading is done.
        if (scene.finishedLoading) {
           return LoadingScreenState.FINISHED_STATE;
        }

        if (scene.cur - scene.start > 20000) {
            scene.finishedLoading = true;
        }
        else {
            scene.cur = SystemClock.uptimeMillis();
        }

        return null;
    }

    public void exit(LoadingScreen30 scene) {
        return;
    }
}
