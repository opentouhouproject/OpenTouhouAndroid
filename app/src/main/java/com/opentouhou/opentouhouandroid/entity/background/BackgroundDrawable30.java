package com.opentouhou.opentouhouandroid.entity.background;

import android.opengl.GLES30;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.mesh.MeshLayout;
import com.scarlet.graphics.opengl.shader.ShaderProgram;
import com.scarlet.graphics.opengl.texture.Texture;
import com.scarlet.loader.CreateVAOTask;
import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.opengles30.GraphicsObject30;
import com.scarlet.opengles30.mesh.Mesh30;

/**
 * A screen background implemented in OpenGLES 3.0.
 */
public class BackgroundDrawable30 extends GraphicsObject30 {
  public Vector3f position = new Vector3f(-5.5f, -10.0f, 0);

  /**
   * Constructor.
   * @param renderer The renderer to draw with.
   * @param async A flag to determine whether to use asynchronous loading.
   */
  public BackgroundDrawable30(Renderer renderer, boolean async) {
    setup(renderer, async);
  }

  /**
   * Setup the background object.
   * @param renderer The renderer to draw with.
   * @param async Flag to indicate asynchronous loading.
   */
  private void setup(Renderer renderer, boolean async) {
    // Get items.
    ShaderProgram program = renderer.getShaderManager().getShaderProgram("Background");
    Texture texture = renderer.getTextureManager().getTexture("art/loading_bg1.png");

    // Set the mesh.
    // (x, y, z), (r, g, b, a), (x, y, z), (s, t)
    float[] data = {
            0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
            0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1,
            1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
            0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
            1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
            1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0
    };

    Mesh30 mesh = new Mesh30(data, MeshLayout.Layout.PCNT);
    if (async) {
      CreateVAOTask task = new CreateVAOTask(mesh, program.getHandle());
      renderer.queue(task.getRunnable());
    }
    else {
      mesh.createVAO(program.getHandle());
    }

    setMesh(mesh);

    // Set the texture.
    setTexture(texture);

    // Set the shader.
    setShader(program);

    // Set the model.
    int width = texture.getWidth();
    int height = texture.getHeight();
    float scale = 11.5f;
    Matrix4f mat = Matrix4f.scaleMatrix(scale, scale * ((float)height / (float)width), 1);
    mat.translate(position.x, position.y, position.z);
    setModelMatrix(mat);
  }

  /**
   * Override the superclass render method.
   * @param renderer The renderer to use.
   */
  public void render(Renderer renderer) {
    // Set the shader program to use.
    int shaderHandle = shaderProgram.getHandle();
    GLES30.glUseProgram(shaderHandle);

    // Set color
    int uniformColorHandle = GLES30.glGetUniformLocation(shaderHandle, "uColor");
    GLES30.glUniform4f(uniformColorHandle, 0.9f, 0.6f, 0.3f, 1f);

    // Set the transformation matrices.
    setTransformationMatrices(shaderHandle, renderer.getCamera());

    // Set the light source(s).
    setLightPosition(shaderHandle, renderer.getCamera(), renderer.getLight());

    // Set the texture.
    setTexture(shaderHandle);

    // Set the model.
    int width = texture.getWidth();
    int height = texture.getHeight();
    float scale = 11.5f;
    Matrix4f mat = Matrix4f.scaleMatrix(scale, scale * ((float)height / (float)width), 1);
    mat.translate(position.x, position.y, position.z);
    setModelMatrix(mat);

    // Set the mesh.
    setMesh();

    // Draw the object.
    GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 6);
  }
}