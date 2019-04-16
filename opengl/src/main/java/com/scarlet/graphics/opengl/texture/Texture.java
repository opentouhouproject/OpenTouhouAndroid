package com.scarlet.graphics.opengl.texture;

import android.graphics.BitmapFactory;

public abstract class Texture {
    protected final int[] textureHandle;

    private int width;
    private int height;
    private String type;

    // Constructor
    protected Texture() {
        textureHandle = new int[1];

        width = -1;
        height = -1;
        type = "";
    }

    protected Texture(BitmapFactory.Options options) {
        textureHandle = new int[1];

        width = options.outWidth;
        height = options.outHeight;
        type = options.outMimeType;
    }

    // Retrieve the handle.
    public int getTextureHandle()
    {
        return textureHandle[0];
    }

    // Getters
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public String getType() { return type; }
}