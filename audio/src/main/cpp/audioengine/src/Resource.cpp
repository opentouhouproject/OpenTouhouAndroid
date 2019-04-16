#include "include/Resource.h"

/*
 * A resource.
 */

// Constructor(s)
Resource::Resource(AAssetManager* assetManager, const char* filePath) {
    manager = assetManager;
    asset = nullptr;
    path = filePath;
    size = 0;
}

// Destructor
Resource::~Resource() {
    closeAsset();
}

// Opens the asset.
void Resource::openAsset() {
    asset = AAssetManager_open(manager, path, AASSET_MODE_UNKNOWN);
    size = AAsset_getLength(asset);
}

// Closes the asset.
void Resource::closeAsset() {
    if (asset != nullptr) {
        AAsset_close(asset);

        asset = nullptr;
    }
}

// Reads the resource into a file buffer.
void Resource::read(u_int8_t* buffer, off_t length) {
    int numRead = 0;

    numRead = AAsset_read(asset, buffer, static_cast<size_t>(length));
    if (numRead <= 0) {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "Failed to read asset into buffer.");
    }
}

// Gets the file descriptor.
ResourceDescriptor Resource::getFileDescriptor() {
    int32_t desc = -1;
    off_t start = 0;
    off_t length = 0;
    ResourceDescriptor descriptor = { desc, start, length };

    if (asset != nullptr) {
        descriptor.descriptor = AAsset_openFileDescriptor(asset, &descriptor.start, &descriptor.length);
    } else {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio ERR", "No asset is opened. (%s)", path);
    }

    return descriptor;
}

const char* Resource::getPath() {
    return path;
}

off_t Resource::length() {
    return size;
}