package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

import com.scarlet.scene.State;

import org.jetbrains.annotations.NotNull;

/*
 * Holds the possible states for the loading screen.
 * Visibility should be package-private.
 */
class States {
  static final State<LoadingScreen30> LOADING_STATE = new StateLoading();
  static final State<LoadingScreen30> FINISHED_STATE = new StateFinished();

  private States() { }

  @Override @NotNull
  public String toString() {
    return "Loading Screen States.";
  }
}