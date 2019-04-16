package opentouhou.com.opentouhouandroid.scene.scenes.startmenu;

import com.scarlet.scene.State;

class States {
    // States
    static final State<StartMenuScreen30> INITIAL_STATE = new StateInitial();

    /*
     * Constructor(s).
     */
    private States() { }

    @Override
    public String toString() {
        return "Start Menu States.";
    }
}