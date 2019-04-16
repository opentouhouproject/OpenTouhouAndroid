#include "include/AudioBuffer.h"

/*
 * Audio Buffer.
 */

AudioBuffer::AudioBuffer(Resource* pResource) {
    resource = pResource;
    buffer = nullptr;
    length = 0;
}

AudioBuffer::~AudioBuffer() {
    delete resource;

    if (buffer != nullptr) {
        unload();
    }
}

void AudioBuffer::load() {
    resource->openAsset();

    length = resource->length();
    buffer = new uint8_t[length];
    resource->read(buffer, length);

    resource->closeAsset();
}

void AudioBuffer::unload() {
    delete[] buffer;
    buffer = nullptr;
    length = 0;
}

const char* AudioBuffer::getPath() { return resource->getPath(); }