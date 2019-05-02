package opentouhou.com.opentouhouandroid.scene.scenes.game;

import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.scene.State;

class StateInitial implements State<GameScene30> {
  StateInitial() { }

  /*
   * Implement State<T> interface.
   */
  @Override
  public void enter(GameScene30 scene) {

  }

  @Override
  public State<GameScene30> handleInput(GameScene30 scene, MotionEvent e) {
    return null;
  }

  @Override
  public State<GameScene30> update(GameScene30 scene) {
    return null;
  }

  @Override
  public void draw(GameScene30 scene) {
    Renderer renderer = scene.getRenderer();

    scene.background.draw(renderer);
  }

  @Override
  public void exit(GameScene30 scene) {

  }
}