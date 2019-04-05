package com.scarlet.opengles20.font;

import com.scarlet.graphics.opengl.font.FontManager;

import java.io.InputStreamReader;

public class FontManager20 extends FontManager {
    public FontManager20() { }

    // Load a font.
    protected void load(String fontPath, InputStreamReader reader) {
        fonts.put(fontPath, new Font20(reader));
    }
}