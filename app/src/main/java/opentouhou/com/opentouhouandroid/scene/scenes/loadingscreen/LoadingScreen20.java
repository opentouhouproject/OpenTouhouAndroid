package opentouhou.com.opentouhouandroid.scene.scenes.loadingscreen;

import android.view.MotionEvent;

import com.scarlet.math.Vector4f;

import java.io.InputStreamReader;

import opentouhou.com.opentouhouandroid.entity.sprite.meilin.MeilinSprite;
import opentouhou.com.opentouhouandroid.entity.petals.PetalFall;
import opentouhou.com.opentouhouandroid.entity.background.Background;
import com.scarlet.graphics.opengl.Camera;
import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.graphics.opengl.Text;
import com.scarlet.graphics.opengl.Version;
import com.scarlet.scene.Scene;
import com.scarlet.scene.Stage;

import opentouhou.com.opentouhouandroid.io.xml.SceneParser;

/*
 * Loading screen implemented with OpenGL ES 2.0 .
 */

public class LoadingScreen20 extends Scene {
    // Game Objects
    private Background background;
    private PetalFall petalFall;
    private Text title, loadingMessage;
    private MeilinSprite sprite;

    // Constructor(s)
    public LoadingScreen20(String name, Stage stage)
    {
        super(name, stage);
    }

    // Loads resources for drawing the scene.
    public void setup()
    {
        // Retrieve the renderer from the stage.
        Renderer renderer = stage.getRenderer();

        // Load the assets.
        InputStreamReader reader = stage.getFileManager().openRawAsset("scene/loadingscreen/loadInfo20.xml");
        SceneParser.parse(reader, this);

        // Create the camera.
        camera = new Camera(0.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);

        // Create light source(s).
        light = new Vector4f(0.0f, 0.0f, 2.0f, 0.0f);

        // Create background.
        background = new Background(renderer, Version.OPENGL_ES_20, false);

        // Create text.
        /*
        FontManager fontManager = renderer.getFontManager();

        title = new Text(fontManager.getFont("fonts/yozakura/yozakura256.xml"));
        title.setText("Scarlet")
                .setPosition(new Vector3f(-3.5f, -1.0f, 3))
                .setScaling(94f)
                .setColor(new Vector4f(1.0f, 0.1412f, 0.0f, 1.0f))
                .setShader("Font");

        TextAnimation msgAnim = new TextAnimation("loading");
        msgAnim.addSequence(new String[]{"Loading", "Loading.", "Loading..", "Loading..."});

        loadingMessage = new Text(fontManager.getFont("fonts/popstar/popstar16.xml"));
        loadingMessage.setText("Loading...")
                .setPosition(new Vector3f(-2.0f, -6.75f, 3))
                .setScaling(40f)
                .setColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f))
                .setShader("Font2")
                .setAnimation(msgAnim);

        // Create petal animation.
        petalFall = new PetalFall(renderer);

        // Create sprite.
        sprite = new MeilinSprite("meilin", renderer);
        */
        // Done loading.
        ready = true;
    }

    public void init() {
        // do nothing
    }

    /*
     * Implement the handleInput method.
     */
    public void handleInput(MotionEvent event) {
        // do nothing
    }

    /*
     * Update the scene.
     */
    public void update() {
        background.update();
    }

    /*
     * Draw the scene.
     */
    public void draw() {
        background.draw(stage.getRenderer());

        //petalFall.draw(this);

        //title.draw(this);

        //loadingMessage.draw(this);

        //sprite.draw(this);
    }
}