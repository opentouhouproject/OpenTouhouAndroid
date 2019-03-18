package opentouhou.com.opentouhouandroid.entity.OpenGLES;

import android.opengl.GLES20;

import java.nio.FloatBuffer;

/**
 * A triangle.
 */

public class Triangle extends GraphicsObject {
    // Handles for the OpenGL program
    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;

    // Number of vertices in the triangle
    private final int vertexCount = 9 / COORDS_PER_VERTEX;

    // Stride values for the color and vertex
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
    private final int colorStride = COLORS_PER_VERTEX * 4; // 4 bytes per vertex

    // Buffers for the color and vertex
    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;

    // Vertex coordinates
    // x, y, z coordinates
    static final int COORDS_PER_VERTEX = 3;
    final float triangleCoords[];

    // Color values
    // Red, Green, Blue and Alpha (opacity) values
    static final int COLORS_PER_VERTEX = 4;
    final float color[];

    public Triangle(int shaderHandle, float[] vertices, float[] colors) {
        triangleCoords = vertices;
        color = colors;

        // create the buffers
        vertexBuffer = createBuffer(triangleCoords.length * 4, triangleCoords);
        colorBuffer = createBuffer(color.length * 4, color);

        // reference to shader program
        mProgram = shaderHandle;
    }

    public void draw(float[] mvpMatrix) {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // pass in the vertex data to the shader
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // pass in the color data to the shader
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
        GLES20.glEnableVertexAttribArray(mColorHandle);
        GLES20.glVertexAttribPointer(mColorHandle, COLORS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                colorStride, colorBuffer);

        // pass in the MVP matrix to the shader
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable the arrays
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mColorHandle);
    }
}