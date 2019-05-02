package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

import com.scarlet.scene.State;

import org.jetbrains.annotations.NotNull;

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

  @Override @NotNull
  public String toString() {
    return "Main Menu States.";
  }
}