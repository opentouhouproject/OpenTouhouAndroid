package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.Font;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class Text extends AbstractText
{
    Font font;

    String value;

    // Constructor
    public Text(String value, Font font)
    {
        this.value = value;
        this.font = font;
    }

    public void render(String s, Vector3f position, float scale, Scene scene)
    {
        float offsetX = 0;

        for (char c : s.toCharArray())
        {
            position.x += offsetX;
            font.draw(c, position, scale, scene);
            offsetX = font.getGlyph(c).getWidth() / scale;
        }
    }
}