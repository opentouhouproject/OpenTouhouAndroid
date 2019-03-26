package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

import android.view.MotionEvent;

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
import opentouhou.com.opentouhouandroid.scene.State;

/*
 * Loading screen implemented with OpenGL ES 3.0 .
 */
public class LoadingScreen30 extends Scene {
    // Track game state.
    public boolean finishedLoading = false;
    public boolean userContinue = false;
    private State<LoadingScreen30> state;

    /*
     * Game Entities
     * Should be package-private.
     */
    public Background background;
    PetalFall petalFall;
    Text title, loadingMessage, loadingFinishedMsg;
    MeilinSprite sprite;

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
        camera = new Camera(0.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);

        // Create light source(s).
        light = new Vector4f(0.0f, 0.0f, 2.0f, 0.0f);

        // Create background.
        background = new Background(renderer, GraphicsObject.Version.OPENGL_ES_30, false);

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
     * Enter the initial state.
     */
    public void init() {
        state = States.LOADING_STATE;
        state.enter(this);
    }

    /*
     * Implement the handleInput method.
     */
    public void handleInput(MotionEvent event) {
        state.handleInput(this, event);
    }

    /*
     * Implement the update method.
     */
    public void update() {
        State<LoadingScreen30> result = state.update(this);

        if (result != null) {
            // Exit the current state.
            state.exit(this);

            // Enter the new state.
            state = result;
            state.enter(this);
        }
    }

    /*
     * Implement the draw method.
     */
    public void draw() {
        background.draw(this);
        //background2.draw(this);
        petalFall.draw(this);
        title.draw(this);
        loadingMessage.draw(this);
        sprite.draw(this);

        if (state == States.FINISHED_STATE) loadingFinishedMsg.draw(this);
    }
}