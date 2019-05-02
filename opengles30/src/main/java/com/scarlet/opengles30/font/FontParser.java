package com.scarlet.opengles30.font;

import android.util.Xml;

import com.scarlet.graphics.opengl.font.Font;
import com.scarlet.graphics.opengl.font.Glyph;

import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Parses the xml metadata for fonts.
 */
public class FontParser {
    // XML tag info.
    private static final String INFO = "info";
    private static final String COMMON = "common";
    private static final String PAGE = "page";
    private static final String CHAR = "char";

    /*
     * Constructor(s).
     */
    private FontParser() { }

    /*
     * Parse the font xml file.
     */
    public static Font parse(InputStreamReader reader, Font font) {
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
                        handleStartTag(parser, font);
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

        return font;
    }

    /*
     * Helper Methods.
     * Handle events.
     */
    private static void handleStartTag(XmlPullParser parser, Font font) {
        String name = parser.getName();

        switch (name) {
            case INFO:
                parseInfoTag(parser, font);
                break;

            case COMMON:
                parseCommonTag(parser, font);
                break;

            case PAGE:
                parsePageTag(parser, font);
                break;

            case CHAR:
                parseCharTag(parser, font);
                break;
        }
    }

    /*
     * Helper Methods.
     * Parse individual tags.
     */
    private static void parseInfoTag(XmlPullParser parser, Font font) {
        // Retrieve the values.
        String face = parser.getAttributeValue(null, "face");
        int fontSize = Integer.parseInt(parser.getAttributeValue(null, "size"));
        boolean isBold = 0 != Integer.parseInt(parser.getAttributeValue(null, "bold"));
        boolean isItalic = 0 != Integer.parseInt(parser.getAttributeValue(null, "italic"));
        boolean isUnicode = 0 != Integer.parseInt(parser.getAttributeValue(null, "unicode"));

        // Set the values.
        font.setFace(face);
        font.setSize(fontSize);
        font.setBold(isBold);
        font.setItalic(isItalic);
        font.setUnicode(isUnicode);
    }

    private static void parseCommonTag(XmlPullParser parser, Font font) {
        // Retrieve the values.
        int width = Integer.parseInt(parser.getAttributeValue(null, "scaleW"));
        int height = Integer.parseInt(parser.getAttributeValue(null, "scaleH"));

        // Set the values.
        font.setWidth(width);
        font.setHeight(height);
    }

    private static void parsePageTag(XmlPullParser parser, Font font) {
        // Retrieve the values.
        String imageFile = parser.getAttributeValue(null, "file");

        // Set the values.
        font.setImageFile(imageFile);
    }

    private static void parseCharTag(XmlPullParser parser, Font font) {
        // Retrieve the values.
        int glyphId = Integer.parseInt(parser.getAttributeValue(null, "id"));
        int x = Integer.parseInt(parser.getAttributeValue(null, "x"));
        int y = Integer.parseInt(parser.getAttributeValue(null, "y"));
        int width = Integer.parseInt(parser.getAttributeValue(null, "width"));
        int height = Integer.parseInt(parser.getAttributeValue(null, "height"));

        // Set the values.
        char key = Character.toChars(glyphId)[0];
        Glyph value = new Glyph30(glyphId, x, y, width, height);
        font.putGlyph(key, value);
    }

    @Override @NotNull
    public String toString() {
        return "Font xml file parser.";
    }
}