package com.scarlet.ui;

import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.animation.UIAnimation;

public abstract class UIEntity {
    public boolean isAnimated = false;
    public UIAnimation[] animations;
    private int count = 0;
    protected int currentAnim = -1;

    public void addAnimation(UIAnimation animation) {
        animations[count] = animation;
        count++;
    }

    protected boolean isVisible;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public abstract void handleInput(MotionEvent event, Renderer renderer);
    public abstract void update();
    public abstract void draw(Renderer renderer);
}