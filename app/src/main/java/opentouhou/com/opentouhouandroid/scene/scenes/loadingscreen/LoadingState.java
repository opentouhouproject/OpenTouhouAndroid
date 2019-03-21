package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

public final class LoadingState extends LoadingScreenState {
    /*
     * Constructor(s).
     */
    LoadingState() { }

    /*
     * Implement LoadingScreenState.
     */
    public void enter(LoadingScreen30 scene) {
        scene.getAudioPlayer().play("audio/music/loadingMusic.mp3");
    }

    public void handleInput(LoadingScreen30 scene) {
        // do nothing
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

        return null;
    }

    public void exit(LoadingScreen30 scene) {
        // do nothing.
    }
}