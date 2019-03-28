/**
 * Mapping for JNI calls.
 */

#ifndef SRC_JNIMAPPING_H
#define SRC_JNIMAPPING_H

#ifdef __cplusplus
extern "C" {
#endif

#include <jni.h>

JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_startAudioManager(JNIEnv* pEnv, jobject pThis);

JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_closeAudioManager(JNIEnv* pEnv, jobject pThis);

JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_playBGM(JNIEnv* pEnv, jobject pThis, jstring path);

JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_stopBGM(JNIEnv* pEnv, jobject pThis);

JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_setAssetManager(JNIEnv* pEnv, jobject pThis, jobject assetManager);

#ifdef __cplusplus
}
#endif

#endif //SRC_JNIMAPPING_H