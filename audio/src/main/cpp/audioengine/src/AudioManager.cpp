/**
 * Class that manages the audio using OpenSL ES.
 * Provides a simpler way for us to interface with JAVA using JNI.
 */

#include "include/AudioManager.h"

// Constructor(s)
AudioManager::AudioManager() {
    audioEngine = new AudioEngine();
    outputMixer = new OutputMixer();
    audioPlayer = nullptr;
}

// Destructor(s)
AudioManager::~AudioManager() {
    delete outputMixer;
    delete audioEngine;
}

// Create the manager.
void AudioManager::create() {
    audioEngine->create();
    outputMixer->create(audioEngine->getInterface());
}

// Close the manager.
void AudioManager::close() {
    outputMixer->close();
    audioEngine->close();
}

// Set the asset manager.
void AudioManager::setAssetManager(JNIEnv* env, jobject obj) {
    assetManager = AAssetManager_fromJava(env, obj);
}

// Play a BGM file.
void AudioManager::playBGM(const char* filePath) {
    // Open the resource.
    ResourceManager res = ResourceManager(assetManager);
    ResourceDescriptor desc = res.descript(filePath);
    if (desc.descriptor < 0) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Resource descriptor is invalid.");
        return;
    }

    // Create a new audio player.
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
void AudioManager::stopBGM() {
    if (audioPlayer != nullptr) {
        delete audioPlayer;
        audioPlayer = nullptr;
    }
}