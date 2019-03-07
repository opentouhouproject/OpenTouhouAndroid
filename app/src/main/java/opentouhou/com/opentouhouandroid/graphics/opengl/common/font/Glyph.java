package opentouhou.com.opentouhouandroid.graphics.opengl.common.font;

import opentouhou.com.opentouhouandroid.R;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.GraphicsObject30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Quad30;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.scene.Scene;

/**
 * Represents a single character.
 */

public class Glyph
{
    // UNICODE number.
    private int id;

    // Top Left coordinate.
    private int x, y;

    // dimensions.
    private int width, height;

    // Mesh
    private GraphicsObject drawable;

    public Glyph(int id, int x, int y, int width, int height)
    {
        // Initialise information
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getters
    public int getCharId() { return id; }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    // Draw
    public void generate(int texWidth, int texHeight, Renderer renderer)
    {
        float left = (float)x / (float)texWidth;
        float right = (float)(x + width) / (float)texWidth;
        float top = (float)y / (float)texHeight;
        float bottom = (float)(y + height) / (float)texHeight;

        // Generate drawable object.

        float[] data = {
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, left, top,
                0, 0, 0, 1, 1, 1, 1, 0, 0, 1, left, bottom,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, right, bottom,
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, left, top,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, right, bottom,
                1, 1, 0, 1, 1, 1, 1, 0, 0, 1, right, top
        };
        /*
        float[] data = {
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0.0f, 0.0f,
                0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0.0f, bottom,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, right, bottom,
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0.0f, 0.0f,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, right, bottom,
                1, 1, 0, 1, 1, 1, 1, 0, 0, 1, right, 0.0f
        };*/

        drawable = new GraphicsObject30();
        drawable.setMesh(new Quad30(data, renderer.getShaderManager().getShaderProgramHandle("TextureShader")));
        drawable.setTexture(renderer.getTextureManager().getTexture(R.drawable.popstar16_0));
        drawable.setShader(renderer.getShaderManager().getShaderProgram("TextureShader"));
        drawable.setModelMatrix(Matrix4f.scaleMatrix(4, 4, 1));
    }

    public void draw(Vector3f point, Scene scene)
    {
        Matrix4f mat = Matrix4f.scaleMatrix(width/40f, height/40f, 1);
        mat.translate(point.x, point.y, point.z);
        drawable.setModelMatrix(mat);
        drawable.draw(scene);
    }
}