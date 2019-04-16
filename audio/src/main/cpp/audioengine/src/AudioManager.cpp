/**
 * Class that manages the audio using OpenSL ES.
 * Provides a simpler way for us to interface with JAVA using JNI.
 */

#include "include/AudioManager.h"

// Constructor(s)
AudioManager::AudioManager() {
    assetManager = nullptr;

    audioEngine = new AudioEngine();
    outputMixer = new OutputMixer();

    audioPlayer = nullptr;

    currentAudioQueue = 0;
    bufferCount = 0;
}

// Destructor(s)
AudioManager::~AudioManager() {
    for (int32_t i = 0; i < bufferCount; ++i) {
        delete audioBuffers[i];
    }
    bufferCount = 0;

    delete outputMixer;
    delete audioEngine;
}

// Create the manager.
void AudioManager::create() {
    audioEngine->create();
    outputMixer->create(audioEngine->getInterface());

    for (int32_t i= 0; i < QUEUE_COUNT; ++i) {
        audioQueues[i].create(audioEngine->getInterface(), outputMixer);
    }

    for (int32_t i = 0; i < bufferCount; ++i) {
        audioBuffers[i]->load();
    }
}

// Close the manager.
void AudioManager::close() {
    for (int32_t i = 0; i < bufferCount; ++i) {
        audioBuffers[i]->unload();
    }

    for (int32_t i= 0; i < QUEUE_COUNT; ++i) {
        audioQueues[i].destroy();
    }

    outputMixer->close();
    audioEngine->close();
}

// Set the asset manager.
void AudioManager::setAssetManager(JNIEnv* env, jobject obj) {
    assetManager = AAssetManager_fromJava(env, obj);
}

// Play a BGM file.
void AudioManager::playBGM(const char* filePath) {
    __android_log_print(ANDROID_LOG_DEBUG, "Scarlet Audio", "Playing BGM");

    // Open the asset.
    Resource resource = Resource(assetManager, filePath);
    resource.openAsset();

    // Get the file descriptor.
    ResourceDescriptor desc = resource.getFileDescriptor();
    if (desc.descriptor < 0) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Resource descriptor is invalid.");
        return;
    }

    // Close the asset.
    resource.closeAsset();

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

// Register audio.
AudioBuffer* AudioManager::registerSound(const char* path) {
    __android_log_print(ANDROID_LOG_DEBUG, "Scarlet Audio", "Registering sound.");

    for (int32_t i = 0; i < bufferCount; ++i) {
        if (strcmp(path, audioBuffers[i]->getPath()) == 0) {
            __android_log_print(ANDROID_LOG_DEBUG, "Scarlet Audio", "Sound is already registered.");
            return audioBuffers[i];
        }
    }

    // Open the asset.
    Resource* resource = new Resource(assetManager, path);
    AudioBuffer* buffer = new AudioBuffer(resource);
    buffer->load();
    audioBuffers[bufferCount++] = buffer;
    __android_log_print(ANDROID_LOG_DEBUG, "Scarlet Audio", "Sound registered Buffer Count (%d)", bufferCount);

    return buffer;
}

void AudioManager::playSound(const char* path) {
    __android_log_print(ANDROID_LOG_DEBUG, "Scarlet Audio", "Playing sound.");

    __android_log_print(ANDROID_LOG_DEBUG, "Scarlet Audio", "Play Sound Buffer Count (%d)", bufferCount);
    for (int32_t i = 0; i < bufferCount; ++i) {
        if (strcmp(path, audioBuffers[i]->getPath()) == 0) {
            __android_log_print(ANDROID_LOG_DEBUG, "Scarlet Audio", "FOUND IT");

            int32_t currentQueue = ++currentAudioQueue;
            AudioQueue& queue = audioQueues[currentQueue % QUEUE_COUNT];
            queue.playAudio(audioBuffers[i]);
        } else {
            __android_log_print(ANDROID_LOG_DEBUG, "Scarlet Audio", "Not IT");
        }
    }
}