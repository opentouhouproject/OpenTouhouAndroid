package com.scarlet.graphics.opengl;

import com.scarlet.graphics.opengl.animation.TextAnimation;
import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import com.scarlet.graphics.opengl.font.Font;

/*
 * Represents a text object that can drawn to the screen.
 */
public class Text {
    private Font font;
    private String shaderProgram;

    private String value;

    private Vector3f position;
    private float scaling;
    private float angle;

    private Vector4f drawOffset;

    private Vector4f color;

    private boolean enableAnim;
    private TextAnimation[] animations;
    private TextAnimation animation;
    private int numberOfAnimations;

    /*
     * Constructor(s)
     */
    public Text(Font font) {
        this.font = font;
        shaderProgram = "";

        // Set default values.
        value = "";

        position = new Vector3f(0, 0, 0);
        scaling = 1.0f;
        angle = 0.0f;

        drawOffset = new Vector4f(1f, 0f, 0f, 1f);

        color = new Vector4f(1, 1, 1, 1);

        enableAnim = false;
        animations = new TextAnimation[4];
        animation = null;
        numberOfAnimations = 0;
    }

    /*
     * Setter(s)
     */
    public Text setText(String text) {
        value = text;

        return this;
    }

    public Text setPosition(Vector3f position) {
        this.position.x = position.x;
        this.position.y = position.y;
        this.position.z = position.z;

        return this;
    }

    public Text setScaling(float scaling) {
        this.scaling = scaling;

        return this;
    }

    public Text setRotation(float degree) {
        angle = degree;

        drawOffset = Matrix4f.multiply(Matrix4f.getYAxisRotation(angle, true), new Vector4f(1f, 0f, 0f, 1f));
        drawOffset.normalize();

        return this;
    }

    public Text setColor(Vector4f color) {
        this.color.x = color.x;
        this.color.y = color.y;
        this.color.z = color.z;
        this.color.w = color.w;

        return this;
    }

    public Text setShader(String shader) {
        shaderProgram = shader;

        return this;
    }

    public Text addAnimation(TextAnimation newAnimation) {
        enableAnim = true;

        animations[numberOfAnimations] = newAnimation;

        animation = animations[numberOfAnimations];

        numberOfAnimations++;

        return this;
    }

    public void selectAnimation(int index) {
        animation = animations[index];
    }

    /*
     * Implement update method.
     */
    public void update() {
        if (enableAnim) {
            animation.update();
        }
    }

    // Drawing and rendering methods.
    public void draw(Renderer renderer) {
        char[] chars;

        // Update text.
        if (enableAnim) {
            chars = animation.currentFrame().toCharArray();
        } else {
            chars = value.toCharArray();
        }

        Vector3f drawPosition = new Vector3f(position);

        for (char c : chars) {
            // Draw the glyph.
            font.render(c, drawPosition, scaling, angle, color, shaderProgram, renderer);

            // Update the offset.
            float factor = (float)font.getGlyph(c).getWidth() / scaling;

            // Update the render position of a glyph by the offset.
            drawPosition.selfAdd(new Vector3f(drawOffset.scale(factor)));
        }
    }
}