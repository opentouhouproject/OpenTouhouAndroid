package com.scarlet.graphics.opengl;

import com.scarlet.math.Matrix4f;

import com.scarlet.graphics.opengl.shader.ShaderProgram;
import com.scarlet.graphics.opengl.texture.Texture;
import com.scarlet.graphics.opengl.mesh.Mesh;

/**
 * Basic building block for a drawable object.
 */
public abstract class GraphicsObject implements Renderable {
  protected GraphicsOptions options;
  protected Version version;

  protected Mesh mesh = null;
  protected Texture texture = null;
  protected ShaderProgram shaderProgram = null;
  protected Matrix4f modelMatrix = null;

  public GraphicsObject() {
    options = new GraphicsOptions(true, true);
    version = Version.UNKNOWN;
  }

  public GraphicsObject(GraphicsOptions options, Version version) {
    this.options = options;
    this.version = version;
  }

  // Setter(s).
  public void setMesh(Mesh mesh) { this.mesh = mesh; }

  public void setTexture(Texture texture) { this.texture = texture; }

  public void setShader(ShaderProgram program) { shaderProgram = program; }

  public void setModelMatrix(Matrix4f modelMatrix) { this.modelMatrix = modelMatrix; }
}