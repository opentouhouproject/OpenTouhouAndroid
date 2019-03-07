/**
 * Manages resources.
 */

#ifndef SRC_RESOURCEMANAGER_H
#define SRC_RESOURCEMANAGER_H

#include <stdint.h>
#include <pty.h>
#include <android/log.h>
#include <android/asset_manager.h>

struct ResourceDescriptor
{
    int32_t descriptor;

    off_t start;
    off_t length;
};

class Resource
{
    public:
        Resource(AAssetManager* assetManager, const char* filePath);

        ResourceDescriptor descript();

    private:
        AAssetManager* assetManager;
        const char* filePath;
};

#endif //SRC_RESOURCEMANAGER_H