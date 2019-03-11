package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import java.nio.IntBuffer;

public abstract class AbstractVertexArrayObject
{
    protected IntBuffer handles;

    public int getHandle() { return handles.get(0); }
}