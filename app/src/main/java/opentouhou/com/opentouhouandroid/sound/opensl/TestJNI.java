package opentouhou.com.opentouhouandroid.sound.opensl;

/**
 * Used for testing JNI.
 */

public class TestJNI
{
    static
    {
        System.loadLibrary("test-lib");
    }

    public native int getMagicNumber();
}