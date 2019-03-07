package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh;

import android.opengl.GLES30;

import java.nio.FloatBuffer;

import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.VertexArrayObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.Mesh;

public class Quad30 extends Mesh
{
    // Constructor.
    public Quad30(float[] attributes, int shader)
    {
        // Generate the buffer(s).
        int[] buffers = new int[1];
        GLES30.glGenBuffers(1, buffers, 0);
        attributeBuffer = buffers[0];

        // Allocate memory on GPU for the attribute data.
        allocateGPUMemory(attributes);

        // Setup the VAO.
        vao = new VertexArrayObject(attributeBuffer, shader);
    }

    private void allocateGPUMemory(float[] attributes)
    {
        // Allocate memory on the native heap.
        FloatBuffer clientMemoryBuffer = createBuffer(attributes);

        // Bind to the buffer.
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, attributeBuffer);

        // Transfer memory to the GPU / buffer.
        // We can release the memory on the native heap when we finish this call. (GC handled)
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER,clientMemoryBuffer.capacity() * BYTES_PER_FLOAT, clientMemoryBuffer, GLES30.GL_STATIC_DRAW);

        // Unbind from the buffer when done with it.
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
    }
}