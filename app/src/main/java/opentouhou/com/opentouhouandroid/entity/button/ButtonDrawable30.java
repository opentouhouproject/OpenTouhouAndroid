package opentouhou.com.opentouhouandroid.entity.button;

import android.opengl.GLES30;

import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import com.scarlet.graphics.opengl.shader.ShaderProgram;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.drawable.GraphicsObject30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Mesh30;
import opentouhou.com.opentouhouandroid.scene.Scene;
import opentouhou.com.opentouhouandroid.scene.loader.CreateVAOTask;

public class ButtonDrawable30 extends GraphicsObject30 {
    private Vector3f position;
    private float width, height, borderWidth;
    private float angle;

    /*
     * Constructor(s).
     */
    ButtonDrawable30() {
        position = new Vector3f(0.0f, 0.0f, 0.0f);
        width = 1.0f;
        height = 1.0f;
        borderWidth = 0.1f;
    }

    /*
     * Setter(s).
     */
    public ButtonDrawable30 setPosition(Vector3f vector) {
        position.x = vector.x;
        position.y = vector.y;
        position.z = vector.z;

        return this;
    }

    public ButtonDrawable30 setDimensions(float width, float height) {
        this.width = width;
        this.height = height;

        return this;
    }

    public ButtonDrawable30 setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;

        return this;
    }

    /*
     * Setup the drawable object.
     */
    public void create(Renderer renderer, boolean async, float degree) {
        // Get the shader program.
        ShaderProgram program = renderer.getShaderManager().getShaderProgram("Button");

        // Create the mesh.
        float[] data = AttributeGenerator.generate(width, height, borderWidth);

        Mesh30 mesh = new Mesh30(data, MeshLayout.Layout.PCN);
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
        angle = degree;
        Matrix4f mat = Matrix4f.getYAxisRotation(degree, true);
        mat.translate(position.x, position.y, position.z);
        setModelMatrix(mat);
    }

    // Override the parent draw method.
    @Override
    public void draw(Scene scene) {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES30.glUseProgram(shaderHandle);

        // Set the transformation matrices.
        setTransformationMatrices(shaderHandle, scene);

        // Set the light source(s).
        setLightPosition(shaderHandle, scene);

        // Set the model.
        Matrix4f mat = Matrix4f.getIdentity();
        mat = Matrix4f.getYAxisRotation(angle, true);
        mat.translate(position.x, position.y, position.z);
        setModelMatrix(mat);

        // Set the mesh.
        setMesh();

        // Draw the inner button.
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 2 * 3);

        // Draw the border.
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 6, ((4 * 2) + (4 * 6)) * 3);
    }
}