/*
 * Plays audio from buffer.
 */

#ifndef SRC_AUDIOQUEUE_H
#define SRC_AUDIOQUEUE_H

#include "AudioBuffer.h"
#include "OutputMixer.h"

#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>

class AudioQueue {
    public:
        AudioQueue();
        ~AudioQueue();

        void create(SLEngineItf pEngine, OutputMixer* outputMixer);
        void destroy();

        // Settings
        void setDataLocatorIn();
        void setDataFormat();
        void setDataSource();
        void setDataLocatorOut(OutputMixer* outputMixer);
        void setDataSink();

        void playAudio(AudioBuffer* audioBuffer);

    private:
        SLObjectItf audioPlayer;
        SLPlayItf playInterface;
        SLBufferQueueItf bufferQueueInterface;

        // Settings
        SLDataLocator_AndroidSimpleBufferQueue dataLocatorIn;
        SLDataFormat_PCM dataFormat;
        SLDataSource dataSource;
        SLDataLocator_OutputMix dataLocatorOut;
        SLDataSink dataSink;
};

#endif //SRC_AUDIOQUEUE_H