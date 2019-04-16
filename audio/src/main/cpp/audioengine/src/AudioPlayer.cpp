/**
 * Loads and plays audio files.
 */

#include "include/AudioPlayer.h"

// Constructor(s)
AudioPlayer::AudioPlayer() {
    audioPlayer = nullptr;
    playInterface = nullptr;
    seekInterface = nullptr;

    // Player IIDs
    isPlayEnabled = SL_BOOLEAN_FALSE;
    isSeekEnabled = SL_BOOLEAN_FALSE;
}

// Destructor
AudioPlayer::~AudioPlayer() {
    close();
}

// Create the audio player.
void AudioPlayer::create(SLEngineItf audioEngineInterface) {
    SLresult result;

    // Setup settings.
    const SLuint32 playerIIDCount = 2;
    const SLInterfaceID playerIIDs[] = { SL_IID_PLAY, SL_IID_SEEK };
    const SLboolean playerReqs[] = { isPlayEnabled, isSeekEnabled };

    // Create the audio player.
    result = (*audioEngineInterface)->CreateAudioPlayer(audioEngineInterface, &audioPlayer, &dataSource, &dataSink,
                                                        playerIIDCount, playerIIDs, playerReqs);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to create audio player.");
    }

    // Realize the audio player.
    result = (*audioPlayer)->Realize(audioPlayer, SL_BOOLEAN_FALSE);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to realize audio player.");
    }

    // Create the Play functionality.
    result = (*audioPlayer)->GetInterface(audioPlayer, SL_IID_PLAY, &playInterface);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to get the PLAY interface.");
    }

    // Create the Seek functionality.
    result = (*audioPlayer)->GetInterface(audioPlayer, SL_IID_SEEK, &seekInterface);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to get the SEEK interface.");
    }
}

// Close the audio player.
void AudioPlayer::close() {
    if (playInterface != nullptr) {
        SLuint32 playerState;

        (*audioPlayer)->GetState(audioPlayer, &playerState);

        if (playerState == SL_OBJECT_STATE_REALIZED) {
            (*playInterface)->SetPlayState(playInterface, SL_PLAYSTATE_PAUSED);
        }
    }

    if (audioPlayer != nullptr) {
        (*audioPlayer)->Destroy(audioPlayer);
        audioPlayer = nullptr;

        playInterface = nullptr;
        seekInterface = nullptr;
    }
}

// Play the audio.
void AudioPlayer::play() {
    SLresult result;

    result = (*seekInterface)->SetLoop(seekInterface, SL_BOOLEAN_TRUE, 0, SL_TIME_UNKNOWN);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to set audio loop.");
    }

    result = (*playInterface)->SetPlayState(playInterface, SL_PLAYSTATE_PLAYING);
    if (result != SL_RESULT_SUCCESS) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to set play state to playing.");
    }
}

// Settings
void AudioPlayer::setDataLocatorIn(ResourceDescriptor desc) {
    dataLocatorIn.locatorType = SL_DATALOCATOR_ANDROIDFD;
    dataLocatorIn.fd          = desc.descriptor;
    dataLocatorIn.offset      = desc.start;
    dataLocatorIn.length      = desc.length;
}

void AudioPlayer::setDataFormat() {
    dataFormat.formatType    = SL_DATAFORMAT_MIME;
    dataFormat.mimeType      = NULL;
    dataFormat.containerType = SL_CONTAINERTYPE_UNSPECIFIED;
}

void AudioPlayer::setDataSource() {
    dataSource.pLocator = &dataLocatorIn;
    dataSource.pFormat  = &dataFormat;
}

void AudioPlayer::setDataLocatorOut(OutputMixer* outputMixer) {
    dataLocatorOut.locatorType = SL_DATALOCATOR_OUTPUTMIX;
    dataLocatorOut.outputMix   = outputMixer->getSLObject();
}

void AudioPlayer::setDataSink() {
    dataSink.pLocator = &dataLocatorOut;
    dataSink.pFormat  = NULL;
}

// IIDs
void AudioPlayer::enablePlay(bool isEnabled) {
    if (isEnabled) {
        isPlayEnabled = SL_BOOLEAN_TRUE;
    } else {
        isPlayEnabled = SL_BOOLEAN_FALSE;
    }
}

void AudioPlayer::enableSeek(bool isEnabled) {
    if (isEnabled) {
        isSeekEnabled = SL_BOOLEAN_TRUE;
    } else {
        isSeekEnabled = SL_BOOLEAN_FALSE;
    }
}