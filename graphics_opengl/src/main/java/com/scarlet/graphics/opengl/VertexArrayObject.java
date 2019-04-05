package com.scarlet.graphics.opengl;

import java.nio.IntBuffer;

public abstract class VertexArrayObject {
    protected IntBuffer handles;

    public int getHandle() { return handles.get(0); }
}