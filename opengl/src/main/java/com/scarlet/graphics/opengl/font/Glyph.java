package com.scarlet.graphics.opengl.font;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.math.Vector2f;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

/**
 * Represents a single UNICODE character.
 */
public abstract class Glyph {
  // UNICODE number.
  protected int id;

  // Top left coordinate of the texture rectangle.
  protected Vector2f glyphCoordinates;
  //protected int x, y;

  // Dimensions of the texture rectangle.
  protected Vector2f glyphDimensions;
  //protected int width, height;

  // Constructor(s)
  public Glyph(int id, int x, int y, int width, int height) {
    this.id = id;
    glyphCoordinates = new Vector2f(x, y);
    glyphDimensions = new Vector2f(width, height);
  }

  // Getter(s)
  public int getUnicodeId() { return id; }

  public float getWidth() { return glyphDimensions.x; }

  public float getHeight() { return glyphDimensions.y; }

  // Set the drawable.
  public abstract void generate(int texWidth, int texHeight, String assetPath, Renderer renderer);

  // Draw the glyph on the screen.
  public abstract void draw(Vector3f position, float scale, Vector4f color, String shaderProgram, Renderer renderer);

  public abstract void draw(Vector3f position, float scale, float angle, Vector4f color, String shaderProgram, Renderer renderer);
}