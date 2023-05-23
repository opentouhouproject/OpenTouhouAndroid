/**
 * Encapsulates the physical device selection.
 */
#ifndef OPENTOUHOUANDROID_PHYSICALDEVICE_H
#include <vulkan/vulkan.h>
#include <stdexcept>
#include <vector>
#include "QueueFamilyIndices.h"
#define OPENTOUHOUANDROID_PHYSICALDEVICE_H
namespace scarlet_vulkan {
    class PhysicalDevice {
    public:
        void pickPhysicalDevice(VkInstance instance);

    private:
        VkPhysicalDevice device = VK_NULL_HANDLE;
        std::unique_ptr<QueueFamilyIndices> queueFamilyIndices = std::make_unique<QueueFamilyIndices>();

        bool isDeviceSuitable(VkPhysicalDevice device);
    };
}
#endif