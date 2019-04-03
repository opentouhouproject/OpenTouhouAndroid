package opentouhou.com.opentouhouandroid.entity.sprite;

import com.scarlet.math.Matrix4f;

import java.util.Hashtable;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsOptions;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.animation.SpriteAnimation;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.mesh.MeshLayout;
import com.scarlet.graphics.opengl.shader.ShaderProgram;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.drawable.GraphicsObject30;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Mesh30;

/*
 * Represents a sprite in a game.
 */

public class Sprite {
    private String name;

    // Stores all animations.
    protected Hashtable<String, SpriteAnimation> animations;

    // Current animation.
    protected SpriteAnimation currentAnimation;

    // Drawable object.
    protected GraphicsObject drawable;

    /*
     * Constructor(s).
     */
    public Sprite(String name) {
        this.name = name;

        animations = new Hashtable<>(8);
        currentAnimation = null;
        drawable = null;
    }

    /*
     * Getter(s).
     */
    public String getName() {
        return name;
    }

    /*
     * Animation Management.
     */
    public void addAnimation(SpriteAnimation animation) {
        animations.put(animation.getName(), animation);
    }

    public void removeAnimation(String name) {
        animations.remove(name);
    }

    public void selectAnimation(String name) {
        currentAnimation = animations.get(name);
    }

    /*
     * Creates the drawable.
     */
    protected void createDrawable(Renderer renderer)
    {
        ShaderProgram program = renderer.getShaderManager().getShaderProgram("TextureShader");

        // Generate the mesh.
        float[] data = {
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
                0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
                1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
                1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0
        };
        Mesh30 mesh = new Mesh30(data, MeshLayout.Layout.PCNT);
        mesh.createVAO(program.getHandle());

        // Create the drawable.
        GraphicsOptions opt = new GraphicsOptions(true, true);
        drawable = new GraphicsObject30(opt);

        // Set the mesh.
        drawable.setMesh(mesh);

        // Set the shader.
        drawable.setShader(program);

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