package com.scarlet.ui.difficultymenu;

import android.util.Log;
import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.Text;
import com.scarlet.graphics.opengl.animation.UIAnimation;
import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;
import com.scarlet.ui.UIEntity;
import com.scarlet.ui.button.LeftRoundedButtonDrawable30;

/*
 * Internal menu item class.
 */
class MenuItem extends UIEntity {
    private String value = "";

    // Center of mass position of Button in 3d space.
    private Vector3f position;
    // If the button is rotated then we have a normal and edge.
    private Vector3f normal;
    private Vector3f edge;

    // Width and height of button.
    private float width;
    private float height;

    // Y-axis rotation.
    private float angle;

    // Drawables.
    private LeftRoundedButtonDrawable30 drawable;
    private Text labelText;

    /*
     * Constructor(s).
     */
    public MenuItem(Renderer renderer, boolean async) {
        position = new Vector3f(0f, 0f, 0f);
        normal = new Vector3f(0f, 0f, 1f);
        edge = new Vector3f(1f, 0f, 0f);

        width = 6.0f;
        height = 1.0f;

        angle = 0f;

        // Create the button drawable.
        drawable = new LeftRoundedButtonDrawable30();
        drawable.setPosition(position)
                .setAngle(angle)
                .setDimensions(width, height)
                .create(renderer, async);

        // Create the text drawable.
        labelText = new Text(renderer.getFontManager().getFont("fonts/popstar/popstarpop128.xml"));
        labelText.setText("")
                .setPosition(new Vector3f(0f, 0f, 0f))
                .setScaling(200f)
                .setRotation(angle)
                .setColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f))
                .setShader("Font2");

        animations = new UIAnimation[1];
        animations[0] = new UIAnimation("button");
    }

    public Vector3f getPosition() {
        return position;
    }

    public String getText() { return value; }

    /*
     * Setter(s).
     */
    public void setPosition(float x, float y, float z) {
        // Set the position.
        position.set(x, y, z);

        // Set the drawable position.
        drawable.setPosition(position);

        // Set the label position.
        Matrix4f mat = Matrix4f.getYAxisRotation(angle, true);
        mat.translate(position.x, position.y, position.z);
        Vector4f offsetPos = Matrix4f.multiply(mat, new Vector4f(0.5f, 0.1f, 0.1f, 1.0f));
        labelText.setPosition(new Vector3f(offsetPos.x, offsetPos.y, offsetPos.z)); // + 0.12f
    }

    public void setAngle(float angle) {
        this.angle = angle;

        drawable.setAngle(angle);

        // Set the label position.
        Matrix4f mat = Matrix4f.getYAxisRotation(angle, true);
        mat.translate(position.x, position.y, position.z);
        Vector4f offsetPos = Matrix4f.multiply(mat, new Vector4f(0.5f, 0.1f, 0.1f, 1.0f));
        labelText.setPosition(new Vector3f(offsetPos.x, offsetPos.y, offsetPos.z)); // + 0.12f

        labelText.setRotation(angle);
    }

    public void setText(String text) {
        labelText.setText(text);
        value = text;
    }

    public void setAnimationLeft() {
        isAnimated = true;

        currentAnim = 0;

        animations[currentAnim].setDuration(500);
        animations[currentAnim].setAngleParameters(angle, angle + 15);

        Vector4f newPosition = Matrix4f.multiply(Matrix4f.getYAxisRotation(15, true), new Vector4f(position.x, position.y, position.z + 20.0f, 1.0f));

        animations[currentAnim].setPositionParameters(position, new Vector3f(newPosition.x, newPosition.y, newPosition.z - 20.0f));

        animations[currentAnim].start();
    }

    public void setAnimationRight() {
        isAnimated = true;

        currentAnim = 0;

        animations[currentAnim].setDuration(500);
        animations[currentAnim].setAngleParameters(angle, angle - 15);

        Vector4f newPosition = Matrix4f.multiply(Matrix4f.getYAxisRotation(-15, true), new Vector4f(position.x, position.y, position.z + 20.0f, 1.0f));

        animations[currentAnim].setPositionParameters(position, new Vector3f(newPosition.x, newPosition.y, newPosition.z - 20.0f));

        animations[currentAnim].start();
    }

    public boolean checkCollision(float x, float y) {
        // Check circle collision.
        float deltaX = x - (position.x + (height / 2));
        float deltaY = y - (position.y + (height / 2));
        float dist = (float)Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (dist < height / 2) {
            return true;
        }

        // check box collision
        float xMin = position.x + height / 2;
        float xMax = position.x + width;
        float yMin = position.y;
        float yMax = position.y + height;

      return (x <= xMax) && (x >= xMin) && (y <= yMax) && (y >= yMin);

    }

    /*
     * Implement UIEntity.
     */
    @Override
    public void handleInput(MotionEvent event, Renderer renderer) {
        // do nothing.
    }

    @Override
    public void update() {
        if (isAnimated) {
            //Log.d("anim", "update");
            animations[currentAnim].update();

            if (animations[currentAnim].isDone()) {
                Log.d("anim", "done");

                isAnimated = false;
            }

            Vector3f newPos = animations[currentAnim].getCurrentPosition();
            setPosition(newPos.x, newPos.y, newPos.z);

            float newAngle = animations[currentAnim].getCurrentAngle();
            setAngle(newAngle);
        }
    }

    @Override
    public void draw(Renderer renderer) {
        drawable.render(renderer);
        labelText.draw(renderer);
    }
}