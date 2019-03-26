package opentouhou.com.opentouhouandroid.entity;

import opentouhou.com.opentouhouandroid.scene.Scene;

public abstract class GameEntity {
    protected boolean isVisible;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public abstract void update();
    public abstract void draw(Scene scene);
}