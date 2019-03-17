package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.animation.TextAnimation;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.Font;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.scene.Scene;

/**
 * Represents a text object that can drawn to the screen.
 */
public class Text
{
    Font font;

    String value;
    Vector3f position;
    float scaling;
    Vector4f color;
    String shaderProgram;

    boolean enableAnim = false;
    TextAnimation animation;

    // Constructor(s)
    public Text(Font font)
    {
        this.font = font;

        // Set default values.
        value = "";
        position = new Vector3f(0, 0, 0);
        scaling = 1.0f;
        color = new Vector4f(1, 1, 1, 1);
    }

    // Setter(s)
    public Text setText(String text)
    {
        value = text;

        return this;
    }

    public Text setPosition(Vector3f position)
    {
        this.position.x = position.x;
        this.position.y = position.y;
        this.position.z = position.z;

        return this;
    }

    public Text setScaling(float scaling)
    {
        this.scaling = scaling;

        return this;
    }

    public Text setColor(Vector4f color)
    {
        this.color.x = color.x;
        this.color.y = color.y;
        this.color.z = color.z;
        this.color.w = color.w;

        return this;
    }

    public Text setShader(String shader)
    {
        shaderProgram = shader;

        return this;
    }

    public Text setAnimation(TextAnimation animation)
    {
        enableAnim = true;
        this.animation = animation;

        return this;
    }

    /*
     * Implement update method.
     */
    public void update() {
        if (enableAnim == true)
        {

        }
    }

    // Drawing and rendering methods.
    public void draw(Scene scene)
    {
        char[] chars;

        if (enableAnim == true)
        {
            chars = animation.currentFrame().toCharArray();

            animation.nextFrame();
        }
        else
        {
            chars = value.toCharArray();
        }

        float xOffset = 0;
        Vector3f drawPosition = new Vector3f(position);

        for (char c : chars)
        {
            // Update the render position of a glyph by the offset.
            drawPosition.x += xOffset;

            // Draw the glyph.
            font.render(c, drawPosition, scaling, color, shaderProgram, scene);

            // Update the offset.
            xOffset = (float)font.getGlyph(c).getWidth() / scaling;
        }
    }

    public void render(String s, Vector3f position, float scale, String shaderProgram, Scene scene)
    {
        float offsetX = 0;

        for (char c : s.toCharArray())
        {
            position.x += offsetX;
            font.render(c, position, scale, color, shaderProgram, scene);
            offsetX = font.getGlyph(c).getWidth() / scale;
        }
    }
}