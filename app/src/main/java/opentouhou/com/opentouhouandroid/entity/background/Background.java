package opentouhou.com.opentouhouandroid.entity.background;

import opentouhou.com.opentouhouandroid.entity.GameEntity;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class Background extends GameEntity {
    private GraphicsObject drawable;

    /*
     * Constructor(s).
     */
    public Background(Renderer renderer, GraphicsObject.Version version) {
        switch (version) {
            case OPENGL_ES_20:
                drawable = new BackgroundDrawable20(renderer);
                break;

            case OPENGL_ES_30:
                drawable = new BackgroundDrawable30(renderer);
                break;
        }
    }

    /*
     * Implement update method.
     */
    public void update() { }

    /*
     * Implement draw method.
     */
    public void draw(Scene scene) {
        drawable.draw(scene);
    }
}