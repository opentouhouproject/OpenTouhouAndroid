package opentouhou.com.opentouhouandroid.entity.button;

import android.util.Log;

import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import opentouhou.com.opentouhouandroid.entity.GameEntity;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Text;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class Button extends GameEntity {
    // Center of mass position of Button in 3d space.
    private Vector3f position;
    // If the button is rotated then we have a normal and edge.
    private Vector3f normal;
    private Vector3f edge;

    // Width and height of button.
    private float width;
    private float height;

    // Y-axis rotation.
    private float angle = 0;

    // Drawables.
    private ButtonDrawable30 drawable;
    private Text labelText;

    /*
     * Constructor(s).
     */
    public Button(Renderer renderer, boolean async, float angle) {
        position = new Vector3f(0f, 0f, 0f);
        normal = new Vector3f(0f, 0f, 1f);
        edge = new Vector3f(1f, 0f, 0f);

        width = 6.0f;
        height = 1.0f;

        this.angle = angle;

        // Create the button drawable.
        drawable = new ButtonDrawable30();
        drawable.setPosition(position)
                .setAngle(angle)
                .setDimensions(width, height)
                .setBorderWidth(0.1f)
                .create(renderer, async);

        // Create the text drawable.
        labelText = new Text(renderer.getFontManager().getFont("fonts/popstar/popstarpop128.xml"));
        labelText.setText("")
                 .setPosition(new Vector3f(0f, 0f, 0f))
                 .setScaling(200f)
                 .setRotation(angle)
                 .setColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f))
                 .setShader("Font2");
    }

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
        Vector4f offsetPos = Matrix4f.multiply(mat, new Vector4f(position.x - 2.5f, -0.3f, 0.1f, 1.0f));
        labelText.setPosition(new Vector3f(offsetPos.x, offsetPos.y, offsetPos.z)); // + 0.12f
    }

    public void setAngle(float angle) {
        this.angle = angle;

        drawable.setAngle(angle);

        // Set the label position.
        Matrix4f mat = Matrix4f.getYAxisRotation(angle, true);
        mat.translate(position.x, position.y, position.z);
        Vector4f offsetPos = Matrix4f.multiply(mat, new Vector4f(position.x - 2.5f, -0.3f, 0.1f, 1.0f));
        labelText.setPosition(new Vector3f(offsetPos.x, offsetPos.y, offsetPos.z)); // + 0.12f
    }

    public void setText(String text) {
        labelText.setText(text);
    }

    /*
     * Implement GameEntity.
     */
    @Override
    public void update() {
        // do nothing
    }

    @Override
    public void draw(Scene scene) {
        drawable.draw(scene);
        labelText.draw(scene);
    }
}