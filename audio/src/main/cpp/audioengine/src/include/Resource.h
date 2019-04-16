/*
 * Manages resources.
 */

#ifndef SRC_RESOURCEMANAGER_H
#define SRC_RESOURCEMANAGER_H

#include <pty.h>
#include <android/log.h>
#include <android/asset_manager.h>

struct ResourceDescriptor {
    int32_t descriptor;

    off_t start;
    off_t length;
};

class ResourceManager {
    public:
        ResourceManager(AAssetManager* assetManager);
        ~ResourceManager();

        ResourceDescriptor descript(const char* filePath);

    private:
        AAssetManager* assetManager;
};

#endif //SRC_RESOURCE_H