package opentouhou.com.opentouhouandroid.entity.background;

import com.scarlet.math.Vector3f;

import opentouhou.com.opentouhouandroid.entity.GameEntity;
import com.scarlet.graphics.opengl.GraphicsObject;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.texture.Texture;

public class Background extends GameEntity {
    private GraphicsObject drawable;

    protected Vector3f position = new Vector3f(-5.5f, -10.0f, 0);

    /*
     * Constructor(s).
     */
    public Background(Renderer renderer, GraphicsObject.Version version, boolean async) {
        switch (version) {
            case OPENGL_ES_20:
                drawable = new BackgroundDrawable20(renderer);
                break;

            case OPENGL_ES_30:
                drawable = new BackgroundDrawable30(renderer, async);
                break;
        }
    }

    /*
     * Setter(s).
     */
    public void setPosition(float x, float y, float z) {
        ((BackgroundDrawable30)drawable).position.x = x;
        ((BackgroundDrawable30)drawable).position.y = y;
        ((BackgroundDrawable30)drawable).position.z = z;
    }

    public void setTexture(Texture texture) {
        ((BackgroundDrawable30)drawable).setTexture(texture);
    }

    /*
     * Implement update method.
     */
    public void update() { }

    /*
     * Implement draw method.
     */
    public void draw(Renderer renderer) {
        drawable.draw(renderer);
    }
}