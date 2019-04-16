#include "include/JNIMapping.h"
#include "include/AudioManager.h"

/*
 * Mapping for JNI calls.
 */

jfieldID getPtrFieldId(JNIEnv* env, jobject obj) {
    static jfieldID ptrFieldId = 0;

    if (!ptrFieldId) {
        jclass clazz = env->GetObjectClass(obj);
        ptrFieldId = env->GetFieldID(clazz, "audioManagerPtr", "J");
        env->DeleteLocalRef(clazz);
    }

    return ptrFieldId;
}

extern "C" {
    JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_startAudioManager
    (JNIEnv* env, jobject obj) {
        env->SetLongField(obj, getPtrFieldId(env, obj), (jlong) new AudioManager);

        auto* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        audioManager->create();
    }

    JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_closeAudioManager
    (JNIEnv* env, jobject obj) {
        auto* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        audioManager->close();

        delete audioManager;
    }

    JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_playBGM
    (JNIEnv* env, jobject obj, jstring path) {
        const char* filePath = env->GetStringUTFChars(path, NULL);

        auto* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        audioManager->playBGM(filePath);

        env->ReleaseStringUTFChars(path, filePath);
    }

    JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_stopBGM
    (JNIEnv* env, jobject obj) {
        auto* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        audioManager->stopBGM();
    }

    JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_setAssetManager
    (JNIEnv* env, jobject obj, jobject assetManager) {
        auto* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        audioManager->setAssetManager(env, assetManager);
    }

    JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_registerSound
    (JNIEnv* env, jobject obj, jstring path) {
        auto* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        const char* filePath = env->GetStringUTFChars(path, NULL);
        audioManager->registerSound(filePath);
    }

    JNIEXPORT void JNICALL Java_com_scarlet_audio_opensles_AudioPlayer_playSound
    (JNIEnv* env, jobject obj, jstring path) {
        auto* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        const char* filePath = env->GetStringUTFChars(path, NULL);
        audioManager->playSound(filePath);
    }
}