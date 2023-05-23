/**
 * Implementation of the QueueFamilyIndices.
 */
#include "QueueFamilyIndices.h"

namespace scarlet_vulkan {
    /**
     * Determines the queue families for a physical device.
     * @param device - The physical device handle.
     * @return
     */
    void QueueFamilyIndices::findQueueFamilies(VkPhysicalDevice device) {
        // Get the number of queue families.
        uint32_t queueFamilyCount = 0;
        vkGetPhysicalDeviceQueueFamilyProperties(device, &queueFamilyCount, nullptr);

        // Enumerate the queue families.
        std::vector<VkQueueFamilyProperties> queueFamilies(queueFamilyCount);
        vkGetPhysicalDeviceQueueFamilyProperties(device, &queueFamilyCount, queueFamilies.data());

        // Find supporting queue family indices.
        int i = 0;
        for (const auto& queueFamily : queueFamilies) {
            if (queueFamily.queueFlags & VK_QUEUE_GRAPHICS_BIT) {
                this->graphicsFamily = i;
            }

            // Early break from loop.
            if (isComplete()) {
                break;
            }

            i++;
        }
    }

    /**
     * Checks if the physical device has all required queue families.
     * @return Boolean - True if all required queue families are found, false otherwise.
     */
    bool QueueFamilyIndices::isComplete() {
        return graphicsFamily.has_value();
    }
}