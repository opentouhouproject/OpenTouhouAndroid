package opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.drawable;

import android.opengl.GLES20;
import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.GraphicsObject20;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class BackgroundDrawable20 extends GraphicsObject20
{
    // Override the parent draw method.
    @Override
    public void draw(Scene scene)
    {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES30.glUseProgram(shaderHandle);

        // Set color
        int uniformColorHandle = GLES30.glGetUniformLocation(shaderHandle, "uColor");
        GLES30.glUniform4f(uniformColorHandle, 0.9f, 0.6f, 0.3f, 1f);

        // Set the transformation matrices.
        setTransformationMatrices(shaderHandle, scene);

        // Set the light source(s).
        setLightPosition(shaderHandle, scene);

        // Set the texture.
        setTexture(shaderHandle);

        // Set the mesh.
        // Bind to the buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mesh.getBuffer());

        // Get the handles.
        int positionHandle = GLES20.glGetAttribLocation(shaderHandle, "aVertex");
        int colorHandle = GLES20.glGetAttribLocation(shaderHandle, "aColor");
        int normalHandle = GLES20.glGetAttribLocation(shaderHandle, "aNormal");
        int textureCoordinateHandle = GLES20.glGetAttribLocation(shaderHandle, "aTexCoordinate");

        // Enable arrays.
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glEnableVertexAttribArray(colorHandle);
        GLES20.glEnableVertexAttribArray(normalHandle);
        GLES20.glEnableVertexAttribArray(textureCoordinateHandle);

        // Pass in the vertex information.
        GLES20.glVertexAttribPointer(positionHandle, MeshLayout.POSITION_DATA_SIZE, GLES20.GL_FLOAT, false, MeshLayout.PCNT_Stride, MeshLayout.PCNT_POSITION_OFFSET);

        // Pass in the color information.
        GLES20.glVertexAttribPointer(colorHandle, MeshLayout.COLOR_DATA_SIZE, GLES20.GL_FLOAT, false, MeshLayout.PCNT_Stride, MeshLayout.PCNT_COLOR_OFFSET);

        // Pass in the normal information.
        GLES20.glVertexAttribPointer(normalHandle, MeshLayout.NORMAL_DATA_SIZE, GLES20.GL_FLOAT, false, MeshLayout.PCNT_Stride, MeshLayout.PCNT_NORMAL_OFFSET);

        // Pass in the texture coordinate information.
        GLES20.glVertexAttribPointer(textureCoordinateHandle, MeshLayout.TEXTURE_COORDINATE_DATA_SIZE, GLES20.GL_FLOAT, false, MeshLayout.PCNT_Stride, MeshLayout.PCNT_TEXTURE_COORDINATE_OFFSET);

        // Draw the object.
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 6);

        // Disable arrays.
        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(colorHandle);
        GLES20.glDisableVertexAttribArray(normalHandle);
        GLES20.glDisableVertexAttribArray(textureCoordinateHandle);

        // Unbind from the buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }
}