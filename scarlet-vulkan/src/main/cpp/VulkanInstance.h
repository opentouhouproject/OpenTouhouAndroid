/**
 * Encapsulates the Vulkan Instance object.
 */
#ifndef OPENTOUHOUANDROID_VULKANINSTANCE_H
#include <android/log.h>
#include <vulkan/vulkan.h>
#include <vector>
#define OPENTOUHOUANDROID_VULKANINSTANCE_H

namespace scarlet_vulkan {
    class VulkanInstance {
    public:
        void create();
        void cleanup();

    private:
        std::vector<const char *> getRequiredExtensions();

        VkInstance instance;
    };
}

#endif //OPENTOUHOUANDROID_VULKANINSTANCE_H