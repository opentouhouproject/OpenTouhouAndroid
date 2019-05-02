package com.scarlet.graphics.opengl.mesh;

/*
 * Stores constants for the supported mesh layouts.
 */
public class MeshLayout {
    // Layout formats.
    public enum Layout {
        PCNT, PCN, P2T
    }

    // 3 floats cover the (x, y, z) coordinates of a vertex.
    public static final int POSITION_DATA_SIZE = 3;

    // 2 floats cover the (x, y) coordinates of a vertex.
    public static final int POSITION2D_DATA_SIZE = 2;

    // 4 floats cover the (r, g, b, a) color of a vertex.
    public static final int COLOR_DATA_SIZE = 4;

    // 3 floats cover the (x, y, z) coordinates of the normal vector of a vertex.
    public static final int NORMAL_DATA_SIZE = 3;

    // 2 floats covering (x, y) texture coordinates of a vertex.
    public static final int TEXTURE_COORDINATE_DATA_SIZE = 2;

    // Number of bytes in a float.
    public static final int BYTES_PER_FLOAT = 4;

    // Strides, number of bytes per vertex.
    public static final int PCNT_Stride = (POSITION_DATA_SIZE + COLOR_DATA_SIZE + NORMAL_DATA_SIZE + TEXTURE_COORDINATE_DATA_SIZE) * BYTES_PER_FLOAT;
    public static final int PCN_Stride = (POSITION_DATA_SIZE + COLOR_DATA_SIZE + NORMAL_DATA_SIZE) * BYTES_PER_FLOAT;
    public static final int P2T_Stride = (POSITION2D_DATA_SIZE + TEXTURE_COORDINATE_DATA_SIZE) * BYTES_PER_FLOAT;

    // Offsets
    public static final int PCNT_POSITION_OFFSET = 0;
    public static final int PCNT_COLOR_OFFSET = POSITION_DATA_SIZE * BYTES_PER_FLOAT;
    public static final int PCNT_NORMAL_OFFSET = (POSITION_DATA_SIZE + COLOR_DATA_SIZE) * BYTES_PER_FLOAT;
    public static final int PCNT_TEXTURE_COORDINATE_OFFSET = (POSITION_DATA_SIZE + COLOR_DATA_SIZE + NORMAL_DATA_SIZE) * BYTES_PER_FLOAT;

    public static final int PCN_POSITION_OFFSET = 0;
    public static final int PCN_COLOR_OFFSET = POSITION_DATA_SIZE * BYTES_PER_FLOAT;
    public static final int PCN_NORMAL_OFFSET = (POSITION_DATA_SIZE + COLOR_DATA_SIZE) * BYTES_PER_FLOAT;

    public static final int P2T_POSITION_OFFSET = 0;
    public static final int P2T_TEXTURE_COORDINATE_OFFSET = POSITION2D_DATA_SIZE * BYTES_PER_FLOAT;

    private MeshLayout() { }
}