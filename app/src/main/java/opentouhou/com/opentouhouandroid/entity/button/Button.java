package opentouhou.com.opentouhouandroid.entity.button;

import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import opentouhou.com.opentouhouandroid.entity.GameEntity;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Text;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.Renderer30;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class Button extends GameEntity {
    private Vector3f position = new Vector3f(0f, 0f, 0f);

    ButtonDrawable30 drawable30;
    Text labelText;

    /*
     * Constructor(s).
     */
    public Button(Renderer renderer, boolean async) {
        drawable30 = new ButtonDrawable30(renderer, async);
        labelText = new Text(renderer.getFontManager().getFont("fonts/popstar/popstarpop128.xml"));

        labelText.setText("")
                 .setPosition(new Vector3f(0f, 0f, 0f))
                 .setScaling(200f)
                 .setColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f))
                 .setShader("Font2");
    }

    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);

        drawable30.position.set(x, y, z);
        labelText.setPosition(new Vector3f(x - 1.8f, y - 0.4f, z + 0.1f));
    }

    public void setText(String text) {
        labelText.setText(text);
    }

    @Override
    public void update() {
        // do nothing
    }

    @Override
    public void draw(Scene scene) {
        drawable30.draw(scene);
        labelText.draw(scene);
    }
}