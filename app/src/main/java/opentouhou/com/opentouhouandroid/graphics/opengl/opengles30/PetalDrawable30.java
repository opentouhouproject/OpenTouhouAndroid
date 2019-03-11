package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30;

import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsOptions;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class PetalDrawable30 extends GraphicsObject30
{
    public PetalDrawable30(GraphicsOptions options)
    {
        super(options);
    }

    // Draw
    public void draw(Scene scene)
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

        // Set the mesh.
        setMesh();

        // Draw the object.
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, mesh.getVertexCount());
    }
}
