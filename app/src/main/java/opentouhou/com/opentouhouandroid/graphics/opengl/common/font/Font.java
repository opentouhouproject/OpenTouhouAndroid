package opentouhou.com.opentouhouandroid.graphics.opengl.common.font;

import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import java.io.InputStreamReader;
import java.util.HashMap;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.io.xml.FontParser;
import opentouhou.com.opentouhouandroid.scene.Scene;

/**
 * Represents a bitmap font with a corresponding xml metadata file.
 */
public class Font {
    // Font info.
    private String face, imageFile;
    private int size, width, height;
    private boolean italic, bold, unicode;

    // Glyph mapping.
    private HashMap<Character, Glyph> glyphs;

    /*
     * Constructor(s).
     */
    public Font(InputStreamReader reader) {
        glyphs = new HashMap<>();

        FontParser.parse(reader, this);
        //open(reader);
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
    public void generate(Renderer renderer) {
        for (Glyph g : glyphs.values()) {
            String assetPath = "fonts/images/" + imageFile;
            g.generate(width, height, assetPath, renderer);
        }
    }

    // Handles rendering of a single UNICODE character.
    public void render(char c, Vector3f position, float scaling, Vector4f color, String shaderProgram, Scene scene) {
        glyphs.get(c).draw(position, scaling, color, shaderProgram, scene);
    }
}