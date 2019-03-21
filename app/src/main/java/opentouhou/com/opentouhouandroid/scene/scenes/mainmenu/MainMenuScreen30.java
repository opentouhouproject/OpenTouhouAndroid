package opentouhou.com.opentouhouandroid.scene.scenes.mainmenu;

import java.io.InputStreamReader;

import opentouhou.com.opentouhouandroid.entity.background.Background;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.io.xml.SceneParser;
import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.scene.Scene;
import opentouhou.com.opentouhouandroid.scene.Stage;

/*
 * A main menu for the game!
 */
public class MainMenuScreen30 extends Scene {
    // Scene state.
    private State state;

    // Game Objects
    public Background background;

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
        camera = new Camera(0.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0, 0, 1, 0);

        // Create light source(s).
        light = new Vector4f(0.0f, 0.0f, 2f, 0f);

        // Create background.
        background = new Background(renderer, GraphicsObject.Version.OPENGL_ES_30);

        // Finished loading.
        ready = true;
    }

    /*
     * Setup initial state.
     */
    public void init() {
        // Setup the state.
        state = States.INITIAL_STATE;
        state.enter(this);

        // Set the current scene.
        stage.setCurrentScene(this);
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
        background.draw(this);
    }
}