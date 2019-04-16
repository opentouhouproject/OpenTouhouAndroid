#include "include/JNIFunc.h"
#include "include/TestJNI.h"

/*
 * Used for testing the JNI setup.
 */
extern "C" {
    JNIEXPORT int JNICALL Java_com_scarlet_audio_opensles_TestJNI_getMagicNumber
    (JNIEnv *pEnv, jobject pThis) {
        TestJNI *test = new TestJNI();
        return test->getMagicNumber();
    }
}