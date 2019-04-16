/**
 * Stores the engine created by OpenSL ES.
 */

#include "include/AudioEngine.h"

// Constructor(s)
AudioEngine::AudioEngine() { }

// Getter(s)
SLEngineItf AudioEngine::getInterface()
{
    return engineInterface;
};

// Starts the audio engine.
void AudioEngine::createEngine()
{
    /**
     * Create an engine object.
     * This is the entry point of the OpenSL ES API.
     *
     * Arguments
     * (1) pointer to SLObjectItf
     * (2) number of options
     * (3) engine options ()
     * (4) number of interfaces
     * (5) interface ids
     * (6) interface required
     */
    result = slCreateEngine(&engineObject, 0, NULL, 0, NULL, NULL);

    /**
     * Realize the engine object.
     *
     * Arguments
     * (1) engine object itself
     * (2) Is async.
     */
    result = (*engineObject)->Realize(engineObject, SL_BOOLEAN_FALSE);

    /**
     * Obtain the engine interface.
     *
     * Arguments
     * (1) engine object itself
     * (2) iid
     * (3) pointer to the interface
     */
    result = (*engineObject)->GetInterface(engineObject, SL_IID_ENGINE, &engineInterface);
}

// Close the object.
void AudioEngine::close()
{
    if (engineObject != NULL)
    {
        (*engineObject)->Destroy(engineObject);
        engineObject = NULL;
    }
}