package opentouhou.com.opentouhouandroid.graphics.opengl.common.font;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.scene.Scene;

/**
 * Represents a bitmap font from a .fnt file.
 */

public class Font
{
    // Font info
    String face, imageFile;
    int size, width, height;
    boolean italic, bold, unicode;

    // Glyphs
    private HashMap<Character, Glyph> glyphs;

    public Font(InputStreamReader reader)
    {
        glyphs = new HashMap<>();

        open(reader);
    }

    // Getters
    public String getFace() { return face; }

    public int getSize() { return size; }

    public boolean isBold() { return bold; }

    public boolean isItalic() { return italic; }

    public boolean isUnicode() { return unicode; }

    public String getImageFile() { return imageFile; }

    public Glyph getGlyph(char c) { return glyphs.get(c); }

    // Open font file.
    private void open(InputStreamReader reader)
    {
        try
        {

            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(reader);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if (eventType == XmlPullParser.START_TAG)
                {
                    String name = parser.getName();

                    if (name.equals("info"))
                    {
                        face = parser.getAttributeValue(null, "face");
                        size = Integer.parseInt(parser.getAttributeValue(null, "size"));
                        bold = 0 != Integer.parseInt(parser.getAttributeValue(null, "bold"));
                        italic = 0 != Integer.parseInt(parser.getAttributeValue(null, "italic"));
                        unicode = 0 != Integer.parseInt(parser.getAttributeValue(null, "unicode"));
                    }
                    else if (name.equals("common"))
                    {
                        width = Integer.parseInt(parser.getAttributeValue(null, "scaleW"));
                        height = Integer.parseInt(parser.getAttributeValue(null, "scaleH"));
                    }
                    else if (name.equals("page"))
                    {
                        imageFile = parser.getAttributeValue(null, "file");
                    }
                    else if (name.equals("char"))
                    {
                        int id = Integer.parseInt(parser.getAttributeValue(null, "id"));
                        int x = Integer.parseInt(parser.getAttributeValue(null, "x"));
                        int y = Integer.parseInt(parser.getAttributeValue(null, "y"));
                        int width = Integer.parseInt(parser.getAttributeValue(null, "width"));
                        int height = Integer.parseInt(parser.getAttributeValue(null, "height"));

                        glyphs.put(Character.toChars(id)[0], new Glyph(id, x, y, width, height));
                    }
                }

                eventType = parser.next();
            }
        }
        catch (XmlPullParserException e)
        {
            throw new RuntimeException("Failed to parse font file. " + e.getMessage(), e);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to parse font file. " + e.getMessage());
        }
        finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                throw new RuntimeException("Could not close stream.");
            }
        }
    }

    public void generate(Renderer renderer)
    {
        for (Glyph g : glyphs.values())
        {
            g.generate(width, height, renderer);
        }
    }

    // Draw
    public void draw(char c, Vector3f point, Scene scene)
    {
        glyphs.get(c).draw(point, scene);
    }
}