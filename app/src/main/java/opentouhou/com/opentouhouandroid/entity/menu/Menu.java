package opentouhou.com.opentouhouandroid.entity.menu;

import android.opengl.GLES30;

import opentouhou.com.opentouhouandroid.entity.GameEntity;
import opentouhou.com.opentouhouandroid.entity.button.Button;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.scene.Scene;

public class Menu extends GameEntity {
    private float xSelection = 0.0f;

    Button startButton;
    Button highScores;
    Button optionsButton;
    Button jukeBox;
    Button credits;
    Button exitButton;

    public Menu(Renderer renderer) {
        startButton = new Button(renderer, true, 0);
        startButton.setPosition(xSelection, 0, 3.0f);
        startButton.setText("Start");

        highScores = new Button(renderer, true, 15);
        highScores.setPosition(xSelection + 0f, -1.3f, 3.0f);
        highScores.setText("Highscores");

        optionsButton = new Button(renderer, true, 30);
        optionsButton.setPosition(xSelection + 0.0f, -2.6f, 3.0f);
        optionsButton.setText("Options");

        jukeBox = new Button(renderer, true, 45);
        jukeBox.setPosition(xSelection + 0.0f, -3.9f, 3.0f);
        jukeBox.setText("Juke Box");

        credits = new Button(renderer, true, 60);
        credits.setPosition(xSelection + 0.0f, -5.2f, 3.0f);
        credits.setText("Credits");

        exitButton = new Button(renderer, true, 75);
        exitButton.setPosition(xSelection + 0.0f, -6.5f, 3.0f);
        exitButton.setText("Exit");
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Scene scene) {
        //GLES30.glDisable(GLES30.GL_DEPTH_TEST);
        GLES30.glDisable(GLES30.GL_CULL_FACE);


        exitButton.draw(scene);

        credits.draw(scene);

        jukeBox.draw(scene);

        optionsButton.draw(scene);

        highScores.draw(scene);

        startButton.draw(scene);

        GLES30.glEnable(GLES30.GL_CULL_FACE);
        //GLES30.glEnable(GLES30.GL_DEPTH_TEST);
    }
}