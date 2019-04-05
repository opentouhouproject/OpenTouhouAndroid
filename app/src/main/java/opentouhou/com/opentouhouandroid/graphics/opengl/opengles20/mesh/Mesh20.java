package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.mesh;

import android.opengl.GLES20;

import java.nio.FloatBuffer;

import com.scarlet.graphics.opengl.mesh.Mesh;
import com.scarlet.graphics.opengl.mesh.MeshLayout;

public class Mesh20 extends Mesh
{
    // Constructor.
    public Mesh20(float[] attributes)
    {
        // Generate buffer(s).
        int[] buffers = new int[1];
        GLES20.glGenBuffers(1, buffers, 0);
        attributeBuffer = buffers[0];

        // Allocate memory on GPU for the attribute data.
        allocateGPUMemory(attributes);
    }

    private void allocateGPUMemory(float[] attributes)
    {
        // Allocate memory on the native heap.
        FloatBuffer clientMemoryBuffer = createBuffer(attributes);

        // Bind to the buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, attributeBuffer);

        // Transfer memory to the GPU / buffer.
        // We can release the memory on the native heap when we finish this call. (GC handled)
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER,clientMemoryBuffer.capacity() * MeshLayout.BYTES_PER_FLOAT, clientMemoryBuffer, GLES20.GL_STATIC_DRAW);

        // Unbind from the buffer when done with it.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }
}