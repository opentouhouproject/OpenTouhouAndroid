/**
 * Implementation of the LogicalDevice class.
 */
#include "LogicalDevice.h"

namespace scarlet_vulkan {
    void LogicalDevice::create(PhysicalDevice physicalDevice) {
        // Set the queue creation information.
        VkDeviceQueueCreateInfo queueCreateInfo{};
        queueCreateInfo.sType = VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO;
        queueCreateInfo.queueFamilyIndex = physicalDevice.getGraphicsQueueIndex();
        queueCreateInfo.queueCount = 1;

        // Assign the queue priorities.
        float queuePriority = 1.0f;
        queueCreateInfo.pQueuePriorities = &queuePriority;

        // Specify the device features that will be used.
        VkPhysicalDeviceFeatures deviceFeatures{};

        // Set the device creation information.
        VkDeviceCreateInfo createInfo{};
        createInfo.sType = VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO;
        createInfo.pQueueCreateInfos = &queueCreateInfo;
        createInfo.queueCreateInfoCount = 1;
        createInfo.pEnabledFeatures = &deviceFeatures;

        createInfo.enabledExtensionCount = 0;
        if (enableValidationLayers) {
            createInfo.enabledLayerCount = static_cast<uint32_t>(validationLayers.size());
            createInfo.ppEnabledLayerNames = validationLayers.data();
        } else {
            createInfo.enabledLayerCount = 0;
        }

        // Create the device.
        if (vkCreateDevice(physicalDevice.getPhysicalDevice(), &createInfo, nullptr, &device) != VK_SUCCESS) {
            throw std::runtime_error("Failed to create logical device!");
        }

        vkGetDeviceQueue(device, physicalDevice.getGraphicsQueueIndex(), 0, &graphicsQueue);
    }

    void LogicalDevice::cleanup() {
        vkDestroyDevice(device, nullptr);
    }
}