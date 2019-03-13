package opentouhou.com.opentouhouandroid.graphics.opengl.common.font;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderProgram;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.drawable.FontDrawable30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Mesh30;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.scene.Scene;

/**
 * Represents a single UNICODE character.
 */
public class Glyph
{
    // UNICODE number.
    private int id;

    // Top left coordinate.
    private int x, y;

    // Dimensions.
    private int width, height;

    // Drawing.
    private FontDrawable30 drawable;

    // Constructor(s)
    public Glyph(int id, int x, int y, int width, int height)
    {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        // Initialise objects.
        drawable = new FontDrawable30();
    }

    // Getter(s)
    public int getUnicodeId() { return id; }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    // Set the drawable.
    public void generate(int texWidth, int texHeight, String assetPath, Renderer renderer)
    {
        float left = (float)x / (float)texWidth;
        float right = (float)(x + width) / (float)texWidth;
        float top = (float)y / (float)texHeight;
        float bottom = (float)(y + height) / (float)texHeight;

        float[] data = {
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, left, top,
                0, 0, 0, 1, 1, 1, 1, 0, 0, 1, left, bottom,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, right, bottom,
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, left, top,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, right, bottom,
                1, 1, 0, 1, 1, 1, 1, 0, 0, 1, right, top
        };

        // Set the mesh.
        Mesh30 mesh = new Mesh30(data, renderer.getShaderManager().getShaderProgramHandle("Font"), MeshLayout.Layout.PCNT);
        drawable.setMesh(mesh);

        // Set the texture.
        drawable.setTexture(renderer.getTextureManager().getTexture(assetPath));

        // Set the shader.
        drawable.setShader(renderer.getShaderManager().getShaderProgram("Font"));

        // Set the model.
        drawable.setModelMatrix(Matrix4f.getIdentity());
    }

    // Draw the glyph on the screen.
    public void draw(Vector3f position, float scale, Vector4f color, String shaderProgram, Scene scene)
    {
        // Set the model matrix.
        Matrix4f model = Matrix4f.scaleMatrix(width / scale, height / scale, 1);
        model.translate(position.x, position.y, position.z);
        drawable.setModelMatrix(model);

        // Set the color.
        drawable.setColor(color);

        // Set the shader.
        ShaderProgram p = scene.renderer.getShaderManager().getShaderProgram(shaderProgram);
        drawable.setShader(p);

        // Draw the glyph.
        drawable.draw(scene);
    }
}