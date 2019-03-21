package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

/*
 * Holds the states for the main menu.
 */

public class States {
    // States
    public static final State INITIAL_STATE = new StateInitial();

    /*
     * Constructor(s).
     */
    private States() { }

    @Override
    public String toString() {
        return "Main Menu States.";
    }
}