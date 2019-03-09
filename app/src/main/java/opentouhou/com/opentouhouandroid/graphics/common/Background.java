package opentouhou.com.opentouhouandroid.graphics.common;

import opentouhou.com.opentouhouandroid.R;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTexture;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.GraphicsObject30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Quad30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.texture.BackgroundDrawable30;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class Background
{
    private GraphicsObject drawable;

    public Background(Renderer renderer)
    {
        drawable = new BackgroundDrawable30();

        // Set the mesh.
        // x, y, z, r, g, b, a, x, y, z, s, t
        float[] data = {
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
                1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0
        };

        drawable.setMesh(new Quad30(data, renderer.getShaderManager().getShaderProgramHandle("Background")));

        // Set the texture.
        drawable.setTexture(renderer.getTextureManager().getTexture("art/loading_bg1.png"));

        // Set the shader.
        drawable.setShader(renderer.getShaderManager().getShaderProgram("Background"));

        // Set the location.
        AbstractTexture texture = renderer.getTextureManager().getTexture("art/loading_bg1.png");
        int width = texture.getWidth();
        int height = texture.getHeight();
        float scale = 11.5f;
        Matrix4f mat = Matrix4f.scaleMatrix(scale, scale * ((float)height / (float)width), 1);
        mat.translate(0, -4.4f, 0);
        drawable.setModelMatrix(mat);
    }

    public void draw(Scene scene)
    {
        drawable.draw(scene);
    }
}