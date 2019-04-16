/*
 * Audio Buffer.
 */

#ifndef SRC_AUDIOBUFFER_H
#define SRC_AUDIOBUFFER_H

#include "Resource.h"

class AudioBuffer {
    public:
        explicit AudioBuffer(Resource* pResource);
        ~AudioBuffer();

        const char* getPath();
        uint8_t* getBuffer() { return buffer; };
        off_t getLength() { return length; };

        void load();
        void unload();

    private:
        Resource* resource;
        uint8_t* buffer;
        off_t length;
};

#endif //SRC_AUDIOBUFFER_H