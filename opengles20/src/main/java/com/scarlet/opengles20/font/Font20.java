package com.scarlet.opengles20.font;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.font.Font;
import com.scarlet.graphics.opengl.font.Glyph;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import java.io.InputStreamReader;

public class Font20 extends Font {
    /*
     * Constructor(s).
     */
    public Font20(InputStreamReader reader) {
        FontParser20.parse(reader, this);
    }

    // Generates binding between glyphs and textures.
    public void generate(Renderer renderer) {
        for (Glyph g : glyphs.values()) {
            String assetPath = "fonts/images/" + imageFile;
            g.generate(width, height, assetPath, renderer);
        }
    }

    // Handles rendering of a single UNICODE character.
    public void render(char c, Vector3f position, float scaling, float angle, Vector4f color, String shaderProgram, Renderer renderer) {
        glyphs.get(c).draw(position, scaling, angle, color, shaderProgram, renderer);
    }
}
