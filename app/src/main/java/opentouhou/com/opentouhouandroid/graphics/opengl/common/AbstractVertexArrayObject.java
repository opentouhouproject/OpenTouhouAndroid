package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import java.nio.IntBuffer;

public abstract class AbstractVertexArrayObject
{
    protected static final int BYTES_PER_FLOAT = 4;

    // 3 floats cover the x, y, and z coordinates of a vertex.
    protected static int VERTEX_DATA_SIZE = 3;

    // 4 floats cover the r, g, b, a color values of a vertex.
    protected static int COLOR_DATA_SIZE = 4;

    // 3 floats cover the x, y, and z normal vector of a vertex.
    protected static int NORMAL_DATA_SIZE = 3;

    // 2 floats covering x and y texture coordinates of a vertex.
    protected static int TEXTURE_COORDINATE_DATA_SIZE = 2;

    // How many bytes are contained in a vertex.
    protected final int stride = (VERTEX_DATA_SIZE + COLOR_DATA_SIZE + NORMAL_DATA_SIZE + TEXTURE_COORDINATE_DATA_SIZE) * BYTES_PER_FLOAT;

    protected IntBuffer handles;

    public int getHandle() { return handles.get(0); }
}