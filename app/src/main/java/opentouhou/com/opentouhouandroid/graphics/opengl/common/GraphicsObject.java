package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractShaderProgram;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTexture;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.Mesh;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.scene.Scene;

public abstract class GraphicsObject
{
    protected GraphicsOptions option;

    protected Mesh mesh;
    protected AbstractTexture texture;
    protected AbstractShaderProgram shaderProgram;
    protected Matrix4f modelMatrix;

    // Constructor(s)
    public GraphicsObject() { this.option = new GraphicsOptions(true, true); }

    public GraphicsObject(GraphicsOptions option) { this.option = option; }

    // Setter(s)
    public void setMesh(Mesh mesh) { this.mesh = mesh; }

    public void setTexture(AbstractTexture texture) { this.texture = texture; }

    public void setShader(AbstractShaderProgram program) { this.shaderProgram = program; }

    public void setModelMatrix(Matrix4f modelMatrix) { this.modelMatrix = modelMatrix; }

    // Draw
    abstract public void draw(Scene scene);
}