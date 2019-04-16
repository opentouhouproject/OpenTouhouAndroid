#include "include/Resource.h"

/*
 * A resource.
 */

// Constructor(s)
ResourceManager::ResourceManager(AAssetManager* assetManager) {
    this->assetManager = assetManager;
}

// Destructor
ResourceManager::~ResourceManager() {
    // Asset manager is managed by Java object.
}

// Gets the file descriptor.
ResourceDescriptor ResourceManager::descript(const char* filePath) {
    int32_t desc = -1;
    off_t start = 0;
    off_t length = 0;
    ResourceDescriptor descriptor = { desc, start, length };

    AAsset* asset = AAssetManager_open(assetManager, filePath, AASSET_MODE_UNKNOWN);

    if (asset != nullptr) {
        descriptor.descriptor = AAsset_openFileDescriptor(asset, &descriptor.start, &descriptor.length);
        AAsset_close(asset);
    } else {
        __android_log_print(ANDROID_LOG_VERBOSE, "Scarlet Audio Lib ERR", "Failed to open asset. (%s)", filePath);
    }

    return descriptor;
}