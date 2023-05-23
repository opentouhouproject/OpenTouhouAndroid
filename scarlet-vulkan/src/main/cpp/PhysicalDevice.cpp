/**
 * Implementation of the PhysicalDevice class.
 */
#include "PhysicalDevice.h"

namespace scarlet_vulkan {
    /**
     * Selects the first supported physical device.
     */
    void PhysicalDevice::pickPhysicalDevice(VkInstance instance) {
        // Get the number of physical devices.
        uint32_t deviceCount = 0;
        vkEnumeratePhysicalDevices(instance, &deviceCount, nullptr);

        // There are no devices supporting Vulkan.
        if (deviceCount == 0) {
            throw std::runtime_error("Failed to find GPUs with Vulkan support!");
        }

        // Enumerate the physical device handles.
        std::vector<VkPhysicalDevice> devices(deviceCount);
        vkEnumeratePhysicalDevices(instance, &deviceCount, devices.data());

        // Iterate through each physical device and pick the first suitable device.
        for (const auto& device : devices) {
            if (isDeviceSuitable(device)) {
                this->device = device;
                break;
            }
        }

        // Check if a suitable physical device was found.
        if (device == VK_NULL_HANDLE) {
            throw std::runtime_error("Failed to find a suitable GPU!");
        }
    }

    /**
     * Checks if a given physical device is suitable.
     * @param device - The physical device to check.
     * @return Boolean - True if suitable, false otherwise.
     */
    bool PhysicalDevice::isDeviceSuitable(VkPhysicalDevice device) {
        // Get the queue family indices for the physical device.
        queueFamilyIndices->findQueueFamilies(device);

        // Check if all required queue families are supported.
        return queueFamilyIndices->isComplete();
    }
}