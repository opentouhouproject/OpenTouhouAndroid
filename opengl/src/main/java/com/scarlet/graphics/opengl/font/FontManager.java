package com.scarlet.graphics.opengl.font;

import com.scarlet.io.FileManager;

import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.texture.TextureManager;

/*
 * Reads xml font files and loads the font.
 */
public abstract class FontManager {
  private static int INITIAL_CAPACITY = 16;

  protected ConcurrentHashMap<String, Font> fonts;

  /*
   * Constructor(s).
   */
  protected FontManager() {
    fonts = new ConcurrentHashMap<>(INITIAL_CAPACITY);
  }

  /*
   * Getter(s).
   */
  public Font getFont(String fontName) {
    return fonts.get(fontName);
  }

  public String getImageFile(String fontName) {
    Font font = fonts.get(fontName);

    if (font == null) {
      throw new RuntimeException("Font not found.");
    }

    return font.getImageFile();
  }

  // Load fonts.
  public void loadFonts(String[] fontPaths, Renderer renderer, FileManager fileManager) {
    for (String fontPath : fontPaths) {
      loadFont(fontPath, renderer, fileManager);
    }
  }

  public void loadFont(String fontPath, Renderer renderer, FileManager fileManager) {
    // Check if already loaded the font.
    if (fonts.containsKey(fontPath)) {
      return;
    }

    // Create the font.
    load(fontPath, fileManager.openRawAsset(fontPath));

    // Create textures.
    String assetPath = "fonts/images/" + getImageFile(fontPath);
    renderer.getTextureManager().loadAssetBitmap(assetPath, TextureManager.Options.NONE, fileManager);

    // Generate
    Font font = fonts.get(fontPath);

    if (font == null) {
      throw new RuntimeException("Null Font returned when loading.");
    }

    font.generate(renderer);
  }

  // Load a font.
  protected abstract void load(String fontPath, InputStreamReader reader);
}