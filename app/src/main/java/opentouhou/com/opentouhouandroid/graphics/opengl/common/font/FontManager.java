package opentouhou.com.opentouhouandroid.graphics.opengl.common.font;

import java.io.InputStreamReader;
import java.util.HashMap;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;
import opentouhou.com.opentouhouandroid.io.FileManager;

/**
 * Reads .fnt files and loads the font.
 */

public class FontManager
{
    private HashMap<String, Font> fonts;
    private HashMap<String, String> textures;

    // Constructor
    public FontManager()
    {
        fonts = new HashMap<>();
        textures = new HashMap<>();
    }

    // Getters
    public Font getFont(String id)
    {
        return fonts.get(id);
    }

    public String getImageFile(String id)
    {
        return fonts.get(id).getImageFile();
    }

    // Setters
    public void setTextureId(String id, String assetPath)
    {
        textures.put(id, assetPath);
    }

    // Load fonts.
    public void loadFonts(String[] fontList, Renderer renderer, FileManager fileManager) {
        for (String id : fontList) {
            // Create the font.
            load(id, fileManager.openRawAsset(id));

            // Create textures.
            String assetPath = "fonts/images/" + getImageFile(id);
            renderer.getTextureManager().loadAssetBitmap(assetPath, TextureManager.Options.NONE, fileManager);
            setTextureId(id, assetPath);

            // Generate
            fonts.get(id).generate(renderer);
        }
    }

    // Load a font.
    public void load(String resourceId, InputStreamReader reader)
    {
        Font font = new Font(reader);
        fonts.put(resourceId, font);
    }
}