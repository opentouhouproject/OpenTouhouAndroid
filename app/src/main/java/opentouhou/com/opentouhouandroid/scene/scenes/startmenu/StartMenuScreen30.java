package opentouhou.com.opentouhouandroid.scene.scenes.startmenu;

import android.util.Log;
import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Camera;
import com.scarlet.graphics.opengl.GraphicsObject;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.math.Vector4f;
import com.scarlet.scene.Scene;
import com.scarlet.scene.Stage;
import com.scarlet.scene.State;
import com.scarlet.ui.button.PlayButton;
import com.scarlet.ui.charactermenu.CharacterMenu;
import com.scarlet.ui.difficultymenu.DifficultyMenu;
import com.scarlet.ui.menu.Menu;
import com.scarlet.ui.stagemenu.StageMenu;

import java.io.InputStreamReader;

import opentouhou.com.opentouhouandroid.entity.background.Background;
import opentouhou.com.opentouhouandroid.io.xml.SceneParser;

public class StartMenuScreen30 extends Scene {
    // Game State
    private State<StartMenuScreen30> state;

    // Game Objects
    Background background;
    //Menu menu;
    CharacterMenu characterMenu;
    DifficultyMenu difficultyMenu;
    StageMenu stageMenu;
    PlayButton playButton;

    /*
     * Constructor(s).
     */
    public StartMenuScreen30(String name, Stage stage) {
        super(name, stage);
    }

    /*
     * Implement Scene
     */
    @Override
    public void setup() {
// Get the renderer.
        Renderer renderer = stage.getRenderer();

        // Load the assets.
        InputStreamReader reader = stage.getFileManager().openRawAsset("scene/startmenu/loadInfo30.xml");
        SceneParser.parse(reader, this);

        // Create the camera.
        camera = new Camera(0.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);

        // Create light source(s).
        light = new Vector4f(0.0f, 0.0f, 2.0f, 0.0f);

        // Create background.
        background = new Background(renderer, GraphicsObject.Version.OPENGL_ES_30, true);
        background.setTexture(renderer.getTextureManager().getTexture("art/startmenu_bg.png"));

        //menu = new Menu(renderer);

        characterMenu = new CharacterMenu(renderer);
        difficultyMenu = new DifficultyMenu(renderer);
        stageMenu = new StageMenu(renderer);

        playButton = new PlayButton(renderer, true);
        playButton.setPosition(-3.0f, -4.0f, 3.0f);
        playButton.setAngle(0);
        playButton.setText("Play");

        // Finished loading.
        Log.d("Scene Loading.", "Start Menu Loading Completed.");
        ready = true;
    }

    @Override
    public void init() {
        // Setup the state.
        state = States.INITIAL_STATE;
        state.enter(this);
    }

    @Override
    public void handleInput(MotionEvent event) {
        state.handleInput(this, event);
    }

    @Override
    public void update() {
        state.update(this);
    }

    @Override
    public void draw() {
        state.draw(this);
    }
}
