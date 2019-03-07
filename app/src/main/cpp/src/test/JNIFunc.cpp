/**
 * Used for testing JNI setup.
 */

#include "../../include/test/JNIFunc.h"
#include "../../include/test/TestJNI.h"

extern "C"
{
    JNIEXPORT int JNICALL Java_opentouhou_com_opentouhouandroid_sound_opensl_TestJNI_getMagicNumber
    (JNIEnv *pEnv, jobject pThis)
    {
        TestJNI *test = new TestJNI();
        return test->getMagicNumber();
    }
}