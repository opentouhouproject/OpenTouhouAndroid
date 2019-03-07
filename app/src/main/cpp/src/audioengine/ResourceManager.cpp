/**
 * Manages resources.
 */

#include "../../include/audioengine/ResourceManager.h"

// Constructor(s)
Resource::Resource(AAssetManager* assetManager, const char* filePath)
{
    this->assetManager = assetManager;
    this->filePath = filePath;
}

// Get a file descriptor.
ResourceDescriptor Resource::descript()
{
    int32_t desc = -1;
    off_t start = 0;
    off_t length = 0;
    ResourceDescriptor descriptor = { desc, start, length };

    AAsset* asset = AAssetManager_open(assetManager, filePath, AASSET_MODE_UNKNOWN);

    if (asset != NULL)
    {
        descriptor.descriptor = AAsset_openFileDescriptor(asset, &descriptor.start, &descriptor.length);
        AAsset_close(asset);
    }
    else
    {
        //__android_log_print(ANDROID_LOG_VERBOSE, "SL LIB ERR", "Failed to open asset. %s", filePath);
    }

    return descriptor;
}