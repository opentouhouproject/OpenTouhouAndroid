/**
 * Encapsulates the queue families for a physical device.
 */
#ifndef OPENTOUHOUANDROID_QUEUEFAMILYINDICES_H
#include <vulkan/vulkan.h>
#include <optional>
#include <vector>
#define OPENTOUHOUANDROID_QUEUEFAMILYINDICES_H
namespace scarlet_vulkan {
    class QueueFamilyIndices {
    public:
        void findQueueFamilies(VkPhysicalDevice device);
        bool isComplete();
        uint32_t getGraphicsQueueIndex();

    private:
        std::optional<uint32_t> graphicsFamily;
    };
}
#endif