/**
 * Encapsulates the physical device selection.
 *
 * Notes:
 * (1) VkPhysicalDevice is implicitly destroyed when the VkInstance object they are retrieved from is destroyed.
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
        VkPhysicalDevice getPhysicalDevice();
        uint32_t getGraphicsQueueIndex();

    private:
        VkPhysicalDevice device = VK_NULL_HANDLE;
        std::unique_ptr<QueueFamilyIndices> queueFamilyIndices = std::make_unique<QueueFamilyIndices>();

        bool isDeviceSuitable(VkPhysicalDevice device);
    };
}
#endif