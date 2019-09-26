package com.opentouhou.opentouhouandroid.scene.scenes.loadingscreen;

import android.view.MotionEvent;

import com.opentouhou.opentouhouandroid.entity.TextEntityGenerator;
import com.opentouhou.opentouhouandroid.entity.petals.PetalFall;
import com.opentouhou.opentouhouandroid.entity.sprite.meilin.MeilinSprite;
import com.opentouhou.opentouhouandroid.io.xml.SceneParser;
import com.scarlet.graphics.opengl.Version;
import com.scarlet.math.Vector4f;

import java.io.InputStreamReader;

import com.opentouhou.opentouhouandroid.entity.background.Background;
import com.scarlet.graphics.opengl.Camera;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.font.Text;
import com.scarlet.graphics.opengl.font.FontManager;
import com.scarlet.scene.LoadingScreen;
import com.scarlet.scene.Stage;

import com.scarlet.scene.State;

/*
 * Loading screen implemented with OpenGL ES 3.0 .
 */
public class LoadingScreen30 extends LoadingScreen {
    // Track game state.
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
        background = new Background(renderer, Version.OPENGL_ES_30, false);

        // Create text.
        FontManager fontManager = renderer.getFontManager();
        title = TextEntityGenerator.CREATE_LOADING_SCREEN_TITLE(fontManager);
        loadingMessage = TextEntityGenerator.CREATE_LOADING_TEXT(fontManager);
        loadingFinishedMsg = TextEntityGenerator.CREATE_LOADING_DONE_TEXT(fontManager);

        // Create petal animation.
        petalFall = new PetalFall(renderer);

        // Create sprite.
        sprite = new MeilinSprite("meilin", renderer);

        getAudioPlayer().register("audio/music/bad_apple.wav");

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
        state.draw(this);
    }
}