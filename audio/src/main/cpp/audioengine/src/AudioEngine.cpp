/**
 * Stores the engine created by OpenSL ES.
 */

#include "include/AudioEngine.h"

// Constructor(s)
AudioEngine::AudioEngine() {
    result = 1;
    engineObject = nullptr;
    engineInterface = nullptr;
}

// Destructor(s)
AudioEngine::~AudioEngine() {
    close();
}

// Getter(s)
SLEngineItf AudioEngine::getInterface() {
    return engineInterface;
};

// Starts the audio engine.
void AudioEngine::create() {
    /*
     * Create an engine object.
     * This is the entry point of the OpenSL ES API.
     *
     * Arguments:
     * (1) pointer to SLObjectItf (engine)
     * (2) number of options
     * (3) engine options
     * (4) number of interfaces
     * (5) interface ids
     * (6) interface required
     */
    result = slCreateEngine(&engineObject, 0, nullptr, 0, nullptr, nullptr);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to create audio engine.");
    }

    /*
     * Realize the engine object.
     *
     * Arguments
     * (1) engine object
     * (2) Is async.
     */
    result = (*engineObject)->Realize(engineObject, SL_BOOLEAN_FALSE);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to realize audio engine.");
    }

    /*
     * Get the engine interface.
     *
     * Arguments
     * (1) engine object
     * (2) iid
     * (3) pointer to the interface
     */
    result = (*engineObject)->GetInterface(engineObject, SL_IID_ENGINE, &engineInterface);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to get audio engine interface.");
    }
}

// Close the engine object.
void AudioEngine::close() {
    if (engineObject != nullptr) {
        (*engineObject)->Destroy(engineObject);
        engineObject = nullptr;
    }
}