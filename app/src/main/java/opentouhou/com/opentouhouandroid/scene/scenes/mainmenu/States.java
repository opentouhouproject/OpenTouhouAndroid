package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

import com.scarlet.scene.State;

/*
 * Holds the states for the main menu.
 */
class States {
    // States
    static final State<MainMenuScreen30> INITIAL_STATE = new StateInitial();

    /*
     * Constructor(s).
     */
    private States() { }

    @Override
    public String toString() {
        return "Main Menu States.";
    }
}