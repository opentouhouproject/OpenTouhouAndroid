package opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh;

import android.opengl.GLES30;

import java.nio.FloatBuffer;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.Mesh;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.VertexArrayObject30;

public class Mesh30 extends Mesh
{
    // Constructor.
    public Mesh30(float[] attributes, int shader, MeshLayout.Layout layout)
    {
        layoutFormat = layout;

        // Generate the buffer(s).
        int[] buffers = new int[1];
        GLES30.glGenBuffers(1, buffers, 0);
        attributeBuffer = buffers[0];

        // Allocate memory on GPU for the attribute data.
        allocateGPUMemory(attributes);

        // Setup the VAO.
        vao = new VertexArrayObject30(attributeBuffer, shader, layout);
    }

    private void allocateGPUMemory(float[] attributes)
    {
        switch(layoutFormat)
        {
            case PCNT:
                vertexCount = attributes.length / 12;
                break;

            case PCN:
                vertexCount = attributes.length / 10;
                break;
        }

        // Allocate memory on the native heap.
        FloatBuffer clientMemoryBuffer = createBuffer(attributes);

        // Bind to the buffer.
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, attributeBuffer);

        // Transfer memory to the GPU / buffer.
        // We can release the memory on the native heap when we finish this call. (GC handled)
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER,clientMemoryBuffer.capacity() * MeshLayout.BYTES_PER_FLOAT, clientMemoryBuffer, GLES30.GL_STATIC_DRAW);

        // Unbind from the buffer when done with it.
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
    }
}