package opentouhou.com.opentouhouandroid.actor;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTexture;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTextureManager;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class MeilinSprite extends Sprite
{
    public MeilinSprite(String name, Renderer renderer)
    {
        super(name);

        AbstractTextureManager manager = renderer.getTextureManager();

        // Load the walking animation.
        Animation walkingForward = new Animation("walkingForward");
        AbstractTexture[] textures = {
                manager.getTexture("sprites/meirin/walkfront/walkFront000.png"),
                manager.getTexture("sprites/meirin/walkfront/walkFront001.png"),
                manager.getTexture("sprites/meirin/walkfront/walkFront002.png"),
                manager.getTexture("sprites/meirin/walkfront/walkFront003.png"),
                manager.getTexture("sprites/meirin/walkfront/walkFront004.png"),
                manager.getTexture("sprites/meirin/walkfront/walkFront005.png")
        };
        walkingForward.stretch = new float[]{1, 1, 1, 1, 1, 1};
        walkingForward.addSequence(textures);

        // Load attack anim
        Animation spellHa = new Animation("spellHa");
        AbstractTexture[] textures2 = {
                manager.getTexture("sprites/meirin/spellHa/spellHa000.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa001.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa002.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa003.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa004.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa005.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa006.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa007.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa008.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa009.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa010.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa011.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa012.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa013.png"),
                manager.getTexture("sprites/meirin/spellHa/spellHa014.png")
        };
        spellHa.stretch = new float[]{0.8f, 1.2f, 1.4f, 1.6f, 1.6f, 0.75f, 0.7f, 1.2f, 1.0f, 1.0f, 1f, 1f, 0.8f, 0.8f, 0.8f};
        spellHa.addSequence(textures2);

        addAnimation(walkingForward);
        addAnimation(spellHa);

        currentAnimation = animations.get("spellHa");

        createDrawable(renderer);
    }

    public void draw(Scene scene)
    {
        int h = currentAnimation.currentFrame().getHeight();
        int w = currentAnimation.currentFrame().getWidth();

        Matrix4f model = Matrix4f.getIdentity();
        model.scale(1.2f*currentAnimation.currentStretch() * 1, 1.2f*currentAnimation.currentStretch() * ((float)h / (float)w), 1);
        model.translate(-0.6f, -2.2f, 3.5f);

        drawable.setTexture(currentAnimation.currentFrame());
        drawable.setModelMatrix(model);
        drawable.draw(scene);
        currentAnimation.nextFrame();
    }
}