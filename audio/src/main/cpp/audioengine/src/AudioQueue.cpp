#include "include/AudioQueue.h"

/*
 * Plays audio from buffer.
 */

AudioQueue::AudioQueue() {
    audioPlayer = nullptr;
    playInterface = nullptr;
    bufferQueueInterface = nullptr;
}

AudioQueue::~AudioQueue() {
    destroy();
}

void AudioQueue::create(SLEngineItf pEngine, OutputMixer* outputMixer) {
    SLresult result;

    setDataLocatorIn();
    setDataFormat();
    setDataSource();
    setDataLocatorOut(outputMixer);
    setDataSink();

    const SLuint32 soundPlayerIIDCount = 2;
    const SLInterfaceID soundPlayerIIDs[] = { SL_IID_PLAY, SL_IID_BUFFERQUEUE };
    const SLboolean soundPlayerReqs[] = { SL_BOOLEAN_TRUE, SL_BOOLEAN_TRUE };

    result = (*pEngine)->CreateAudioPlayer(pEngine, &audioPlayer, &dataSource, &dataSink, soundPlayerIIDCount, soundPlayerIIDs, soundPlayerReqs);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to create audio queue player.");
    }

    result = (*audioPlayer)->Realize(audioPlayer, SL_BOOLEAN_FALSE);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to realize audio player.");
    }

    result = (*audioPlayer)->GetInterface(audioPlayer, SL_IID_PLAY, &playInterface);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to get the PLAY interface.");
    }

    result = (*audioPlayer)->GetInterface(audioPlayer, SL_IID_BUFFERQUEUE, &bufferQueueInterface);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to get the BUFFERQUEUE interface.");
    }

    result = (*playInterface)->SetPlayState(playInterface, SL_PLAYSTATE_PLAYING);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to set PLAYSTATE to PLAYING.");
    }
}

void AudioQueue::destroy() {
    if (audioPlayer != nullptr) {
        (*audioPlayer)->Destroy(audioPlayer);

        audioPlayer = nullptr;
        playInterface = nullptr;
        bufferQueueInterface = nullptr;
    }
}

void AudioQueue::playAudio(AudioBuffer *audioBuffer) {
    SLresult result;
    SLuint32 playerState;

    (*audioPlayer)->GetState(audioPlayer, &playerState);
    __android_log_print(ANDROID_LOG_DEBUG, "Scarlet Audio", "OBJECT STATE (%d).", playerState);
    if (playerState == SL_OBJECT_STATE_REALIZED) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio", "Object Realized Playing Audio Queue.");

        auto* buffer = (int16_t*) audioBuffer->getBuffer();
        off_t length = audioBuffer->getLength();

        // Removes any sound from the queue.
        result = (*bufferQueueInterface)->Clear(bufferQueueInterface);
        if (result != SL_RESULT_SUCCESS) {
            __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to clear audio queue.");
        }

        // Plays the new sound.
        result = (*bufferQueueInterface)->Enqueue(bufferQueueInterface, buffer, static_cast<SLuint32>(length));
        if (result != SL_RESULT_SUCCESS) {
            __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to queue buffer for playing.");
        }
    }
}

void AudioQueue::setDataLocatorIn() {
    dataLocatorIn.locatorType = SL_DATALOCATOR_ANDROIDSIMPLEBUFFERQUEUE;
    dataLocatorIn.numBuffers = 1;
}

void AudioQueue::setDataFormat() {
    dataFormat.formatType = SL_DATAFORMAT_PCM;
    dataFormat.numChannels = 1;
    dataFormat.samplesPerSec = SL_SAMPLINGRATE_96;
    dataFormat.bitsPerSample = SL_PCMSAMPLEFORMAT_FIXED_16;
    dataFormat.containerSize = SL_PCMSAMPLEFORMAT_FIXED_16;
    dataFormat.channelMask = SL_SPEAKER_FRONT_CENTER;
    dataFormat.endianness = SL_BYTEORDER_LITTLEENDIAN;
}

void AudioQueue::setDataSource() {
    dataSource.pLocator = &dataLocatorIn;
    dataSource.pFormat = &dataFormat;
}

void AudioQueue::setDataLocatorOut(OutputMixer *outputMixer) {
    dataLocatorOut.locatorType = SL_DATALOCATOR_OUTPUTMIX;
    dataLocatorOut.outputMix   = outputMixer->getSLObject();
}

void AudioQueue::setDataSink() {
    dataSink.pLocator = &dataLocatorOut;
    dataSink.pFormat = NULL;
}