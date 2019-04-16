package com.scarlet.ui.menu;

import android.opengl.GLES30;
import android.util.Log;
import android.view.MotionEvent;

import com.scarlet.graphics.opengl.Renderer;
import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;
import com.scarlet.ui.ExitActivityListener;
import com.scarlet.ui.UIEntity;
import com.scarlet.ui.button.Button;

public class Menu extends UIEntity {
    private float xSelection = 0.0f;

    private Button[] menuItems;
    private int currentItem;

    private boolean isSelected = false;
    private float startY = 0;
    private float endY = 0;

    private String clickValue = "";

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
        menuItems[5].registerOnEventListener(new ExitActivityListener());

        currentItem = 0;
    }

    public String getClickValue() {
        return clickValue;
    }

    @Override
    public void handleInput(MotionEvent event, Renderer renderer) {
        float x = event.getX();
        float y = event.getY();

        Vector3f intersection;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intersection = computeIntersectionPoint(x, y, renderer);

                if (menuItems[currentItem].checkCollision(intersection.x, intersection.y)) {
                    Log.d("Main Menu", "Menu Item Selected");
                    isSelected = true;
                    startY = event.getY();
                }

                break;

            case MotionEvent.ACTION_UP:
                Log.d("Main Menu", "deltaY: " + (y - startY));

                intersection = computeIntersectionPoint(x, y, renderer);

                if (isSelected && menuItems[currentItem].checkCollision(intersection.x, intersection.y)) {
                    Log.d("Main Menu", "Clicked menu item.");
                    menuItems[currentItem].executeSynchronousListener();
                    //menuItems[currentItem].executeAsynchronousListener();

                    clickValue = menuItems[currentItem].getText();

                } else if (isSelected && (y - startY > 50)) {
                    Log.d("Main Menu", "anim down");
                    setDownAnimation();

                } else if (isSelected && (y - startY < 50)) {
                    Log.d("Main Menu", "anim up");
                    setUpAnimation();
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

    /*
     * Helper methods.
     */
    private Vector3f computeIntersectionPoint(float x, float y, Renderer renderer) {
        Vector4f ndc = renderer.getCamera().convertToNDC(x, y, renderer.getScreenWidth(), renderer.getScreenHeight());
        Vector3f position = renderer.getCamera().unProject(ndc);

        return renderer.getCamera().intersectionPoint(new Vector3f(0, 0, 3), new Vector3f(0, 0, 1), position);
    }

    private void setDownAnimation() {
        if (currentItem > 0) {
            for (int i = 0; i < 6; i++) {
                menuItems[i].setAnimationDown();
            }
            currentItem--;
        }
    }

    private void setUpAnimation() {
        if (currentItem < 5) {
            for (int i = 0; i < 6; i++) {
                menuItems[i].setAnimationUp();
            }
            currentItem++;
        }
    }
}