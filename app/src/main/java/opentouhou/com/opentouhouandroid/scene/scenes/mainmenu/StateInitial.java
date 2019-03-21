package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

public class StateInitial extends State {
    public StateInitial() { }

    /*
     * Implement State.
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
    public State update(MainMenuScreen30 scene) {
        return null;
    }

    @Override
    public void exit(MainMenuScreen30 scene) {
        // do nothing
    }
}