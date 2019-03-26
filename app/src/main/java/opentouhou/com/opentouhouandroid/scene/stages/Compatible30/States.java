package opentouhou.com.opentouhouandroid.scene.stages.Compatible30;

import opentouhou.com.opentouhouandroid.scene.State;

public class States {
    public static final State<OpenGLES30Test> LOADING_SCREEN = new StateLoadScreen();
    public static final State<OpenGLES30Test> MAIN_MENU = new StateMainMenu();

    /*
     * Constructor(s).
     */
    private States() { }
}