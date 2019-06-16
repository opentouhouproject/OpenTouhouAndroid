package com.scarlet.opengles30;

import android.opengl.GLES30;

import java.nio.IntBuffer;

import com.scarlet.graphics.opengl.VertexArrayObject;
import com.scarlet.graphics.opengl.mesh.MeshLayout;

/**
 * Implements a vertex array object in OpenGLES 3.0 .
 */
public class VertexArrayObject30 extends VertexArrayObject {
  public VertexArrayObject30(int buffer, int shader, MeshLayout.Layout layout) {
    // Bind the vertex buffer object
    GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, buffer);

    // Create a VAO.
    handles = IntBuffer.allocate(1);
    GLES30.glGenVertexArrays(1, handles);

    // Bind the VAO.
    GLES30.glBindVertexArray(handles.get(0));

    switch (layout) {
      case PCNT:
        initialisePCNT(shader);
        break;

      case PCN:
        initialisePCN(shader);
        break;

      case P2T:
        initialiseP2T(shader);
        break;
    }

    // Unbind the VAO.
    GLES30.glBindVertexArray(0);
  }

  /**
   * Set the VAO for a PCNT layout.
   *
   * @param shader The shader handle.
   */
  private void initialisePCNT(int shader) {
    setPosition(shader, MeshLayout.PCNT_Stride, MeshLayout.PCNT_POSITION_OFFSET);
    setColor(shader, MeshLayout.PCNT_Stride, MeshLayout.PCNT_COLOR_OFFSET);
    setNormal(shader, MeshLayout.PCNT_Stride, MeshLayout.PCNT_NORMAL_OFFSET);
    setTextureCoordinate(shader, MeshLayout.PCNT_Stride, MeshLayout.PCNT_TEXTURE_COORDINATE_OFFSET);
  }

  /**
   * Set the VAO for a PCN layout.
   *
   * @param shader The shader handle.
   */
  private void initialisePCN(int shader) {
    setPosition(shader, MeshLayout.PCN_Stride, MeshLayout.PCN_POSITION_OFFSET);
    setColor(shader, MeshLayout.PCN_Stride, MeshLayout.PCN_COLOR_OFFSET);
    setNormal(shader, MeshLayout.PCN_Stride, MeshLayout.PCN_NORMAL_OFFSET);
  }

  /**
   * Set the VAO for a P2T layout.
   *
   * @param shader The shader handle.
   */
  private void initialiseP2T(int shader) {
    setPosition(shader, MeshLayout.P2T_Stride, MeshLayout.P2T_POSITION_OFFSET);
    setTextureCoordinate(shader, MeshLayout.P2T_Stride, MeshLayout.P2T_TEXTURE_COORDINATE_OFFSET);
  }

  /**
   * Set the position attribute.
   *
   * @param shader The shader handle.
   * @param stride The number of bytes to the start of the next position attribute.
   * @param offset The number of bytes to offset from index 0.
   */
  private void setPosition(int shader, int stride, int offset) {
    int handle = GLES30.glGetAttribLocation(shader, "aVertex");
    GLES30.glEnableVertexAttribArray(handle);
    GLES30.glVertexAttribPointer(handle, MeshLayout.POSITION_DATA_SIZE, GLES30.GL_FLOAT, false, stride, offset);
  }

  /**
   * Set the color attribute.
   *
   * @param shader The shader handle.
   * @param stride The number of bytes to the start of the next color attribute.
   * @param offset The number of bytes to offset from index 0.
   */
  private void setColor(int shader, int stride, int offset) {
    int handle = GLES30.glGetAttribLocation(shader, "aColor");
    GLES30.glEnableVertexAttribArray(handle);
    GLES30.glVertexAttribPointer(handle, MeshLayout.COLOR_DATA_SIZE, GLES30.GL_FLOAT, false, stride, offset);
  }

  /**
   * Set the normal attribute.
   *
   * @param shader The shader handle.
   * @param stride The number of bytes to the start of the next normal attribute.
   * @param offset The number of bytes to offset from index 0.
   */
  private void setNormal(int shader, int stride, int offset) {
    int handle = GLES30.glGetAttribLocation(shader, "aNormal");
    GLES30.glEnableVertexAttribArray(handle);
    GLES30.glVertexAttribPointer(handle, MeshLayout.NORMAL_DATA_SIZE, GLES30.GL_FLOAT, false, stride, offset);
  }

  /**
   * Set the texture coordinate attribute.
   *
   * @param shader The shader handle.
   * @param stride The number of bytes to the start of the next texture coordinate attribute.
   * @param offset The number of bytes to offset from index 0.
   */
  private void setTextureCoordinate(int shader, int stride, int offset) {
    int handle = GLES30.glGetAttribLocation(shader, "aTexCoordinate");
    GLES30.glEnableVertexAttribArray(handle);
    GLES30.glVertexAttribPointer(handle, MeshLayout.TEXTURE_COORDINATE_DATA_SIZE, GLES30.GL_FLOAT, false, stride, offset);
  }
}