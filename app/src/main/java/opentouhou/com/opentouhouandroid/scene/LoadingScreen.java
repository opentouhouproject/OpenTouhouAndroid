package opentouhou.com.opentouhouandroid.scene;

import opentouhou.com.opentouhouandroid.actor.PetalFall;
import opentouhou.com.opentouhouandroid.graphics.common.Background;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Text;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractShaderManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTextureManager;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.sound.opensl.AudioPlayer;

public class LoadingScreen extends Scene
{
    public Text testTitle;
    public Background background;
    public PetalFall petalFall;

    private AudioPlayer aud;

    // Constructor(s)
    public LoadingScreen(String name)
    {
        super(name);

        currentScene = this;
    }

    public void draw()
    {
        background.draw(this);

        petalFall.draw(this);

        testTitle.render("Scarlet", new Vector3f(-3.5f, -1.0f, 3), 94f,this);
    }

    // Loads resources for drawing the scene.
    public void setup(Renderer renderer) {
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

        // Load Objects
        background = new Background(renderer);
        testTitle = new Text("Scarlet", renderer.getFontManager().getFont("fonts/yozakura/yozakura256.xml"));
        petalFall = new PetalFall(renderer);

        // Load Audio
        aud = new AudioPlayer(renderer.getContext());
        aud.play("audio/music/loadingMusic.mp3");

        ready = true;
    }

    private void loadShaders(Renderer renderer)
    {
        // Get the shader manager.
        AbstractShaderManager manager = renderer.getShaderManager();

        // Create vertex shaders.
        manager.createVertexShader("TextureShader", "shaders/opengles30/TextureShader.vert");
        manager.createVertexShader("Font", "shaders/opengles30/Font.vert");
        manager.createVertexShader("Background", "shaders/opengles30/Background.vert");
        manager.createVertexShader("Petal", "shaders/opengles30/Petal.vert");

        // Create fragment shaders.
        manager.createFragmentShader("TextureShader", "shaders/opengles30/TextureShader.frag");
        manager.createFragmentShader("Font", "shaders/opengles30/Font.frag");
        manager.createFragmentShader("Background", "shaders/opengles30/Background.frag");
        manager.createFragmentShader("Petal", "shaders/opengles30/Petal.frag");

        // Create shader programs.
        manager.createShaderProgram("TextureShader", "TextureShader", "TextureShader");
        manager.createShaderProgram("Font", "Font", "Font");
        manager.createShaderProgram("Background", "Background", "Background");
        manager.createShaderProgram("Petal", "Petal", "Petal");
    }

    private void loadTextures(Renderer renderer)
    {
        String[] assets = { "art/loading_bg1.png" };

        // Get the texture manager.
        AbstractTextureManager manager = renderer.getTextureManager();

        // Load the textures.
        manager.loadAssetBitmap("art/loading_bg1.png", AbstractTextureManager.Options.GREYSCALE, renderer);
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