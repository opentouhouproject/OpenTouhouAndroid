package com.scarlet.graphics.opengl.font;

import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import com.scarlet.graphics.opengl.Renderer;

/**
 * Represents a single UNICODE character.
 */
public abstract class Glyph {
    // UNICODE number.
    protected int id;

    // Top left coordinate.
    protected int x, y;

    // Dimensions.
    protected int width, height;

    // Constructor(s)
    public Glyph(int id, int x, int y, int width, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getter(s)
    public int getUnicodeId() { return id; }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    // Set the drawable.
    public abstract void generate(int texWidth, int texHeight, String assetPath, Renderer renderer);

    // Draw the glyph on the screen.
    public abstract void draw(Vector3f position, float scale, Vector4f color, String shaderProgram, Renderer renderer);

    public abstract void draw(Vector3f position, float scale, float angle, Vector4f color, String shaderProgram, Renderer renderer);
}