package opentouhou.com.opentouhouandroid.graphics.opengl.common.font;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;

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
    public void loadFonts(String[] fontList, Renderer renderer)
    {
        for (String id : fontList)
        {
            // load the font metadata.
            InputStreamReader in;

            try
            {
                InputStream raw = renderer.getContext().getAssets().open(id);
                in = new InputStreamReader(raw);
            }
            catch (IOException e)
            {
                throw new RuntimeException();
            }

            load(id, in);


            // Create textures.
            String assetPath = "fonts/images/" + getImageFile(id);

            //String resourceName = fileName.substring(0, fileName.lastIndexOf('.'));
            //String type = "drawable";
            //String packageName = "teamdroid.com.speedtestarena";
            //int imageId = renderer.getContext().getResources().getIdentifier(resourceName, type, packageName);

            renderer.getTextureManager().loadAssetBitmap(assetPath, TextureManager.Options.NONE, renderer);
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