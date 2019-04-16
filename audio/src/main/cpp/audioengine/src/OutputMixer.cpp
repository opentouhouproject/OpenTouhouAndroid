#include "include/OutputMixer.h"

/*
 * Manages the OpenSL ES output mix.
 */

// Constructor(s)
OutputMixer::OutputMixer() {
    result = 1;
    outputMixer = nullptr;
    outputMixerInterface = nullptr;
}

// Destructor(s)
OutputMixer::~OutputMixer() {
    close();
}

// Create the output mixer.
void OutputMixer::create(SLEngineItf audioEngineInterface) {
    /*
     * Create the output mixer.
     *
     * Arguments
     * (1) audio engine interface
     * (2) output mixer
     * (3) IID Count
     * (4) IIDs
     * (5) bool
     */
    const SLuint32 outputMixIIDCount = 0;
    const SLInterfaceID outputMixIIDs[] = { };
    const SLboolean outputMixReqs[] = { };
    //const SLInterfaceID ids[1] = { SL_IID_ENVIRONMENTALREVERB };
    //const SLboolean req[1] = { SL_BOOLEAN_FALSE };
    result = (*audioEngineInterface)->CreateOutputMix(audioEngineInterface, &outputMixer, outputMixIIDCount, outputMixIIDs, outputMixReqs);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to create audio mixer.");
    }

    /*
     * Realize the output mixer.
     */
    result = (*outputMixer)->Realize(outputMixer, SL_BOOLEAN_FALSE);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to realize audio mixer.");
    }

    /*
     * Obtain the interface for the output mixer.
     */
    //result = (*outputMixer)->GetInterface(outputMixer, SL_IID_ENVIRONMENTALREVERB, &outputMixerInterface);
}

// Close the object.
void OutputMixer::close() {
    if (outputMixer != nullptr) {
        (*outputMixer)->Destroy(outputMixer);
        outputMixer = nullptr;
    }
}

SLObjectItf OutputMixer::getSLObject() {
    return outputMixer;
}