package com.scarlet.audio.opensles;

/*
 * Used for testing JNI.
 */
public class TestJNI {
    static {
        System.loadLibrary("test-lib");
    }

    public native int getMagicNumber();
}