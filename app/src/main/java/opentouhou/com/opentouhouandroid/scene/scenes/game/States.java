package opentouhou.com.opentouhouandroid.scene.scenes.game;

import com.scarlet.scene.State;

/*
 * Holds all possible states.
 */
class States {
  static final State<GameScene30> INITIAL_STATE = new StateInitial();

  private States() { }

  @Override
  public String toString() {
    return "Game States.";
  }
}