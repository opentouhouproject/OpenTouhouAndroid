/**
 * Backend Vulkan engine class.
 */
#ifndef OPENTOUHOUANDROID_ENGINE_H
#include <android/asset_manager.h>
#include <android/native_window.h>
#include <android/native_window_jni.h>
#include <memory>
#include "Constants.h"
#include "DebugMessenger.h"
#include "VulkanInstance.h"
#define OPENTOUHOUANDROID_ENGINE_H

namespace scarlet_vulkan {
    struct ANativeWindowDeleter {
        void operator()(ANativeWindow *window) { ANativeWindow_release(window); }
    };

    class Engine {
    public:
        void initVulkan();
        void render();
        void cleanup();
        void reset(ANativeWindow *newWindow, AAssetManager *newManager);

        bool initialized = false;

    private:
        std::unique_ptr<ANativeWindow, ANativeWindowDeleter> window;
        AAssetManager *assetManager;
        VulkanInstance vulkanInstance;
        DebugMessenger debugMessenger;
    };
}

#endif //OPENTOUHOUANDROID_ENGINE_H
