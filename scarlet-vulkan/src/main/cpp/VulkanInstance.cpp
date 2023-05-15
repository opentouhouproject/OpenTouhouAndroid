/**
 * Implementation of the VulkanInstance class.
 */
#include "VulkanInstance.h"

namespace scarlet_vulkan {
    void VulkanInstance::create() {
        // Get the required extensions.
        auto requiredExtensions = getRequiredExtensions();

        // Add application information.
        VkApplicationInfo appInfo{};
        appInfo.sType = VK_STRUCTURE_TYPE_APPLICATION_INFO;
        appInfo.pApplicationName = "Open Touhou";
        appInfo.applicationVersion = VK_MAKE_VERSION(1, 0, 0);
        appInfo.pEngineName = "Scarlet Vulkan";
        appInfo.engineVersion = VK_MAKE_VERSION(1, 0, 0);
        appInfo.apiVersion = VK_API_VERSION_1_0;

        // Add creation information.
        VkInstanceCreateInfo createInfo{};
        createInfo.sType = VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO;
        createInfo.pApplicationInfo = &appInfo;
        createInfo.enabledExtensionCount = (uint32_t)requiredExtensions.size();
        createInfo.ppEnabledExtensionNames = requiredExtensions.data();
        createInfo.enabledLayerCount = 0;

        // Create the Vulkan instance.
        VkResult result = vkCreateInstance(&createInfo, nullptr, &instance);
        if (result != VK_SUCCESS) {
            __android_log_print(ANDROID_LOG_ERROR, "TRACKERS", "%s", "Failed to create Vulkan instance!");
            throw std::runtime_error("Failed to create Vulkan instance!");
        }
    }

    void VulkanInstance::cleanup() {
        vkDestroyInstance(instance, nullptr);
    }

    std::vector<const char *> VulkanInstance::getRequiredExtensions() {
        std::vector<const char *> extensions;
        extensions.push_back("VK_KHR_surface");
        extensions.push_back("VK_KHR_android_surface");
        return extensions;
    }
}