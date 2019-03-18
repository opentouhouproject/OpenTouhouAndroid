package opentouhou.com.opentouhouandroid.entity.OpenGLES;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * DEPRECATED
 * Remove later.
 */

public class GraphicsObject
{
    protected static final int BYTES_PER_FLOAT = 4;

    /*
        buffer capacity is measured in terms of bytes
        ex. a float is 4 bytes long
     */
    protected static FloatBuffer createBuffer(int capacity, float[] data)
    {
        // allocate a float buffer with the given capacity
        // use the device hardware's native byte order
        FloatBuffer fBuffer = ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder()).asFloatBuffer();

        // add the data to the buffer
        // set the buffer to read the data point
        fBuffer.put(data).position(0);

        return fBuffer;
    }
}