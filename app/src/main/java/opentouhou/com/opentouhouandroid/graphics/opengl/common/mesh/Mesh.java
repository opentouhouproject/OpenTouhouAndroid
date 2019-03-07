package opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.AbstractVertexArrayObject;

public abstract class Mesh
{
    // Number of bytes in a float.
    public static final int BYTES_PER_FLOAT = 4;

    // 3 floats cover the x, y, and z coordinates of a vertex.
    public static int VERTEX_DATA_SIZE = 3;

    // 4 floats cover the r, g, b, a color values of a vertex.
    public static int COLOR_DATA_SIZE = 4;

    // 3 floats cover the x, y, and z normal vector of a vertex.
    public static int NORMAL_DATA_SIZE = 3;

    // 2 floats covering x and y texture coordinates of a vertex.
    public static int TEXTURE_COORDINATE_DATA_SIZE = 2;

    // How many bytes are contained in a vertex.
    public final int stride = (VERTEX_DATA_SIZE + COLOR_DATA_SIZE + NORMAL_DATA_SIZE + TEXTURE_COORDINATE_DATA_SIZE) * BYTES_PER_FLOAT;

    protected int attributeBuffer;

    protected AbstractVertexArrayObject vao;

    // Constructor
    public Mesh()
    {
        vao = null;
    }

    // Getters
    public int getBuffer() { return attributeBuffer; }

    public AbstractVertexArrayObject getVAO() { return vao; }

    // Create a buffer.
    protected static FloatBuffer createBuffer(float[] data)
    {
        // allocate a float buffer with the given capacity
        // use the device hardware's native byte order
        int capacity = data.length * BYTES_PER_FLOAT;
        FloatBuffer fBuffer = ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder()).asFloatBuffer();

        // add the data to the buffer
        // set the buffer to read the data point
        fBuffer.put(data).position(0);

        return fBuffer;
    }
}