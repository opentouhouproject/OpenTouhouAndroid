package com.scarlet.opengles30.font;

import android.opengl.GLES30;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.math.Vector4f;
import com.scarlet.opengles30.GraphicsObject30;

public class FontDrawable30 extends GraphicsObject30
{
    private Vector4f fontColor;

    public FontDrawable30()
    {
        fontColor = new Vector4f(1.0f, 0.1412f, 0.0f, 1.0f);
    }

    // Setter(s)
    public void setColor(Vector4f color)
    {
        fontColor.x = color.x;
        fontColor.y = color.y;
        fontColor.z = color.z;
        fontColor.w = color.w;
    }

    // Draw
    public void draw(Renderer renderer)
    {
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
}