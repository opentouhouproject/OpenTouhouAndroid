/**
 * Stores the engine created by OpenSL ES.
 */

#ifndef SRC_AUDIOENGINE_H

#define SRC_AUDIOENGINE_H

#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>

class AudioEngine
{
  public:
    AudioEngine();

    void createEngine();
    SLEngineItf getInterface();

    void close();

  private:
    SLresult result;

    SLObjectItf engineObject;
    SLEngineItf engineInterface;
};

#endif //SRC_AUDIOENGINE_H