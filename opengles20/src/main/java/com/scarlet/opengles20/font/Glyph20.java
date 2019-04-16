package com.scarlet.opengles20.font;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.font.Glyph;
import com.scarlet.graphics.opengl.shader.ShaderProgram;
import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;
import com.scarlet.opengles20.mesh.Mesh20;

public class Glyph20 extends Glyph {
    // Drawing.
    private FontDrawable20 drawable;

    // Constructor(s)
    public Glyph20(int id, int x, int y, int width, int height) {
        super(id, x, y, width, height);

        // Initialise objects.
        drawable = new FontDrawable20();
    }

    // Set the drawable.
    public void generate(int texWidth, int texHeight, String assetPath, Renderer renderer) {
        float left = (float) x / (float) texWidth;
        float right = (float) (x + width) / (float) texWidth;
        float top = (float) y / (float) texHeight;
        float bottom = (float) (y + height) / (float) texHeight;

        float[] data = {
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, left, top,
                0, 0, 0, 1, 1, 1, 1, 0, 0, 1, left, bottom,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, right, bottom,
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, left, top,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, right, bottom,
                1, 1, 0, 1, 1, 1, 1, 0, 0, 1, right, top
        };

        ShaderProgram program = renderer.getShaderManager().getShaderProgram("Font");

        // Set the mesh.
        Mesh20 mesh = new Mesh20(data);
        drawable.setMesh(mesh);

        // Set the texture.
        drawable.setTexture(renderer.getTextureManager().getTexture(assetPath));

        // Set the shader.
        drawable.setShader(program);

        // Set the model.
        drawable.setModelMatrix(Matrix4f.getIdentity());
    }

    // Draw the glyph on the screen.
    public void draw(Vector3f position, float scale, Vector4f color, String shaderProgram, Renderer renderer)
    {
        // Set the model matrix.
        Matrix4f model = Matrix4f.scaleMatrix(width / scale, height / scale, 1);
        model.translate(position.x, position.y, position.z);
        drawable.setModelMatrix(model);

        // Set the color.
        drawable.setColor(color);

        // Set the shader.
        ShaderProgram p = renderer.getShaderManager().getShaderProgram(shaderProgram);
        drawable.setShader(p);

        // Draw the glyph.
        drawable.draw(renderer);
    }

    public void draw(Vector3f position, float scale, float angle, Vector4f color, String shaderProgram, Renderer renderer)
    {
        // Set the model matrix.
        Matrix4f model = Matrix4f.scaleMatrix(width / scale, height / scale, 1);
        model.rotateY(angle, true);
        model.translate(position.x, position.y, position.z);

        //Vector4f forward = Matrix4f.multiply(Matrix4f.getYAxisRotation(angle, true), new Vector4f(0, 0, 1, 1));
        //forward.selfScale(-0.5f);
        //model.translate(forward.x, forward.y, forward.z);
        drawable.setModelMatrix(model);

        // Set the color.
        drawable.setColor(color);

        // Set the shader.
        ShaderProgram p = renderer.getShaderManager().getShaderProgram(shaderProgram);
        drawable.setShader(p);

        // Draw the glyph.
        drawable.draw(renderer);
    }
}