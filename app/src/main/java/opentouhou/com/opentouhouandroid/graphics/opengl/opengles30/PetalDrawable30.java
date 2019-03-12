package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30;

import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.actor.Petal;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsOptions;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class PetalDrawable30 extends GraphicsObject30
{
    public PetalDrawable30(GraphicsOptions options)
    {
        super(options);
    }

    // Draw
    public void draw(Scene scene, Petal petal)
    {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES30.glUseProgram(shaderHandle);

        // Set the transformation matrices.
        setTransformationMatrices(shaderHandle, scene);

        // Set the light source(s).
        if (option.lightingSetting()) setLightPosition(shaderHandle, scene);

        // Set the texture.
        if (option.textureSetting()) setTexture(shaderHandle);

        // Set the lifetime.
        int progressHandle = GLES30.glGetUniformLocation(shaderHandle, "uProgress");
        GLES30.glUniform1f(progressHandle, petal.progress());

        // Set the mesh.
        setMesh();

        // Draw the object.
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, mesh.getVertexCount());
    }
}
