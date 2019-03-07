/**
 * Class that manages the audio using OpenSL ES.
 * Provides a simpler way for us to interface with JAVA using JNI.
 */

#include "../../include/audioengine/AudioManager.h"

// Constructor(s)
AudioManager::AudioManager() { }

// Start the manager.
void AudioManager::create()
{
    audioEngine = new AudioEngine();
    audioEngine->createEngine();

    outputMixer = new OutputMixer();
    outputMixer->start(audioEngine->getInterface());
}

// Close the manager.
void AudioManager::close()
{
    audioEngine->close();
}

// Set asset manager.
void AudioManager::setAssetManager(JNIEnv* env, jobject obj)
{
    assetManager = AAssetManager_fromJava(env, obj);
}

// Play a BGM file.
void AudioManager::playBGM(const char* filePath)
{
    SLresult result;

    Resource res = Resource(assetManager, filePath); // fix
    ResourceDescriptor desc = res.descript();

    if (desc.descriptor < 0)
    {
        __android_log_print(ANDROID_LOG_VERBOSE, "SL LIB ERR", "Resource descriptor invalid.");
        return;
    }

    audioPlayer = new AudioPlayer();

    audioPlayer->setDataLocatorIn(desc);
    audioPlayer->setDataFormat();
    audioPlayer->setDataSource();
    audioPlayer->setDataLocatorOut(outputMixer);
    audioPlayer->setDataSink();

    audioPlayer->enablePlay(true);
    audioPlayer->enableSeek(true);

    audioPlayer->create(audioEngine->getInterface());

    audioPlayer->play();
}

// Stop the music.
void AudioManager::stopBGM()
{
    audioPlayer->stop();
}