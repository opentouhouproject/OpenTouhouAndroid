/*
 * Loads and plays audio files.
 */

#ifndef SRC_AUDIOPLAYER_H
#define SRC_AUDIOPLAYER_H

#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>

#include "Resource.h"
#include "OutputMixer.h"

class AudioPlayer {
    public:
        AudioPlayer();
        ~AudioPlayer();

        void create(SLEngineItf audioEngineInterface);
        void close();

        // Settings
        void setDataLocatorIn(ResourceDescriptor desc);
        void setDataFormat();
        void setDataSource();
        void setDataLocatorOut(OutputMixer* outputMixer);
        void setDataSink();

        // Player IIDs
        void enablePlay(bool isEnabled);
        void enableSeek(bool isEnabled);

        // Playing audio.
        void play();

    private:
        SLObjectItf audioPlayer;
        SLPlayItf playInterface;
        SLSeekItf seekInterface;

        // Settings here
        SLDataLocator_AndroidFD dataLocatorIn;
        SLDataFormat_MIME dataFormat;
        SLDataSource dataSource;
        SLDataLocator_OutputMix dataLocatorOut;
        SLDataSink dataSink;

        // Player IIDs
        SLboolean isPlayEnabled;
        SLboolean isSeekEnabled;
};

#endif //SRC_AUDIOPLAYER_H