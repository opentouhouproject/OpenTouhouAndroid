package opentouhou.com.opentouhouandroid.entity.button;

import opentouhou.com.opentouhouandroid.entity.GameEntity;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.Renderer30;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class Button extends GameEntity {
    ButtonDrawable30 drawable30;

    /*
     * Consructor(s).
     */
    public Button(Renderer renderer, boolean async) {
        drawable30 = new ButtonDrawable30(renderer, async);
    }

    @Override
    public void update() {
        // do nothing
    }

    @Override
    public void draw(Scene scene) {
        drawable30.draw(scene);
    }
}