package opentouhou.com.opentouhouandroid.actor;

import java.util.Hashtable;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsOptions;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.GraphicsObject30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Mesh30;
import opentouhou.com.opentouhouandroid.math.Matrix4f;

/**
 * Represents a sprite in a game.
 */

public class Sprite
{
    private String name;

    protected Hashtable<String, Animation> animations;

    protected Animation currentAnimation;

    protected GraphicsObject drawable;

    public Sprite(String name)
    {
        this.name = name;

        animations = new Hashtable<>();
    }

    public String getName()
    {
        return name;
    }

    public void addAnimation(Animation animation)
    {
        animations.put(animation.getName(), animation);
    }

    public void removeAnimation(String name)
    {
        animations.remove(name);
    }

    protected void createDrawable(Renderer renderer)
    {
        // Generate the mesh.
        float[] data = {
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
                1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0
        };
        Mesh30 mesh = new Mesh30(data, renderer.getShaderManager().getShaderProgramHandle("TextureShader"), MeshLayout.Layout.PCNT);

        // Create the drawable.
        GraphicsOptions opt = new GraphicsOptions(true, true);
        drawable = new GraphicsObject30(opt);

        // Set the mesh.
        drawable.setMesh(mesh);

        // Set the shader.
        drawable.setShader(renderer.getShaderManager().getShaderProgram("TextureShader"));

        // Set the texture.
        drawable.setTexture(currentAnimation.currentFrame());

        // Set the model matrix.
        int h = currentAnimation.currentFrame().getHeight();
        int w = currentAnimation.currentFrame().getWidth();

        Matrix4f model = Matrix4f.getIdentity();
        model.scale(1, ((float)h / (float)w), 1);
        model.translate(0, 0, 3.3f);
        drawable.setModelMatrix(model);
    }

    @Override
    public String toString()
    {
        return "Sprite: " + name;
    }
}