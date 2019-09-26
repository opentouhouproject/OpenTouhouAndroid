package com.opentouhou.opentouhouandroid.entity.background;

import com.opentouhou.opentouhouandroid.entity.GameEntity;
import com.scarlet.graphics.opengl.GraphicsObject;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.Version;
import com.scarlet.graphics.opengl.texture.Texture;

/**
 * The Background class is responsible for handling the state of a screen background.
 */
public class Background extends GameEntity {
  private GraphicsObject drawable;

  /**
   * Constructor.
   * @param renderer The Renderer to use for drawing.
   * @param version The OpenGL version.
   * @param async Flag to use asynchronous loading.
   */
  public Background(Renderer renderer, Version version, boolean async) {
    switch (version) {
      case OPENGL_ES_20:
        drawable = new BackgroundDrawable20(renderer);
        break;

      case OPENGL_ES_30:
        drawable = new BackgroundDrawable30(renderer, async);
        break;
    }
  }

  /**
   * Set the texture.
   * @param texture The new texture.
   */
  public void setTexture(Texture texture) {
    drawable.setTexture(texture);
  }

  /**
   * Implement abstract class GameEntity.
   */
  public void update() { }

  /**
   * Implement abstract class GameEntity.
   * @param renderer
   */
  public void draw(Renderer renderer) {
    drawable.render(renderer);
  }
}