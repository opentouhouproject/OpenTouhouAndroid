package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

import android.util.Log;
import android.view.MotionEvent;

import com.scarlet.math.Vector4f;

import java.io.InputStreamReader;

import opentouhou.com.opentouhouandroid.entity.TextEntityGenerator;
import opentouhou.com.opentouhouandroid.entity.background.Background;

import com.scarlet.graphics.opengl.Camera;
import com.scarlet.graphics.opengl.GraphicsObject;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.Text;
import com.scarlet.graphics.opengl.font.FontManager;

import com.scarlet.scene.Scene;
import com.scarlet.scene.Stage;

import opentouhou.com.opentouhouandroid.io.xml.SceneParser;

import com.scarlet.scene.State;
import com.scarlet.ui.menu.Menu;

/*
 * A main menu for the game!
 */
public class MainMenuScreen30 extends Scene {
    // Game State
    private State<MainMenuScreen30> state;

    // Game Objects
    Background background;
    Text title;
    Menu menu;

    /*
     * Constructor(s)
     */
    public MainMenuScreen30(String name, Stage stage) {
        super(name, stage);
    }

    /*
     * Loads assets and setup scene.
     */
    public void setup() {
        // Get the renderer.
        Renderer renderer = stage.getRenderer();

        // Load the assets.
        InputStreamReader reader = stage.getFileManager().openRawAsset("scene/mainmenu/loadInfo30.xml");
        SceneParser.parse(reader, this);

        // Create the camera.
        camera = new Camera(0.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);

        // Create light source(s).
        light = new Vector4f(0.0f, 0.0f, 2.0f, 0.0f);

        // Create background.
        background = new Background(renderer, GraphicsObject.Version.OPENGL_ES_30, true);
        background.setTexture(renderer.getTextureManager().getTexture("art/mm_bg1.png"));

        FontManager fontManager = renderer.getFontManager();
        title = TextEntityGenerator.CREATE_MAIN_MENU_TITLE(fontManager);

        menu = new Menu(renderer);

        // Finished loading.
        Log.d("DONE", "MAIN MENU LOAD COMPLETE.");
        ready = true;
    }

    /*
     * Setup initial state.
     */
    public void init() {
        // Setup the state.
        state = States.INITIAL_STATE;
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
        state.update(this);
    }

    /*
     * Implement the draw method.
     */
    public void draw() {
        state.draw(this);
    }
}