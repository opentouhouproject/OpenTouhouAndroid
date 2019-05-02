package opentouhou.com.opentouhouandroid.scene.stages.Compatible30;

import com.scarlet.scene.State;

public class States {
  static final State<OpenGLES30Test> LOADING_SCREEN = new StateLoadScreen();
  static final State<OpenGLES30Test> MAIN_MENU = new StateMainMenu();
  static final State<OpenGLES30Test> START_MENU = new StateStartMenu();
  static final State<OpenGLES30Test> GAME_SCENE = new StateGameScene();

  private States() { }
}