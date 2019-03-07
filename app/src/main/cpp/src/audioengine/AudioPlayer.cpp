/**
 * Loads and plays audio files.
 */

#include "../../include/audioengine/AudioPlayer.h"

// Constructor(s)
AudioPlayer::AudioPlayer() { }

// Create the audio player.
void AudioPlayer::create(SLEngineItf audioEngineInterface)
{
    SLresult result;

    // Setup settings.
    const SLuint32 playerIIDCount = 2;
    const SLInterfaceID playerIIDs[] = { SL_IID_PLAY, SL_IID_SEEK };
    const SLboolean playerReqs[] = { isPlayEnabled, isSeekEnabled };

    // Create the audio player.
    result = (*audioEngineInterface)->CreateAudioPlayer(audioEngineInterface, &audioPlayer, &dataSource, &dataSink,
                                                        playerIIDCount, playerIIDs, playerReqs);
    if (result != SL_RESULT_SUCCESS) __android_log_print(ANDROID_LOG_VERBOSE, "SL LIB ERR", "ERROR");

    // Realize the audio player.
    result = (*audioPlayer)->Realize(audioPlayer, SL_BOOLEAN_FALSE);
    if (result != SL_RESULT_SUCCESS) __android_log_print(ANDROID_LOG_VERBOSE, "SL LIB ERR", "ERROR");

    // Create the Play functionality.
    result = (*audioPlayer)->GetInterface(audioPlayer, SL_IID_PLAY, &audioPlayerPlay);
    if (result != SL_RESULT_SUCCESS) __android_log_print(ANDROID_LOG_VERBOSE, "SL LIB ERR", "ERROR");

    // Create the Seek functionality.
    result = (*audioPlayer)->GetInterface(audioPlayer, SL_IID_SEEK, &audioPlayerSeek);
    if (result != SL_RESULT_SUCCESS) __android_log_print(ANDROID_LOG_VERBOSE, "SL LIB ERR", "ERROR");
}

// Close the audio player.
void AudioPlayer::close()
{
    if (audioPlayer != NULL)
    {
        (*audioPlayer)->Destroy(audioPlayer);
        audioPlayer = NULL;
    }
}

void AudioPlayer::play()
{
    SLresult result;

    result = (*audioPlayerSeek)->SetLoop(audioPlayerSeek, SL_BOOLEAN_TRUE, 0, SL_TIME_UNKNOWN);
    if (result != SL_RESULT_SUCCESS) __android_log_print(ANDROID_LOG_VERBOSE, "SL LIB ERR", "ERROR");

    result = (*audioPlayerPlay)->SetPlayState(audioPlayerPlay, SL_PLAYSTATE_PLAYING);
    if (result != SL_RESULT_SUCCESS) __android_log_print(ANDROID_LOG_VERBOSE, "SL LIB ERR", "ERROR");
}

void AudioPlayer::stop()
{
    if (audioPlayerPlay != NULL)
    {
        SLuint32 playerState;

        (*audioPlayer)->GetState(audioPlayer, &playerState);

        if (playerState == SL_OBJECT_STATE_REALIZED)
        {
            (*audioPlayerPlay)->SetPlayState(audioPlayerPlay, SL_PLAYSTATE_PAUSED);

            (*audioPlayer)->Destroy(audioPlayer);

            audioPlayer = NULL;
            audioPlayerPlay = NULL;
            audioPlayerSeek = NULL;
        }
    }
}

// Settings
void AudioPlayer::setDataLocatorIn(ResourceDescriptor desc)
{
    dataLocatorIn.locatorType = SL_DATALOCATOR_ANDROIDFD;
    dataLocatorIn.fd          = desc.descriptor;
    dataLocatorIn.offset      = desc.start;
    dataLocatorIn.length      = desc.length;
}

void AudioPlayer::setDataFormat()
{
    dataFormat.formatType    = SL_DATAFORMAT_MIME;
    dataFormat.mimeType      = NULL;
    dataFormat.containerType = SL_CONTAINERTYPE_UNSPECIFIED;
}

void AudioPlayer::setDataSource()
{
    dataSource.pLocator = &dataLocatorIn;
    dataSource.pFormat  = &dataFormat;
}

void AudioPlayer::setDataLocatorOut(OutputMixer* outputMixer)
{
    dataLocatorOut.locatorType = SL_DATALOCATOR_OUTPUTMIX;
    dataLocatorOut.outputMix   = outputMixer->getSLObject();
}

void AudioPlayer::setDataSink()
{
    dataSink.pLocator = &dataLocatorOut;
    dataSink.pFormat  = NULL;
}

// IIDs
void AudioPlayer::enablePlay(bool isEnabled)
{
    if (isEnabled)
    {
        isPlayEnabled = SL_BOOLEAN_TRUE;
    }
    else
    {
        isPlayEnabled = SL_BOOLEAN_FALSE;
    }
}

void AudioPlayer::enableSeek(bool isEnabled)
{
    if (isEnabled)
    {
        isSeekEnabled = SL_BOOLEAN_TRUE;
    }
    else
    {
        isSeekEnabled = SL_BOOLEAN_FALSE;
    }
}