package com.scarlet.ui;

import com.scarlet.graphics.opengl.Renderer;

public abstract class UIEntity {
    protected boolean isVisible;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public abstract void update();
    public abstract void draw(Renderer renderer);
}