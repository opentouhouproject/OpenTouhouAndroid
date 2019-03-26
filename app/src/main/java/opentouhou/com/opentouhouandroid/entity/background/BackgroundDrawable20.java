package opentouhou.com.opentouhouandroid.entity.background;

import android.opengl.GLES20;

import com.scarlet.math.Matrix4f;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.Texture;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.drawable.GraphicsObject20;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles20.mesh.Mesh20;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class BackgroundDrawable20 extends GraphicsObject20 {
    /*
     * Constructor(s).
     */
    public BackgroundDrawable20(Renderer renderer) {
        setup(renderer);
    }

    /*
     * Setup the drawable object.
     */
    private void setup(Renderer renderer) {
        // Set the mesh.
        // (x, y, z), (r, g, b, a), (x, y, z), (s, t)
        float[] data = {
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
                1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0
        };

        setMesh(new Mesh20(data));

        // Set the texture.
        setTexture(renderer.getTextureManager().getTexture("art/loading_bg1.png"));

        // Set the shader.
        setShader(renderer.getShaderManager().getShaderProgram("Background"));

        // Set the model.
        Texture texture = renderer.getTextureManager().getTexture("art/loading_bg1.png");
        int width = texture.getWidth();
        int height = texture.getHeight();
        float scale = 11.5f;
        Matrix4f mat = Matrix4f.scaleMatrix(scale, scale * ((float)height / (float)width), 1);
        mat.translate(-5.5f, -10.0f, 0);
        setModelMatrix(mat);
    }

    // Override the parent draw method.
    @Override
    public void draw(Scene scene) {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES20.glUseProgram(shaderHandle);

        // Set color
        int uniformColorHandle = GLES20.glGetUniformLocation(shaderHandle, "uColor");
        GLES20.glUniform4f(uniformColorHandle, 0.9f, 0.6f, 0.3f, 1f);

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
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);

        // Disable arrays.
        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(colorHandle);
        GLES20.glDisableVertexAttribArray(normalHandle);
        GLES20.glDisableVertexAttribArray(textureCoordinateHandle);

        // Unbind from the buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }
}