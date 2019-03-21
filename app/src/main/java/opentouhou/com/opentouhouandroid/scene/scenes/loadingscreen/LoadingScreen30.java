package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

import java.io.InputStreamReader;

import opentouhou.com.opentouhouandroid.entity.sprite.meilin.MeilinSprite;
import opentouhou.com.opentouhouandroid.entity.petals.PetalFall;
import opentouhou.com.opentouhouandroid.entity.TextEntityGenerator;
import opentouhou.com.opentouhouandroid.entity.background.Background;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Text;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.io.xml.SceneParser;
import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.scene.Scene;
import opentouhou.com.opentouhouandroid.scene.Stage;

/*
 * Loading screen implemented with OpenGL ES 3.0 .
 */
public class LoadingScreen30 extends Scene {
    public boolean finishedLoading = false;

    // Game Objects
    public Background background;
    public PetalFall petalFall;
    public Text title, loadingMessage, loadingFinishedMsg;
    public MeilinSprite sprite;

    LoadingScreenState state;

    /*
     * Constructor(s)
     */
    public LoadingScreen30(String name, Stage stage) {
        super(name, stage);
    }

    /*
     * Loads resources for drawing the scene.
     */
    public void setup() {
        // Retrieve the renderer from the stage.
        Renderer renderer = stage.getRenderer();

        // Load the assets.
        InputStreamReader reader = stage.getFileManager().openRawAsset("scene/loadingscreen/loadInfo30.xml");
        SceneParser.parse(reader, this);

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

        // Done loading.
        ready = true;
    }

    /*
     * Setup initial state.
     */
    public void init() {
        // Setup the state.
        state = LoadingScreenState.LOADING_STATE;
        state.enter(this);

        // Set the current scene.
        stage.setCurrentScene(this);
    }

    /*
     * Update the scene.
     */
    public void update() {
        LoadingScreenState result = state.update(this);

        if (result != null) {
            // Exit current state.
            state.exit(this);

            // Enter new state.
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