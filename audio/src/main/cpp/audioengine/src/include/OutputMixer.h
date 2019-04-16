/**
 * Manages the OpenSL ES output mix.
 */

#ifndef SRC_OUTPUTMIXER_H
#define SRC_OUTPUTMIXER_H

#include <SLES/OpenSLES.h>

#include <SLES/OpenSLES_Android.h>

class OutputMixer
{
    public:
        OutputMixer();

        void start(SLEngineItf audioEngineInterface);
        void close();

        SLObjectItf getSLObject();

    private:
        SLresult result;
        SLObjectItf outputMixer;
        SLEngineItf outputMixerInterface;
};

#endif //SRC_OUTPUTMIXER_H