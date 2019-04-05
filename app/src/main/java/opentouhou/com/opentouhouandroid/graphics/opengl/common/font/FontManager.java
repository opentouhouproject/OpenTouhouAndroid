package opentouhou.com.opentouhouandroid.graphics.opengl.common.font;

import com.scarlet.io.FileManager;

import java.io.InputStreamReader;
import java.util.HashMap;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;

/*
 * Reads xml font files and loads the font.
 */

public class FontManager {
    private static int INITIAL_CAPACITY = 16;

    private HashMap<String, Font> fonts;
    private HashMap<String, String> textures;

    /*
     * Constructor(s).
     */
    public FontManager() {
        fonts = new HashMap<>(INITIAL_CAPACITY);
        textures = new HashMap<>(INITIAL_CAPACITY);
    }

    /*
     * Getter(s).
     */
    public Font getFont(String fontName) {
        return fonts.get(fontName);
    }

    public String getImageFile(String fontName) {
        return fonts.get(fontName).getImageFile();
    }

    /*
     * Setter(s).
     */
    public void setTextureId(String fontName, String assetPath) {
        textures.put(fontName, assetPath);
    }

    // Load fonts.
    public void loadFonts(String[] fontPaths, Renderer renderer, FileManager fileManager) {
        for (String fontPath : fontPaths) {
            // Check if already loaded the font.
            if (fonts.containsKey(fontPath)) {
                continue;
            }

            // Create the font.
            load(fontPath, fileManager.openRawAsset(fontPath));

            // Create textures.
            String assetPath = "fonts/images/" + getImageFile(fontPath);
            renderer.getTextureManager().loadAssetBitmap(assetPath, TextureManager.Options.NONE, fileManager);
            setTextureId(fontPath, assetPath);

            // Generate
            fonts.get(fontPath).generate(renderer);
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
        setTextureId(fontPath, assetPath);

        // Generate
        fonts.get(fontPath).generate(renderer);
    }

    // Load a font.
    private void load(String fontPath, InputStreamReader reader) {
        fonts.put(fontPath, new Font(reader));
    }
}