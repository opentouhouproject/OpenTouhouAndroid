package opentouhou.com.opentouhouandroid.entity.button;

import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import opentouhou.com.opentouhouandroid.entity.GameEntity;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Text;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class Button extends GameEntity {
    private Vector3f position = new Vector3f(0f, 0f, 0f);
    private float width = 6.0f;
    private float height = 1.0f;

    private ButtonDrawable30 drawable;
    private Text labelText;

    /*
     * Constructor(s).
     */
    public Button(Renderer renderer, boolean async) {
        drawable = new ButtonDrawable30();
        drawable.setPosition(position)
                .setDimensions(width, height)
                .setBorderWidth(0.1f)
                .create(renderer, async);

        labelText = new Text(renderer.getFontManager().getFont("fonts/popstar/popstarpop128.xml"));
        labelText.setText("")
                 .setPosition(new Vector3f(0f, 0f, 0f))
                 .setScaling(200f)
                 .setColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f))
                 .setShader("Font2");
    }

    /*
     * Setter(s).
     */
    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);

        drawable.setPosition(position);

        labelText.setPosition(new Vector3f(x - 1.8f, y - 0.4f, z + 0.1f));
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