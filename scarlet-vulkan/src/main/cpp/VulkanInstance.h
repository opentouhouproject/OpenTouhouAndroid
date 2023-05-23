/**
 * Encapsulates the Vulkan Instance object.
 */
#ifndef OPENTOUHOUANDROID_VULKANINSTANCE_H
#include <android/log.h>
#include <vulkan/vulkan.h>
#include <vector>
#include "Constants.h"
#include "DebugMessenger.h"
#define OPENTOUHOUANDROID_VULKANINSTANCE_H
namespace scarlet_vulkan {
    class VulkanInstance {
    public:
        static const bool enableValidationLayers = true;
        void create();
        void cleanup();
        VkInstance getInstance();

    private:
        std::vector<const char *> getRequiredExtensions();
        bool checkValidationLayerSupport();

        VkInstance instance;
    };
}
#endif