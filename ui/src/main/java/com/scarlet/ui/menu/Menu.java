package com.scarlet.ui.menu;

import android.opengl.GLES30;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;
import com.scarlet.ui.UIEntity;
import com.scarlet.ui.button.Button;

public class Menu extends UIEntity {
    private float xSelection = 0.0f;

    private Button[] menuItems;
    private int currentItem = -1;

    private boolean isSelected = false;
    private float startY = 0;
    private float endY = 0;

    public Menu(Renderer renderer) {
        menuItems = new Button[6];

        menuItems[0] = new Button(renderer, true);
        menuItems[0].setPosition(xSelection, 0, 3.0f);
        menuItems[0].setAngle(0);
        menuItems[0].setText("Start");

        menuItems[1] = new Button(renderer, true);
        menuItems[1].setPosition(xSelection + 0f, -1.3f, 3.0f);
        menuItems[1].setAngle(15);
        menuItems[1].setText("Highscores");

        menuItems[2] = new Button(renderer, true);
        menuItems[2].setPosition(xSelection + 0.0f, -2.6f, 3.0f);
        menuItems[2].setAngle(30);
        menuItems[2].setText("Options");

        menuItems[3] = new Button(renderer, true);
        menuItems[3].setPosition(xSelection + 0.0f, -3.9f, 3.0f);
        menuItems[3].setAngle(45);
        menuItems[3].setText("Juke Box");

        menuItems[4] = new Button(renderer, true);
        menuItems[4].setPosition(xSelection + 0.0f, -5.2f, 3.0f);
        menuItems[4].setAngle(60);
        menuItems[4].setText("Credits");

        menuItems[5] = new Button(renderer, true);
        menuItems[5].setPosition(xSelection + 0.0f, -6.5f, 3.0f);
        menuItems[5].setAngle(75);
        menuItems[5].setText("Exit");

        currentItem = 0;
    }

    @Override
    public void handleInput(MotionEvent event, Renderer renderer) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Vector4f ndc = renderer.getCamera().convertToNDC(x, y, renderer.getScreenWidth(), renderer.getScreenHeight());

                Vector3f position = renderer.getCamera().unProject(ndc);

                Vector3f intersection = renderer.getCamera().intersectionPoint(new Vector3f(0, 0, 3), new Vector3f(0, 0, 1), position);

                if (menuItems[currentItem].checkCollision(intersection.x, intersection.y)) {
                    Log.d("Menu Collision", "MAIN ITEM SELECTED");
                    isSelected = true;
                    startY = event.getY();
                }

                break;

            case MotionEvent.ACTION_UP:
                Log.d("delta y", "deltaY: " + (y - startY));

                if (isSelected && (y - startY > 50)) {
                    Log.d("anim", "anim down");

                    if (currentItem > 0) {
                        for (int i = 0; i < 6; i++) {
                            menuItems[i].setAnimationDown();
                        }
                        currentItem--;
                    }
                }

                if (isSelected && (y - startY < 50)) {
                    Log.d("anim", "anim up");

                    if (currentItem < 5) {
                        for (int i = 0; i < 6; i++) {
                            menuItems[i].setAnimationUp();
                        }
                        currentItem++;
                    }
                }

                isSelected = false;

                break;
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < 6; i++) {
            menuItems[i].update();
        }
    }

    @Override
    public void draw(Renderer renderer) {
        GLES30.glDisable(GLES30.GL_CULL_FACE);

        for (int i = 0; i < 6; i++) {
            menuItems[i].draw(renderer);
        }

        GLES30.glEnable(GLES30.GL_CULL_FACE);
    }
}