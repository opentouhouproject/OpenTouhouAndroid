package com.scarlet.opengles30.font;

import android.opengl.GLES30;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.mesh.MeshLayout;
import com.scarlet.graphics.opengl.shader.ShaderProgram;
import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector4f;
import com.scarlet.opengles30.GraphicsObject30;
import com.scarlet.opengles30.mesh.Mesh30;

/**
 * Drawable that renders an individual glyph.
 * Implemented with OpenGL ES 3.0.
 */
public class GlyphDrawable30 extends GraphicsObject30 {
  private Vector4f fontColor = new Vector4f(1.0f, 0.1412f, 0.0f, 1.0f);

  public GlyphDrawable30() {
    super();
  }

  // Setter(s)
  public void setColor(Vector4f color) {
    fontColor.x = color.x;
    fontColor.y = color.y;
    fontColor.z = color.z;
    fontColor.w = color.w;
  }

  @Override
  public void render(Renderer renderer) {
    // Set the shader program to use.
    int shaderHandle = shaderProgram.getHandle();
    GLES30.glUseProgram(shaderHandle);

    // Set the text color.
    int uniformColorHandle = GLES30.glGetUniformLocation(shaderHandle, "uColor");
    GLES30.glUniform4f(uniformColorHandle, fontColor.x, fontColor.y, fontColor.z, fontColor.w);

    // Set the transformation matrices.
    setTransformationMatrices(shaderHandle, renderer.getCamera());

    // Set the light source(s).
    setLightPosition(shaderHandle, renderer.getCamera(), renderer.getLight());

    // Set the texture.
    setTexture(shaderHandle);

    // Set the mesh.
    setMesh();

    // Draw the object.
    GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 6);
  }

  /**
   * Sets the initial data for the drawable.
   */
  public void generate(float[] meshData, String assetPath, Renderer renderer) {
    // Get the font shader.
    ShaderProgram program = renderer.getShaderManager().getShaderProgram("Font");

    // Set the mesh.
    Mesh30 mesh = new Mesh30(meshData, MeshLayout.Layout.PCNT);
    mesh.createVAO(program.getHandle());
    setMesh(mesh);

    // Set the texture.
    setTexture(renderer.getTextureManager().getTexture(assetPath));

    // Set the shader.
    setShader(program);

    // Set the model.
    setModelMatrix(Matrix4f.identityMatrix());
  }
}