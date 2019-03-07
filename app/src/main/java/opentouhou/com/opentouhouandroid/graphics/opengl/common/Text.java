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

    public void render(String s, Vector3f position, Scene scene)
    {
        float offsetX = 0;

        for (char c : s.toCharArray())
        {
            position.x += offsetX;
            draw(c, position, scene);
            offsetX = font.getGlyph(c).getWidth()/40f;
        }
    }

    private void draw(char c, Vector3f position, Scene scene)
    {
        font.draw(c, position, scene);
    }
}