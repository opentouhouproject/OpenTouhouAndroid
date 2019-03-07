/**
 * Used for testing JNI setup.
 */

#ifndef SRC_JNIFUNC_H
#define SRC_JNIFUNC_H

#ifdef __cplusplus
extern "C" {
#endif

#include <jni.h>

JNIEXPORT int JNICALL Java_opentouhou_com_opentouhouandroid_sound_opensl_TestJNI_getMagicNumber(JNIEnv* pEnv, jobject pThis);

#ifdef __cplusplus
}
#endif

#endif //SRC_JNIFUNC_H