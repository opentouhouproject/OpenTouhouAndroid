package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.drawable;

import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.scene.Scene;

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
    public void draw(Scene scene)
    {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES30.glUseProgram(shaderHandle);

        // Set the text color.
        int uniformColorHandle = GLES30.glGetUniformLocation(shaderHandle, "uColor");
        GLES30.glUniform4f(uniformColorHandle, fontColor.x, fontColor.y, fontColor.z, fontColor.w);

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