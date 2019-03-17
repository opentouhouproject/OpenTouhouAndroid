package opentouhou.com.opentouhouandroid.scene.scenes;

import opentouhou.com.opentouhouandroid.graphics.common.Background;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;
import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.scene.Scene;
import opentouhou.com.opentouhouandroid.scene.Stage;
import opentouhou.com.opentouhouandroid.sound.opensl.AudioPlayer;

public class MainMenuScreen extends Scene
{
    public Background background;

    private AudioPlayer aud;

    // Constructor(s)
    public MainMenuScreen(String name, Stage stage)
    {
        super(name, stage);
    }

    public void draw()
    {
        background.draw(this);
    }

    // Loads resources for drawing the scene.
    public void setup()
    {
        // Get the renderer.
        Renderer renderer = stage.getRenderer();

        // Load the shaders.
        loadShaders(renderer);

        // Load bitmaps.
        loadTextures(renderer);

        // Load fonts.
        loadFonts(renderer);

        // Create the camera.
        camera = new Camera(0.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0, 0, 1, 0);

        // Create light source(s).
        light = new Vector4f(0.0f, 0.0f, 2f, 0f);

        // Create background.
        background = new Background(renderer, GraphicsObject.Version.OPENGL_ES_30);

        // Load Audio
        aud = new AudioPlayer(renderer.getContext());
        aud.play("audio/music/loadingMusic.mp3");

        // Done loading.
        ready = true;
    }

    private void loadShaders(Renderer renderer)
    {
        // Get the shader manager.
        ShaderManager manager = renderer.getShaderManager();

        // Create vertex shaders.
        manager.createVertexShader("TextureShader", "shaders/opengles30/TextureShader.vert");
        manager.createVertexShader("Font", "shaders/opengles30/Font.vert");
        manager.createVertexShader("Background", "shaders/opengles30/Background.vert");
        manager.createVertexShader("Petal", "shaders/opengles30/Petal.vert");

        // Create fragment shaders.
        manager.createFragmentShader("TextureShader", "shaders/opengles30/TextureShader.frag");
        manager.createFragmentShader("Font", "shaders/opengles30/Font.frag");
        manager.createFragmentShader("Font2", "shaders/opengles30/Font2.frag");
        manager.createFragmentShader("Background", "shaders/opengles30/Background.frag");
        manager.createFragmentShader("Petal", "shaders/opengles30/Petal.frag");

        // Create shader programs.
        manager.createShaderProgram("TextureShader", "TextureShader", "TextureShader");
        manager.createShaderProgram("Font", "Font", "Font");
        manager.createShaderProgram("Font2", "Font", "Font2");
        manager.createShaderProgram("Background", "Background", "Background");
        manager.createShaderProgram("Petal", "Petal", "Petal");
    }

    private void loadTextures(Renderer renderer)
    {
        String[] assets = {
                "sprites/meirin/walkfront/walkFront000.png",
                "sprites/meirin/walkfront/walkFront001.png",
                "sprites/meirin/walkfront/walkFront002.png",
                "sprites/meirin/walkfront/walkFront003.png",
                "sprites/meirin/walkfront/walkFront004.png",
                "sprites/meirin/walkfront/walkFront005.png",

                "sprites/meirin/spellHa/spellHa000.png",
                "sprites/meirin/spellHa/spellHa001.png",
                "sprites/meirin/spellHa/spellHa002.png",
                "sprites/meirin/spellHa/spellHa003.png",
                "sprites/meirin/spellHa/spellHa004.png",
                "sprites/meirin/spellHa/spellHa005.png",
                "sprites/meirin/spellHa/spellHa006.png",
                "sprites/meirin/spellHa/spellHa007.png",
                "sprites/meirin/spellHa/spellHa008.png",
                "sprites/meirin/spellHa/spellHa009.png",
                "sprites/meirin/spellHa/spellHa010.png",
                "sprites/meirin/spellHa/spellHa011.png",
                "sprites/meirin/spellHa/spellHa012.png",
                "sprites/meirin/spellHa/spellHa013.png",
                "sprites/meirin/spellHa/spellHa014.png"
        };

        // Get the texture manager.
        TextureManager manager = renderer.getTextureManager();

        // Load the textures.
        manager.loadAssetBitmap("art/loading_bg1.png", TextureManager.Options.GREYSCALE, renderer);

        manager.loadAssetBitmaps(assets, renderer);
    }

    private void loadFonts(Renderer renderer)
    {
        String[] fontList = {
                "fonts/popstar/popstar16.xml",
                "fonts/yozakura/yozakura256.xml"
        };

        // Get the font manager.
        FontManager manager = renderer.getFontManager();

        // Load the fonts.
        manager.loadFonts(fontList, renderer);
    }
}