package opentouhou.com.opentouhouandroid.entity;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Text;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.animation.TextAnimation;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.math.Vector4f;

public class TextEntityGenerator {
    private TextEntityGenerator() { }

    public static Text CREATE_LOADING_SCREEN_TITLE(FontManager fontManager) {
        Text text = new Text(fontManager.getFont("fonts/yozakura/yozakura256.xml"));

        text.setText("Scarlet")
            .setPosition(new Vector3f(-3.5f, -1.0f, 3))
            .setScaling(94f)
            .setColor(new Vector4f(1.0f, 0.1412f, 0.0f, 1.0f))
            .setShader("Font");

        return text;
    }

    public static Text CREATE_LOADING_TEXT(FontManager fontManager) {
        Text text = new Text(fontManager.getFont("fonts/popstar/popstar16.xml"));

        text.setText("Loading...")
            .setPosition(new Vector3f(-2.0f, -6.75f, 3))
            .setScaling(40f)
            .setColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f))
            .setShader("Font2");

        TextAnimation anim1 = new TextAnimation("loading");
        anim1.setSequence(new String[]{"Loading", "Loading.", "Loading..", "Loading..."});
        anim1.setMaxDuration(267);

        TextAnimation anim2 = new TextAnimation("loading");
        anim2.setSequence(new String[]{"Loading", "oading", "ading", "ding", "ing", "ng", "g", ""});
        anim2.setMaxDuration(533);
        anim2.repeat = false;

        text.addAnimation(anim1)
            .addAnimation(anim2);
        text.selectAnimation(0);

        return text;
    }

    public static Text CREATE_LOADING_DONE_TEXT(FontManager fontManager) {
        Text text = new Text(fontManager.getFont("fonts/popstar/popstar16.xml"));

        text.setText("Touch to Continue!")
                .setPosition(new Vector3f(-3.2f, -1.5f, 3))
                .setScaling(40f)
                .setColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f))
                .setShader("Font2");

        return text;
    }
}