package opentouhou.com.opentouhouandroid.entity.button;

import android.opengl.GLES30;

import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderProgram;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.drawable.GraphicsObject30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Mesh30;
import opentouhou.com.opentouhouandroid.scene.Scene;
import opentouhou.com.opentouhouandroid.scene.loader.CreateVAOTask;

public class ButtonDrawable30 extends GraphicsObject30 {
    public Vector3f position = new Vector3f(0f, 0f, 3f);
    /*
     * Constructor(s).
     */
    public ButtonDrawable30(Renderer renderer, boolean async) {
        setup(renderer, async);
    }

    /*
     * Setup the drawable object.
     */
    private void setup(Renderer renderer, boolean async) {
        // Get items.
        ShaderProgram program = renderer.getShaderManager().getShaderProgram("Button");

        // Set the mesh.
        // (x, y, z), (r, g, b, a), (x, y, z), (s, t)
        AttributeGenerator gen = new AttributeGenerator();
        gen.generate();
        //float[] data = gen.innerAttributes;
        float[] data2 = gen.attributes;

        Mesh30 mesh = new Mesh30(data2, MeshLayout.Layout.PCN);
        if (async) {
            CreateVAOTask task = new CreateVAOTask(mesh, program.getHandle());
            renderer.queue(task.getRunnable());
        }
        else {
            mesh.createVAO(program.getHandle());
        }

        setMesh(mesh);

        // Set the shader.
        setShader(program);

        // Set the model.
        Matrix4f mat = Matrix4f.getIdentity();
        mat.translate(position.x, position.y, position.z);
        setModelMatrix(mat);
    }

    // Override the parent draw method.
    @Override
    public void draw(Scene scene) {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES30.glUseProgram(shaderHandle);

        // Set color
        //int uniformColorHandle = GLES30.glGetUniformLocation(shaderHandle, "uColor");
        //GLES30.glUniform4f(uniformColorHandle, 0.9f, 0.6f, 0.3f, 1f);

        // Set the transformation matrices.
        setTransformationMatrices(shaderHandle, scene);

        // Set the light source(s).
        setLightPosition(shaderHandle, scene);

        // Set the model.
        Matrix4f mat = Matrix4f.getIdentity();
        mat.translate(position.x, position.y, position.z);
        setModelMatrix(mat);

        // Set the mesh.
        setMesh();

        // Draw the object.
        //GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, ((4 * 2) + (4 * 6)) * 3);

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 2 * 3);

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 6, ((4 * 2) + (4 * 6)) * 3);
    }
}