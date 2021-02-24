package com.scarlet.opengles30.font;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.font.Glyph;
import com.scarlet.graphics.opengl.shader.ShaderProgram;
import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

/**
 * Represents a single UNICODE character glyph.
 * Implemented with OpenGL ES 3.0.
 */
public class Glyph30 extends Glyph {
  private GlyphDrawable30 drawable;

  public Glyph30(int id, int x, int y, int width, int height) {
      super(id, x, y, width, height);

      drawable = new GlyphDrawable30();
  }

  // Set the drawable.
  public void generate(int texWidth, int texHeight, String assetPath, Renderer renderer) {
    float left = glyphCoordinates.x / (float) texWidth;
    float right = (glyphCoordinates.x + glyphDimensions.x) / (float) texWidth;
    float top = glyphCoordinates.y / (float) texHeight;
    float bottom = (glyphCoordinates.y + glyphDimensions.y) / (float) texHeight;

    // PCNT Format
    float[] data = {
      0, 1, 0, 1, 1, 1, 1, 0, 0, 1, left, top,
      0, 0, 0, 1, 1, 1, 1, 0, 0, 1, left, bottom,
      1, 0, 0, 1, 1, 1, 1, 0, 0, 1, right, bottom,
      0, 1, 0, 1, 1, 1, 1, 0, 0, 1, left, top,
      1, 0, 0, 1, 1, 1, 1, 0, 0, 1, right, bottom,
      1, 1, 0, 1, 1, 1, 1, 0, 0, 1, right, top
    };

    drawable.generate(data, assetPath, renderer);
  }

  // Draw the glyph on the screen.
  public void draw(Vector3f position, float scale, Vector4f color, String shaderProgram, Renderer renderer) {
    // Set the model matrix.
    Matrix4f model = Matrix4f.scaleMatrix(
            glyphDimensions.x / scale,
            glyphDimensions.y / scale,
            1);
    model.translate(position.x, position.y, position.z);
    drawable.setModelMatrix(model);

    // Set the color.
    drawable.setColor(color);

    // Set the shader.
    ShaderProgram p = renderer.getShaderManager().getShaderProgram(shaderProgram);
    drawable.setShader(p);

    // Draw the glyph.
    drawable.render(renderer);
  }

  public void draw(Vector3f position, float scale, float angle, Vector4f color, String shaderProgram, Renderer renderer) {
    // Set the model matrix.
    Matrix4f model = Matrix4f.scaleMatrix(
            glyphDimensions.x / scale,
            glyphDimensions.y / scale,
            1);
    model.rotateY(angle, true);
    model.translate(position.x, position.y, position.z);

    drawable.setModelMatrix(model);

    // Set the color.
    drawable.setColor(color);

    // Set the shader.
    ShaderProgram p = renderer.getShaderManager().getShaderProgram(shaderProgram);
    drawable.setShader(p);

    // Draw the glyph.
    drawable.render(renderer);
  }
}
