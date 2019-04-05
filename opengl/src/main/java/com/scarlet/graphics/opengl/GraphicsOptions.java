package com.scarlet.graphics.opengl;

// Options
public class GraphicsOptions {
    private boolean enableLighting = true;
    private boolean enableTexture = true;

    public GraphicsOptions(boolean enableLighting, boolean enableTexture) {
        this.enableLighting = enableLighting;
        this.enableTexture = enableTexture;
    }

    public boolean lightingSetting() { return enableLighting; }

    public boolean textureSetting() { return enableTexture; }

    public void enableLighting(boolean isEnabled) { enableLighting = isEnabled; }

    public void enableTexture(boolean isEnabled) { enableTexture = isEnabled; }
}