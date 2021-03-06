package com.scarlet.graphics.opengl;

import com.scarlet.math.Matrix4f;

import com.scarlet.graphics.opengl.shader.ShaderProgram;
import com.scarlet.graphics.opengl.texture.Texture;
import com.scarlet.graphics.opengl.mesh.Mesh;

public abstract class GraphicsObject {
    // Options
    public enum Version {
        OPENGL_ES_20, OPENGL_ES_30
    }

    protected GraphicsOptions option;
    protected Version version;

    // Properties
    protected Mesh mesh;
    protected Texture texture;
    protected ShaderProgram shaderProgram;
    protected Matrix4f modelMatrix;

    // Constructor(s)
    public GraphicsObject() {
        // Set the options.
        option = new GraphicsOptions(true, true);
        version = Version.OPENGL_ES_30;
    }

    public GraphicsObject(GraphicsOptions option, Version version) {
        // Set the options.
        this.option = option;
        this.version = version;
    }

    // Setter(s)
    public void setMesh(Mesh mesh) { this.mesh = mesh; }

    public void setTexture(Texture texture) { this.texture = texture; }

    public void setShader(ShaderProgram program) { this.shaderProgram = program; }

    public void setModelMatrix(Matrix4f modelMatrix) { this.modelMatrix = modelMatrix; }

    // Draw
    abstract public void draw(Renderer renderer);
}