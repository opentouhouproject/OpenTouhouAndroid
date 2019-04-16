/*
 * Manages resources.
 */

#ifndef SRC_RESOURCE_H
#define SRC_RESOURCE_H

#include <pty.h>
#include <android/log.h>
#include <android/asset_manager.h>

struct ResourceDescriptor {
    int32_t descriptor;

    off_t start;
    off_t length;
};

class Resource {
    public:
        Resource(AAssetManager* assetManager, const char* filePath);
        ~Resource();

        void openAsset();
        void closeAsset();

        void read(u_int8_t* buffer, off_t length);

        ResourceDescriptor getFileDescriptor();

        const char* getPath();

        off_t length();

    private:
        AAssetManager* manager;
        AAsset* asset;
        const char* path;
        off_t size;
};

#endif //SRC_RESOURCE_H