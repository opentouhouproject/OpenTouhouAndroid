package com.scarlet.graphics.opengl.animation;

/*
 * Represents a generic animation.
 */

public abstract class Animation {
    private String name;

    public boolean repeat = true;

    /*
     * Constructor(s).
     */
    public Animation(String name) {
        this.name = name;
    }

    /*
     * Getter(s).
     */
    public String getName() { return name; }

    abstract void update();

    @Override
    public String toString() {
        return "Animation: " + name;
    }
}