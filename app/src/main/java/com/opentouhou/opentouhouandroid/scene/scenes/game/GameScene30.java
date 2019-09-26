package com.opentouhou.opentouhouandroid.scene.scenes.game;

import android.view.MotionEvent;

import com.opentouhou.opentouhouandroid.entity.sprite.Sprite;
import com.opentouhou.opentouhouandroid.io.xml.SceneParser;
import com.scarlet.graphics.opengl.Camera;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.font.Text;
import com.scarlet.graphics.opengl.Version;
import com.scarlet.math.Vector4f;
import com.scarlet.scene.Scene;
import com.scarlet.scene.Stage;
import com.scarlet.scene.State;

import java.io.InputStreamReader;

import com.opentouhou.opentouhouandroid.entity.background.Background;

/**
 * Represents a stage in the game.
 *
 */
public class GameScene30 extends Scene {
  /*
   * State
   */
  private State<GameScene30> state = null;

  /*
   * Entities
   */
  public Background background;

  public Sprite playerSprite;

  public Text livesText;
  public Text bombText;
  public Text grazeText;
  public Text scoreText;

  public Text graze;
  public Text score;
  public Text bossName;

  // TODO:
  // public ParticleManager particleManager;
  // public EntityManager entityManager;
  // public Sprite boss;
  // public HealthBar healthBar;

  /*
   * Constructor(s)
   */
  public GameScene30(String name, Stage stage) {
    super(name, stage);
  }

  /*
   * Implement Scene interface
   */

  /*
   * Handles the initial loading of assets for the scene.
   */
  @Override
  public void setup() {
    // Retrieve the renderer from the stage.
    Renderer renderer = stage.getRenderer();

    // Load the assets.
    InputStreamReader reader = stage.getFileManager().openRawAsset("scene/demo/loadInfo30.xml");
    SceneParser.parse(reader, this);

    // Create the camera.
    camera = new Camera(0.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);

    // Create light source(s).
    light = new Vector4f(0.0f, 0.0f, 2.0f, 0.0f);

    // Create background.
    background = new Background(renderer, Version.OPENGL_ES_30, true);
    background.setTexture(renderer.getTextureManager().getTexture("art/game_demo_bg.png"));

    // Done Loading
    ready = true;
  }

  @Override
  public void init() {
    state = States.INITIAL_STATE;
  }

  @Override
  public void handleInput(MotionEvent event) {
    state.handleInput(this, event);
  }

  @Override
  public void update() {
    state.update(this);
  }

  @Override
  public void draw() {
    state.draw(this);
  }
}