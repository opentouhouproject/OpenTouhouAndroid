package com.scarlet.graphics.opengl.font;

import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import java.util.HashMap;

import com.scarlet.graphics.opengl.Renderer;

/**
 * Represents a bitmap font with a corresponding xml metadata file.
 */
public abstract class Font {
    // Font info.
    protected String face, imageFile;
    protected int size, width, height;
    protected boolean italic, bold, unicode;

    // Glyph mapping.
    protected HashMap<Character, Glyph> glyphs;

    /*
     * Constructor(s).
     */
    public Font() {
        glyphs = new HashMap<>();
    }

    /*
     * Getter(s).
     */
    public String getFace() { return face; }

    public String getImageFile() { return imageFile; }

    public int getSize() { return size; }

    public boolean isBold() { return bold; }

    public boolean isItalic() { return italic; }

    public boolean isUnicode() { return unicode; }

    public Glyph getGlyph(char c) { return glyphs.get(c); }

    /*
     * Setter(s).
     */
    public void setFace(String input) { face = input; }

    public void setImageFile(String input) { imageFile = input; }

    public void setSize(int input) { size = input; }

    public void setWidth(int input) { width = input; }

    public void setHeight(int input) { height = input; }

    public void setBold(boolean input) { bold = input; }

    public void setItalic(boolean input) { italic = input; }

    public void setUnicode(boolean input) { unicode = input; }

    public void putGlyph(char key, Glyph value) { glyphs.put(key, value); }

    // Generates binding between glyphs and textures.
    public abstract void generate(Renderer renderer);

    // Handles rendering of a single UNICODE character.
    public abstract void render(char c, Vector3f position, float scaling, float angle, Vector4f color, String shaderProgram, Renderer renderer);
}