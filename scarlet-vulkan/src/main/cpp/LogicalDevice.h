/**
 * Encapsulates the logical device selection.
 *
 * Notes:
 * (1) VkQueue is implicitly destroyed when the VkDevice object they are retrieved from is destroyed.
 */
#ifndef OPENTOUHOUANDROID_LOGICALDEVICE_H
#include <vulkan/vulkan.h>
#include "Constants.h"
#include "PhysicalDevice.h"
#define OPENTOUHOUANDROID_LOGICALDEVICE_H
namespace scarlet_vulkan {
    class LogicalDevice {
    public:
        void create(PhysicalDevice physicalDevice);
        void cleanup();

    private:
        VkDevice device;
        VkQueue graphicsQueue;
    };
}
#endif