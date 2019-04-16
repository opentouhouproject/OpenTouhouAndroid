/*
 * Class that manages the audio using OpenSL ES.
 * Provides a simpler way for us to interface with JAVA using JNI.
 */

#ifndef SRC_AUDIOMANAGER_H
#define SRC_AUDIOMANAGER_H

#include <cstring>
#include <android/log.h>
#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>

#include "AudioEngine.h"
#include "OutputMixer.h"
#include "AudioPlayer.h"
#include "Resource.h"
#include "AudioBuffer.h"
#include "AudioQueue.h"

class AudioManager {
    public:
        AudioManager();
        ~AudioManager();

        void create();
        void close();

        void setAssetManager(JNIEnv* env, jobject obj);

        void playBGM(const char* pPath);
        void stopBGM();

        AudioBuffer* registerSound(const char* path);
        void playSound(const char* path);

    private:
        AudioEngine* audioEngine;
        OutputMixer* outputMixer;

        AudioPlayer* audioPlayer;

        AAssetManager* assetManager;

        static const int32_t QUEUE_COUNT = 4;
        AudioQueue audioQueues[QUEUE_COUNT];
        int32_t currentAudioQueue;
        AudioBuffer* audioBuffers[32];
        int32_t bufferCount;
};

#endif //SRC_AUDIOMANAGER_H