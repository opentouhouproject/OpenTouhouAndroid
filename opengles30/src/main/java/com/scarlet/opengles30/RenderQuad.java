package com.scarlet.opengles30;

import android.opengl.GLES30;

import com.scarlet.math.Matrix4f;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.mesh.MeshLayout;
import com.scarlet.graphics.opengl.shader.ShaderProgram;
import com.scarlet.graphics.opengl.texture.Texture;
import com.scarlet.opengles30.mesh.Mesh30;

/**
 * A quad used to draw the post-processed frame buffer.
 */
public class RenderQuad extends GraphicsObject30 {
  private float minX;
  private float maxX;

  private float minY;
  private float maxY;

  public RenderQuad(Renderer renderer, float width, float height, Texture texture) {
    minY = -10f;
    maxY = 10f;

    minX = (20f * (width / height)) / -2;
    maxX = (20f * (width / height)) / 2;

    setup(renderer, width, height, texture);
  }

  private void setup(Renderer renderer, float width, float height, Texture texture) {
    // Get items.
    ShaderProgram program = renderer.getShaderManager().getShaderProgram("RenderQuad");

    // Set the mesh.
    // (x, y), (s, t)
    float[] data = {
      minX, maxY, 0, 1,
      minX, minY, 0, 0,
      maxX, minY, 1, 0,
      maxX, minY, 1, 0,
      maxX, maxY, 1, 1,
      minX, maxY, 0, 1
    };

    Mesh30 mesh = new Mesh30(data, MeshLayout.Layout.P2T);
    mesh.createVAO(program.getHandle());

    setMesh(mesh);

    // Set the texture.
    super.setTexture(texture);

    // Set the shader.
    super.setShader(program);

    // Set the model.
    setModelMatrix(Matrix4f.identityMatrix());
  }

  public void setTexture(Texture texture) { super.setTexture(texture); }

  public void setShader(ShaderProgram program) { super.setShader(program); }

  public void useShader() {
    // Set the shader program to use.
    int shaderHandle = shaderProgram.getHandle();
    GLES30.glUseProgram(shaderHandle);
  }

  public void setupTexture(Texture texture, int texUnit, String texName) {
    int handle = shaderProgram.getHandle();

    int textureHandle = GLES30.glGetUniformLocation(handle, texName);

    // Set the active texture unit to texture unit 0.
    GLES30.glActiveTexture(texUnit);
    // Bind the texture to this unit.
    GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture.getTextureHandle());

    // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit.
    if (texUnit == GLES30.GL_TEXTURE0) {
      GLES30.glUniform1i(textureHandle, 0);
    } else if (texUnit == GLES30.GL_TEXTURE1) {
      GLES30.glUniform1i(textureHandle, 1);
    }
  }

  public void draw(Renderer renderer) {
    int shaderHandle = shaderProgram.getHandle();

    // Set the transformation matrices.
    setTransformationMatrices(shaderHandle, renderer.getCamera());

    // Set the mesh.
    setMesh();

    // Draw the object.
    GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 6);
  }
}