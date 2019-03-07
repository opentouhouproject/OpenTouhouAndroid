/**
 * Manages the OpenSL ES output mix.
 */

#include "../../include/audioengine/OutputMixer.h"

// Constructor(s)
OutputMixer::OutputMixer() { }

// Create the output mixer.
void OutputMixer::start(SLEngineItf audioEngineInterface)
{
    /**
     * Create the output mixer.
     *
     * Arguments
     * (1) interface itself
     * (2) object
     * (3) int
     * (4) interface id
     * (5) bool
     */
    const SLuint32 outputMixIIDCount = 0;
    const SLInterfaceID outputMixIIDs[] = { };
    const SLboolean outputMixReqs[] = { };
    //const SLInterfaceID ids[1] = { SL_IID_ENVIRONMENTALREVERB };
    //const SLboolean req[1] = { SL_BOOLEAN_FALSE };
    result = (*audioEngineInterface)->CreateOutputMix(audioEngineInterface, &outputMixer, outputMixIIDCount, outputMixIIDs, outputMixReqs);

    /**
     * Realize the output mixer.
     */
    result = (*outputMixer)->Realize(outputMixer, SL_BOOLEAN_FALSE);

    /**
     * Obtain the interface for the output mixer.
     */
    //result = (*outputMixer)->GetInterface(outputMixer, SL_IID_ENVIRONMENTALREVERB, &outputMixerInterface);
}

// Close the object.
void OutputMixer::close()
{
    if (outputMixer != NULL)
    {
        (*outputMixer)->Destroy(outputMixer);
        outputMixer = NULL;
    }
}

SLObjectItf OutputMixer::getSLObject()
{
    return outputMixer;
}