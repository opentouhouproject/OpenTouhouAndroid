package com.scarlet.opengles30.texture;

import android.graphics.BitmapFactory;
import android.opengl.GLES30;

import com.scarlet.graphics.opengl.texture.Texture;

public class Texture30 extends Texture {
    /*
     * Constructor(s).
     */
    public Texture30(int handle) {
        super();

        this.textureHandle[0] = handle;
    }

    public Texture30(BitmapFactory.Options options) {
        super(options);

        GLES30.glGenTextures(1, textureHandle, 0);
    }
}