/**
 * Class that manages the audio using OpenSL ES.
 * Provides a simpler way for us to interface with JAVA using JNI.
 */

#ifndef SRC_AUDIOMANAGER_H
#define SRC_AUDIOMANAGER_H

#include <android\log.h>

#include <android\asset_manager.h>
#include <android\asset_manager_jni.h>

#include "AudioEngine.h"

#include "OutputMixer.h"

#include "AudioPlayer.h"

#include "ResourceManager.h"

class AudioManager
{
    public:
        AudioManager();

        void create();
        void close();

        void setAssetManager(JNIEnv* env, jobject obj);

        void playBGM(const char* pPath);
        void stopBGM();

    private:
        AudioEngine* audioEngine;
        OutputMixer* outputMixer;
        AudioPlayer* audioPlayer;

        SLObjectItf mBGMPlayerObj;
        SLPlayItf mBGMPlayer;
        SLSeekItf mBGMPlayerSeek;

        AAssetManager* assetManager;
};

#endif //SRC_AUDIOMANAGER_H