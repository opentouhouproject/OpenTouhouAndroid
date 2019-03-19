package opentouhou.com.opentouhouandroid.entity.background;

import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.Texture;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.drawable.GraphicsObject30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Mesh30;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class BackgroundDrawable30 extends GraphicsObject30 {
    /*
     * Constructor(s).
     */
    public BackgroundDrawable30(Renderer renderer) {
        setup(renderer);
    }

    /*
     * Setup the drawable object.
     */
    private void setup(Renderer renderer) {
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

        Mesh30 mesh = new Mesh30(data, renderer.getShaderManager().getShaderProgramHandle("Background"), MeshLayout.Layout.PCNT);
        setMesh(mesh);

        // Set the texture.
        setTexture(renderer.getTextureManager().getTexture("art/loading_bg1.png"));

        // Set the shader.
        setShader(renderer.getShaderManager().getShaderProgram("Background"));

        // Set the model.
        Texture texture = renderer.getTextureManager().getTexture("art/loading_bg1.png");
        int width = texture.getWidth();
        int height = texture.getHeight();
        float scale = 11.5f;
        Matrix4f mat = Matrix4f.scaleMatrix(scale, scale * ((float)height / (float)width), 1);
        mat.translate(-5.5f, -10.0f, 0);
        setModelMatrix(mat);
    }

    // Override the parent draw method.
    @Override
    public void draw(Scene scene) {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES30.glUseProgram(shaderHandle);

        // Set color
        int uniformColorHandle = GLES30.glGetUniformLocation(shaderHandle, "uColor");
        GLES30.glUniform4f(uniformColorHandle, 0.9f, 0.6f, 0.3f, 1f);

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