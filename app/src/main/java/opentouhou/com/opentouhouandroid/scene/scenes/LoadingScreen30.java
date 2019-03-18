package opentouhou.com.opentouhouandroid.scene.scenes;

import android.os.SystemClock;

import opentouhou.com.opentouhouandroid.entity.MeilinSprite;
import opentouhou.com.opentouhouandroid.entity.Petals.PetalFall;
import opentouhou.com.opentouhouandroid.entity.TextEntityGenerator;
import opentouhou.com.opentouhouandroid.graphics.common.Background;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Text;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.ShaderManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;
import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.scene.Scene;
import opentouhou.com.opentouhouandroid.scene.Stage;
import opentouhou.com.opentouhouandroid.scene.state.LoadingScreen.LoadingScreenState;

/*
 * Loading screen implemented with OpenGL ES 3.0 .
 */
public class LoadingScreen30 extends Scene {
    public boolean finishedLoading = false;
    public long start;
    public long cur;

    // Game Objects
    public Background background;
    public PetalFall petalFall;
    public Text title, loadingMessage, loadingFinishedMsg;
    public MeilinSprite sprite;

    LoadingScreenState state;

    // Constructor(s)
    public LoadingScreen30(String name, Stage stage)
    {
        super(name, stage);
    }

    // Loads resources for drawing the scene.
    public void setup()
    {
        // Retrieve the renderer from the stage.
        Renderer renderer = stage.getRenderer();

        // Load the shaders.
        loadShaders(renderer);

        // Load the textures.
        loadTextures(renderer);

        // Load the fonts.
        loadFonts(renderer);

        // Create the camera.
        camera = new Camera(0.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);

        // Create light source(s).
        light = new Vector4f(0.0f, 0.0f, 2.0f, 0.0f);

        // Create background.
        background = new Background(renderer, GraphicsObject.Version.OPENGL_ES_30);

        // Create text.
        FontManager fontManager = renderer.getFontManager();
        title = TextEntityGenerator.CREATE_LOADING_SCREEN_TITLE(fontManager);
        loadingMessage = TextEntityGenerator.CREATE_LOADING_TEXT(fontManager);
        loadingFinishedMsg = TextEntityGenerator.CREATE_LOADING_DONE_TEXT(fontManager);

        // Create petal animation.
        petalFall = new PetalFall(renderer);

        // Create sprite.
        sprite = new MeilinSprite("meilin", renderer);

        state = LoadingScreenState.LOADING_STATE;
        start = SystemClock.uptimeMillis();
        cur = SystemClock.uptimeMillis();

        // Done loading.
        ready = true;
    }

    private void loadShaders(Renderer renderer)
    {
        // Get the shader manager.
        ShaderManager manager = renderer.getShaderManager();

        // Create vertex shaders.
        manager.createVertexShader("TextureShader", "shaders/opengles30/TextureShader.vert", stage.getFileManager());
        manager.createVertexShader("Font", "shaders/opengles30/Font.vert", stage.getFileManager());
        manager.createVertexShader("Background", "shaders/opengles30/Background.vert", stage.getFileManager());
        manager.createVertexShader("Petal", "shaders/opengles30/Petal.vert", stage.getFileManager());

        // Create fragment shaders.
        manager.createFragmentShader("TextureShader", "shaders/opengles30/TextureShader.frag", stage.getFileManager());
        manager.createFragmentShader("Font", "shaders/opengles30/Font.frag", stage.getFileManager());
        manager.createFragmentShader("Font2", "shaders/opengles30/Font2.frag", stage.getFileManager());
        manager.createFragmentShader("Background", "shaders/opengles30/Background.frag", stage.getFileManager());
        manager.createFragmentShader("Petal", "shaders/opengles30/Petal.frag", stage.getFileManager());

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
        manager.loadAssetBitmap("art/loading_bg1.png", TextureManager.Options.GREYSCALE, stage.getFileManager());

        manager.loadAssetBitmaps(assets, stage.getFileManager());
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
        manager.loadFonts(fontList, renderer, stage.getFileManager());
    }

    /*
     * Update the scene.
     */
    public void update() {
        LoadingScreenState result = state.update(this);
        if (result != null)
        {
            state = result;
            state.enter(this);
        }
    }

    /*
     * Draw the scene.
     */
    public void draw() {
        background.draw(this);
        petalFall.draw(this);
        title.draw(this);
        loadingMessage.draw(this);
        sprite.draw(this);

        if (state == LoadingScreenState.FINISHED_STATE) loadingFinishedMsg.draw(this);
    }
}