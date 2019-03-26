package opentouhou.com.opentouhouandroid.entity.sprite.meilin;

import com.scarlet.math.Matrix4f;

import opentouhou.com.opentouhouandroid.entity.sprite.Sprite;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.animation.SpriteAnimation;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.Texture;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class MeilinSprite extends Sprite
{
    float positionX = 0;

    public MeilinSprite(String name, Renderer renderer)
    {
        super(name);

        TextureManager manager = renderer.getTextureManager();

        // Load the walking animation.
        SpriteAnimation walkingForward = new SpriteAnimation("walkingForward");
        Texture[] textures = {
                manager.getTexture("sprites/meirin/walkfront/walkFront000.png"),
                manager.getTexture("sprites/meirin/walkfront/walkFront001.png"),
                manager.getTexture("sprites/meirin/walkfront/walkFront002.png"),
                manager.getTexture("sprites/meirin/walkfront/walkFront003.png"),
                manager.getTexture("sprites/meirin/walkfront/walkFront004.png"),
                manager.getTexture("sprites/meirin/walkfront/walkFront005.png")
        };
        walkingForward.setStretch(new float[]{0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f});
        walkingForward.setSequence(textures);

        // Load attack anim
        SpriteAnimation spellHa = new SpriteAnimation("spellHa");
        Texture[] textures2 = {
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
        spellHa.setStretch(new float[]{0.8f, 1.2f, 1.4f, 1.6f, 1.6f, 0.75f, 0.7f, 1.2f, 1.0f, 1.0f, 1f, 1f, 0.8f, 0.8f, 0.8f});
        spellHa.setSequence(textures2);

        addAnimation(walkingForward);
        addAnimation(spellHa);

        currentAnimation = animations.get("spellHa");

        createDrawable(renderer);
    }

    public void update() {
        currentAnimation.update();

        if (currentAnimation.getName() == "walkingForward") {
            positionX += 0.02f;
        }
    }

    public void draw(Scene scene)
    {
        int h = currentAnimation.currentFrame().getHeight();
        int w = currentAnimation.currentFrame().getWidth();

        Matrix4f model = Matrix4f.getIdentity();
        model.scale(1.2f*currentAnimation.currentStretch() * 1, 1.2f*currentAnimation.currentStretch() * ((float)h / (float)w), 1);
        model.translate(-3.2f + positionX, -6.2f, 3.5f);

        drawable.setTexture(currentAnimation.currentFrame());
        drawable.setModelMatrix(model);
        drawable.draw(scene);
    }
}