package opentouhou.com.opentouhouandroid.scene.stages.Compatible30;

import android.view.MotionEvent;

import com.scarlet.opengles30.Renderer30;
import com.scarlet.scene.State;

public class StateGameScene implements State<OpenGLES30Test> {
  StateGameScene() { }

  @Override
  public void enter(OpenGLES30Test stage) {
    // Set the current scene.
    stage.setCurrentScene(stage.gameDemo30);

    stage.getCurrentScene().init();

    ((Renderer30)stage.getRenderer()).setProjection();
  }

  @Override
  public State<OpenGLES30Test> handleInput(OpenGLES30Test stage, MotionEvent event) {
    stage.getCurrentScene().handleInput(event);

    return null;
  }

  @Override
  public State<OpenGLES30Test> update(OpenGLES30Test stage) {
    stage.getCurrentScene().update();

    return null;
  }

  @Override
  public void draw(OpenGLES30Test stage) {
    stage.getCurrentScene().draw();
  }

  @Override
  public void exit(OpenGLES30Test stage) {

  }
}