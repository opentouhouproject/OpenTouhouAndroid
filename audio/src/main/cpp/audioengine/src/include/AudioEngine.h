/*
 * Stores the "engine" created by OpenSL ES.
 */

#ifndef SRC_AUDIOENGINE_H

#define SRC_AUDIOENGINE_H

#include <android/log.h>
#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>

class AudioEngine {
  public:
    AudioEngine();
    ~AudioEngine();

    SLEngineItf getInterface();

    void create();
    void close();

  private:
    SLresult result;
    SLObjectItf engineObject;
    SLEngineItf engineInterface;
};

#endif //SRC_AUDIOENGINE_H