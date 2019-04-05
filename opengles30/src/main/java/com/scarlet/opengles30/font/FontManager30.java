package com.scarlet.opengles30.font;

import com.scarlet.graphics.opengl.font.FontManager;

import java.io.InputStreamReader;

public class FontManager30 extends FontManager {
    public FontManager30() { }

    // Load a font.
    protected void load(String fontPath, InputStreamReader reader) {
        fonts.put(fontPath, new Font30(reader));
    }
}