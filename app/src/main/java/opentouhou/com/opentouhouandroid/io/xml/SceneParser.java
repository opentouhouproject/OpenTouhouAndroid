package opentouhou.com.opentouhouandroid.io.xml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStreamReader;

import opentouhou.com.opentouhouandroid.graphics.opengl.common.Renderer;
import opentouhou.com.opentouhouandroid.graphics.opengl.common.texture.TextureManager;
import opentouhou.com.opentouhouandroid.scene.Scene;

/*
 * Parses a load file.
 */
public class SceneParser {
    // XML tag info.
    private static final String VERTEX = "vertex";
    private static final String FRAGMENT = "fragment";
    private static final String PROGRAM = "program";
    private static final String TEXTURE = "texture";
    private static final String FONT = "font";

    /*
     * Constructor(s).
     */
    private SceneParser() { }

    /*
     * Parse and load scene assets.
     */
    public static Scene parse(InputStreamReader reader, Scene scene) {
        // Create a new xml pull parser.
        XmlPullParser parser = Xml.newPullParser();

        // Set the input source for the parser.
        try {
            parser.setInput(reader);
        }
        catch (XmlPullParserException e) {
            throw new RuntimeException("Failed to set parser input.", e);
        }

        // Parse the file.
        try {
            int eventType = parser.getEventType();

            // Read until end of document.
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        handleStartEvent(parser, scene);
                        break;
                }

                eventType = parser.next();
            }
        }
        catch (XmlPullParserException e) {
            throw new RuntimeException("Failed to parse font file. " + e.getMessage(), e);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to parse font file. " + e.getMessage(), e);
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
                throw new RuntimeException("Could not close stream.");
            }
        }

        return scene;
    }

    /*
     * Helper Methods.
     * Handle events.
     */
    private static void handleStartEvent(XmlPullParser parser, Scene scene) {
        String name = parser.getName();

        switch (name) {
            case VERTEX:
                parseVertexTag(parser, scene);
                break;

            case FRAGMENT:
                parseFragmentTag(parser, scene);
                break;

            case PROGRAM:
                parseProgramTag(parser, scene);
                break;

            case TEXTURE:
                parseTextureTag(parser, scene);
                break;

            case FONT:
                parseFontTag(parser, scene);
                break;
        }
    }

    /*
     * Helper Methods.
     * Parse individual tags.
     */
    private static void parseVertexTag(XmlPullParser parser, Scene scene) {
        // Retrieve the values.
        String name = parser.getAttributeValue(null, "name");
        String path = parser.getAttributeValue(null, "path");

        // Perform action.
        scene.loadVertexShader(name, path);
    }

    private static void parseFragmentTag(XmlPullParser parser, Scene scene) {
        // Retrieve the values.
        String name = parser.getAttributeValue(null, "name");
        String path = parser.getAttributeValue(null, "path");

        // Perform action.
        scene.loadFragmentShader(name, path);
    }

    private static void parseProgramTag(XmlPullParser parser, Scene scene) {
        // Retrieve the values.
        String name = parser.getAttributeValue(null, "name");
        String vertex = parser.getAttributeValue(null, "vertex");
        String fragment = parser.getAttributeValue(null, "fragment");

        // Perform action.
        scene.loadShaderProgram(name, vertex, fragment);
    }

    private static void parseTextureTag(XmlPullParser parser, Scene scene) {
        // Retrieve the values.
        String path = parser.getAttributeValue(null, "path");
        String option = parser.getAttributeValue(null, "option");

        TextureManager.Options opt = TextureManager.Options.NONE;
        switch (option) {
            case "NONE":
                opt = TextureManager.Options.NONE;
                break;

            case "GREYSCALE":
                opt = TextureManager.Options.GREYSCALE;
                break;

            case "LIGHTGREYSCALE":
                opt = TextureManager.Options.LIGHTGREYSCALE;
                break;
        }

        // Perform action.
        scene.loadTexture(path, opt);
    }

    private static void parseFontTag(XmlPullParser parser, Scene scene) {
        // Retrieve the values.
        String path = parser.getAttributeValue(null, "path");

        // Perform action.
        scene.loadFont(path);
    }

    @Override
    public String toString() {
        return "Scene load file parser.";
    }
}