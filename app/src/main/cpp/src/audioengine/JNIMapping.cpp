/**
 * Mapping for JNI calls.
 */

#include "../../include/audioengine/JNIMapping.h"
#include "../../include/audioengine/AudioManager.h"

jfieldID getPtrFieldId(JNIEnv* env, jobject obj)
{
    static jfieldID ptrFieldId = 0;

    if (!ptrFieldId)
    {
        jclass clazz = env->GetObjectClass(obj);
        ptrFieldId = env->GetFieldID(clazz, "audioManagerPtr", "J");
        env->DeleteLocalRef(clazz);
    }

    return ptrFieldId;
}

extern "C"
{
    JNIEXPORT void JNICALL Java_opentouhou_com_opentouhouandroid_sound_opensl_AudioPlayer_startAudioManager
    (JNIEnv* env, jobject obj)
    {
        env->SetLongField(obj, getPtrFieldId(env, obj), (jlong) new AudioManager);

        AudioManager* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        audioManager->create();
    }

    JNIEXPORT void JNICALL Java_opentouhou_com_opentouhouandroid_sound_opensl_AudioPlayer_closeAudioManager
    (JNIEnv* env, jobject obj)
    {
        AudioManager* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        audioManager->close();

        delete audioManager;
    }

    JNIEXPORT void JNICALL Java_opentouhou_com_opentouhouandroid_sound_opensl_AudioPlayer_playBGM
    (JNIEnv* env, jobject obj, jstring path)
    {
        const char* filePath = env->GetStringUTFChars(path, NULL);

        AudioManager* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        audioManager->playBGM(filePath);

        env->ReleaseStringUTFChars(path, filePath);
    }

    JNIEXPORT void JNICALL Java_opentouhou_com_opentouhouandroid_sound_opensl_AudioPlayer_stopBGM
    (JNIEnv* env, jobject obj)
    {
        AudioManager* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        audioManager->stopBGM();
    }

    JNIEXPORT void JNICALL Java_opentouhou_com_opentouhouandroid_sound_opensl_AudioPlayer_setAssetManager
    (JNIEnv* env, jobject obj, jobject assetManager)
    {
        AudioManager* audioManager = (AudioManager*) env->GetLongField(obj, getPtrFieldId(env, obj));

        audioManager->setAssetManager(env, assetManager);
    }
}