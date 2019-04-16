package opentouhou.com.opentouhouandroid.entity;

import com.scarlet.math.Vector3f;
import com.scarlet.math.Vector4f;

import com.scarlet.graphics.opengl.Text;
import com.scarlet.graphics.opengl.animation.TextAnimation;
import com.scarlet.graphics.opengl.font.FontManager;

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
        Text text = new Text(fontManager.getFont("fonts/popstar/popstarpop128.xml"));

        text.setText("Loading...")
            .setPosition(new Vector3f(-2.0f, -6.75f, 3))
            .setScaling(280f)
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
        Text text = new Text(fontManager.getFont("fonts/popstar/popstarpop128.xml"));

        text.setText("Touch to Continue!")
                .setPosition(new Vector3f(-3.2f, -1.5f, 3))
                .setScaling(280f)
                .setColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f))
                .setShader("Font2");

        return text;
    }

    public static Text CREATE_MAIN_MENU_TITLE(FontManager fontManager) {
        // 0.529f 0.807f 0.921f
        // 1.0f 0.717f 0.772f
        Text text = new Text(fontManager.getFont("fonts/yozakura/yozakura256.xml"));

        text.setText("Open Touhou")
                .setPosition(new Vector3f(-3.5f, 4.0f, 3))
                .setScaling(125f)
                .setColor(new Vector4f(1.0f, 1.0f, 1.0f, 0.8f))
                .setShader("Font");

        return text;
    }
}