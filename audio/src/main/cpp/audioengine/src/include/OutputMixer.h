/*
 * Manages the OpenSL ES output mix.
 */

#ifndef SRC_OUTPUTMIXER_H
#define SRC_OUTPUTMIXER_H

#include <android/log.h>
#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>

class OutputMixer {
    public:
        OutputMixer();
        ~OutputMixer();

        void create(SLEngineItf audioEngineInterface);
        void close();

        SLObjectItf getSLObject();

    private:
        SLresult result;
        SLObjectItf outputMixer;
        SLEngineItf outputMixerInterface;
};

#endif //SRC_OUTPUTMIXER_H