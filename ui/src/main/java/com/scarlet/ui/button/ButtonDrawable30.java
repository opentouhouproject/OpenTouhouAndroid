package com.scarlet.ui.button;

import android.opengl.GLES30;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.mesh.MeshLayout;
import com.scarlet.graphics.opengl.shader.ShaderProgram;
import com.scarlet.loader.CreateVAOTask;
import com.scarlet.math.Matrix4f;
import com.scarlet.math.Vector3f;
import com.scarlet.opengles30.GraphicsObject30;
import com.scarlet.opengles30.mesh.Mesh30;

public class ButtonDrawable30 extends GraphicsObject30 {
    private Vector3f position;
    private float angle;
    private float width, height, borderWidth;

    private boolean isDirty;

    /*
     * Constructor(s).
     */
    public ButtonDrawable30() {
        position = new Vector3f(0.0f, 0.0f, 0.0f);
        angle = 0.0f;
        width = 1.0f;
        height = 1.0f;
        borderWidth = 0.1f;

        isDirty = true;
    }

    /*
     * Setter(s).
     */
    public ButtonDrawable30 setPosition(Vector3f vector) {
        position.x = vector.x;
        position.y = vector.y;
        position.z = vector.z;

        isDirty = true;

        return this;
    }

    public ButtonDrawable30 setAngle(float angle) {
        this.angle = angle;

        isDirty = true;

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
    public void create(Renderer renderer, boolean async) {
        // Get the shader program.
        ShaderProgram program = renderer.getShaderManager().getShaderProgram("Button");

        // Create the mesh.
        float[] data = ButtonDrawableMesh.generate(width, height, borderWidth);

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
        updateModelMatrix();
    }

    private void updateModelMatrix() {
        Matrix4f model = Matrix4f.getYAxisRotation(angle, true);
        model.translate(position.x, position.y, position.z);
        setModelMatrix(model);

        isDirty = false;
    }

    // Override the parent draw method.
    @Override
    public void render(Renderer renderer) {
        // Set the shader program to use.
        int shaderHandle = shaderProgram.getHandle();
        GLES30.glUseProgram(shaderHandle);

        // Set the model.
        if (isDirty) {
            updateModelMatrix();
        }

        // Set the transformation matrices.
        setTransformationMatrices(shaderHandle, renderer.getCamera());

        // Set the light source(s).
        setLightPosition(shaderHandle, renderer.getCamera(), renderer.getLight());

        // Set the mesh.
        setMesh();

        // Draw the inner button.
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 2 * 3);

        // Draw the border.
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 6, ((4 * 2) + (4 * 6)) * 3);
    }
}