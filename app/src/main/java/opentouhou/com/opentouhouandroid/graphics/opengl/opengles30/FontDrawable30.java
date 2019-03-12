package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30;

import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.scene.Scene;

public class FontDrawable30 extends GraphicsObject30
{
    // Draw
    public void draw(Scene scene)
    {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES30.glUseProgram(shaderHandle);

        // Set color
        int uniformColorHandle = GLES30.glGetUniformLocation(shaderHandle, "uColor");
        GLES30.glUniform4f(uniformColorHandle, 1f, 0.1412f, 0f, 1f);
        //GLES30.glUniform4f(uniformColorHandle, 0.65f, 0.062f, 0.11f, 1f);

        // Set the transformation matrices.
        setTransformationMatrices(shaderHandle, scene);

        // Set the light source(s).
        setLightPosition(shaderHandle, scene);

        // Set the texture.
        setTexture(shaderHandle);

        // Set the mesh.
        setMesh();

        // Draw the object.
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 6);
    }
}