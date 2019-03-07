package opentouhou.com.opentouhouandroid.scene;

import opentouhou.com.opentouhouandroid.R;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Camera;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.GraphicsObject;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.font.FontManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.AbstractTextureManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.shader.AbstractShaderManager;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.GraphicsObject30;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.Text;
import opentouhou.com.opentouhouandroid.graphics.opengl.opengles30.mesh.Quad30;
import opentouhou.com.opentouhouandroid.math.Matrix4f;
import opentouhou.com.opentouhouandroid.math.Vector3f;
import opentouhou.com.opentouhouandroid.math.Vector4f;
import opentouhou.com.opentouhouandroid.sound.opensl.AudioPlayer;
import opentouhou.com.opentouhouandroid.sound.opensl.TestJNI;

public class OpenGLES30Test extends Scene
{
    private GraphicsObject background, fontImage;
    private Text text;

    private AudioPlayer aud;

    // Constructor
    public OpenGLES30Test(String name)
    {
        super(name);
    }

    public void setup(Renderer renderer)
    {
        // (1) Load the shaders.
        loadShaders(renderer);

        // (2) Load bitmaps.
        loadTextures(renderer);

        // (3) Load fonts.
        loadFonts(renderer);

        // (4) Create the camera.
        camera = new Camera(5.5f, 5.5f, 9.9f, 5.5f, 5.5f, 0, 0, 1, 0);

        // (5) Create light source(s).
        light = new Vector4f(5.5f, 5.5f, 2f, 0f);

        // (6) Create objects.
        // Position, Color, Normal, Texture
        // x, y, z, r, g, b, a, x, y, z, s, t
        float[] data = {
            0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
            0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1,
            1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
            0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0,
            1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1,
            1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0
        };

        background = new GraphicsObject30();
        background.setMesh(new Quad30(data, renderer.getShaderManager().getShaderProgramHandle("TextureShader")));
        background.setTexture(renderer.getTextureManager().getTexture(R.drawable.test_touhou_bg));
        background.setShader(renderer.getShaderManager().getShaderProgram("TextureShader"));
        background.setModelMatrix(Matrix4f.scaleMatrix(11, 11, 1));

        fontImage = new GraphicsObject30();
        fontImage.setMesh(new Quad30(data, renderer.getShaderManager().getShaderProgramHandle("TextureShader")));
        fontImage.setTexture(renderer.getTextureManager().getTexture(R.drawable.popstar16_0));
        fontImage.setShader(renderer.getShaderManager().getShaderProgram("TextureShader"));
        Matrix4f model = Matrix4f.scaleMatrix(5, 5, 1);
        model.translate(2, 2, 2);
        fontImage.setModelMatrix(model);

        text = new Text("Hello World!", renderer.getFontManager().getFont("popstar16.xml"));

        // AUDIO
        aud = new AudioPlayer(renderer.getContext());
        aud.play("bgm1.mp3");
    }

    private void loadShaders(Renderer renderer)
    {
        // Get the shader manager.
        AbstractShaderManager manager = renderer.getShaderManager();

        // Create vertex shaders.
        manager.createVertexShader("Triangle", "Triangle.vert");
        manager.createVertexShader("Triangle2", "Triangle2.vert");
        manager.createVertexShader("PerFragmentLighting", "PerFragmentLighting.vert");
        manager.createVertexShader("TextureShader", "TextureShader.vert");

        // Create fragment shaders.
        manager.createFragmentShader("Triangle", "Triangle.frag");
        manager.createFragmentShader("Triangle2", "Triangle2.frag");
        manager.createFragmentShader("PerFragmentLighting", "PerFragmentLighting.frag");
        manager.createFragmentShader("TextureShader", "TextureShader.frag");

        // Create shader programs.
        manager.createShaderProgram("Triangle", "Triangle", "Triangle");
        manager.createShaderProgram("Triangle2", "Triangle2", "Triangle2");
        manager.createShaderProgram("PerFragmentLighting", "PerFragmentLighting", "PerFragmentLighting");
        manager.createShaderProgram("TextureShader", "TextureShader", "TextureShader");
    }

    public void loadTextures(Renderer renderer)
    {
        int[] textureList = {R.drawable.test_touhou_bg};

        // Get the texture manager.
        AbstractTextureManager manager = renderer.getTextureManager();

        // Load the textures.
        manager.loadBitmaps(textureList, renderer);
    }

    public void loadFonts(Renderer renderer)
    {
        String[] fontList = {"popstar16.xml"};

        // Get the font manager.
        FontManager manager = renderer.getFontManager();

        // Load the fonts.
        manager.loadFonts(fontList, renderer);
    }

    public void draw()
    {
        background.draw(this);
        fontImage.draw(this);

        TestJNI test = new TestJNI();
        String s = String.valueOf(test.getMagicNumber());
        text.render("Hello World!" + s, new Vector3f(2, 1, 3), this);
    }
}