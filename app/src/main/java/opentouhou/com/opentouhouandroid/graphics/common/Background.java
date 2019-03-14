package opentouhou.com.opentouhouandroid.graphics.common;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTexture;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.drawable.BackgroundDrawable20;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.mesh.Mesh20;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Mesh30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.drawable.BackgroundDrawable30;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class Background
{
    private GraphicsObject drawable;

    public Background(Renderer renderer, GraphicsObject.Version version)
    {
        switch (version)
        {
            case OPENGL_ES_20:
                setup20(renderer);
                break;

            case OPENGL_ES_30:
                setup30(renderer);
                break;
        }
    }

    public void draw(Scene scene)
    {
        drawable.draw(scene);
    }

    // Setup code.
    private void setup20(Renderer renderer)
    {
        // Create a new drawable.
        drawable = new BackgroundDrawable20();

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

        Mesh20 mesh = new Mesh20(data);
        drawable.setMesh(mesh);

        // Set the texture.
        drawable.setTexture(renderer.getTextureManager().getTexture("art/loading_bg1.png"));

        // Set the shader.
        drawable.setShader(renderer.getShaderManager().getShaderProgram("Background"));

        // Set the model.
        AbstractTexture texture = renderer.getTextureManager().getTexture("art/loading_bg1.png");
        int width = texture.getWidth();
        int height = texture.getHeight();
        float scale = 11.5f;
        Matrix4f mat = Matrix4f.scaleMatrix(scale, scale * ((float)height / (float)width), 1);
        mat.translate(-5.5f, -10.0f, 0);
        drawable.setModelMatrix(mat);
    }

    private void setup30(Renderer renderer)
    {
        // Create a new drawable.
        drawable = new BackgroundDrawable30();

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
        drawable.setMesh(mesh);

        // Set the texture.
        drawable.setTexture(renderer.getTextureManager().getTexture("art/loading_bg1.png"));

        // Set the shader.
        drawable.setShader(renderer.getShaderManager().getShaderProgram("Background"));

        // Set the model.
        AbstractTexture texture = renderer.getTextureManager().getTexture("art/loading_bg1.png");
        int width = texture.getWidth();
        int height = texture.getHeight();
        float scale = 11.5f;
        Matrix4f mat = Matrix4f.scaleMatrix(scale, scale * ((float)height / (float)width), 1);
        mat.translate(-5.5f, -10.0f, 0);
        drawable.setModelMatrix(mat);
    }
}