package com.scarlet.graphics.opengl.mesh;

import com.scarlet.graphics.opengl.VertexArrayObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class Mesh
{
    protected int attributeBuffer;

    protected VertexArrayObject vao;

    protected int vertexCount;

    protected MeshLayout.Layout layoutFormat;

    // Constructor(s)
    public Mesh()
    {
        vao = null;
    }

    // Getter(s)
    public int getBuffer() { return attributeBuffer; }

    public VertexArrayObject getVAO() { return vao; }

    public int getVertexCount() { return vertexCount; }

  // Create a buffer.
    protected static FloatBuffer createBuffer(float[] data)
    {
        // Allocate a float buffer with the given capacity.
        // Use the device hardware's native byte order.
        int capacity = data.length * MeshLayout.BYTES_PER_FLOAT;
        FloatBuffer fBuffer = ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder()).asFloatBuffer();

        // Add the data to the buffer.
        // Set the buffer position to the start.
        fBuffer.put(data).position(0);

        return fBuffer;
    }
}