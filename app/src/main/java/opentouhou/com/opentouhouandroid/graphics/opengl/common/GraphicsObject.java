package opentouhou.com.opentouhouandroid.graphics.opengl.common;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderProgram;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTexture;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.Mesh;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.scene.Scene;

public abstract class GraphicsObject
{
    // Options
    public enum Version
    {
        OPENGL_ES_20, OPENGL_ES_30
    }

    protected GraphicsOptions option;
    protected Version version;

    // Properties
    protected Mesh mesh;
    protected AbstractTexture texture;
    protected ShaderProgram shaderProgram;
    protected Matrix4f modelMatrix;

    // Constructor(s)
    public GraphicsObject()
    {
        // Set the options.
        option = new GraphicsOptions(true, true);
        version = Version.OPENGL_ES_30;
    }

    public GraphicsObject(GraphicsOptions option, Version version)
    {
        // Set the options.
        this.option = option;
        this.version = version;
    }

    // Setter(s)
    public void setMesh(Mesh mesh) { this.mesh = mesh; }

    public void setTexture(AbstractTexture texture) { this.texture = texture; }

    public void setShader(ShaderProgram program) { this.shaderProgram = program; }

    public void setModelMatrix(Matrix4f modelMatrix) { this.modelMatrix = modelMatrix; }

    // Draw
    abstract public void draw(Scene scene);
}