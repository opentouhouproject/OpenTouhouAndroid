package com.scarlet.opengles20.font;

import android.opengl.GLES20;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.math.Vector4f;
import com.scarlet.opengles20.GraphicsObject20;

public class FontDrawable20 extends GraphicsObject20 {
    private Vector4f fontColor;

    public FontDrawable20()
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
        GLES20.glUseProgram(shaderHandle);

        // Set the text color.
        int uniformColorHandle = GLES20.glGetUniformLocation(shaderHandle, "uColor");
        GLES20.glUniform4f(uniformColorHandle, fontColor.x, fontColor.y, fontColor.z, fontColor.w);

        // Set the transformation matrices.
        setTransformationMatrices(shaderHandle, renderer.getCamera());

        // Set the light source(s).
        setLightPosition(shaderHandle, renderer.getCamera(), renderer.getLight());

        // Set the texture.
        setTexture(shaderHandle);

        // Draw the object.
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
    }
}
