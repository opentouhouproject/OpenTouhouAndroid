/**
 * Encapsulates the Vulkan debug messenger.
 * NDK version dependant.
 */
#ifndef OPENTOUHOUANDROID_DEBUGMESSENGER_H
#include <android/log.h>
#include <stdexcept>
#include <vulkan/vulkan.h>
#include "Constants.h"
#define OPENTOUHOUANDROID_DEBUGMESSENGER_H
namespace scarlet_vulkan {
    class DebugMessenger {
    public:
        static VKAPI_ATTR VkBool32 VKAPI_CALL debugCallback(VkDebugUtilsMessageSeverityFlagBitsEXT messageSeverity,
                                                            VkDebugUtilsMessageTypeFlagsEXT messageType,
                                                            const VkDebugUtilsMessengerCallbackDataEXT* pCallbackData,
                                                            void* pUserData) {
            __android_log_print(ANDROID_LOG_ERROR, "VK DEBUG SCARLET", "%s", pCallbackData->pMessage);
            return VK_FALSE;
        }

        void create(VkInstance instance);
        void cleanup(VkInstance instance);
        void populateDebugMessengerCreateInfo(VkDebugUtilsMessengerCreateInfoEXT& createInfo);

    private:
        VkResult createDebugUtilsMessengerEXT(VkInstance instance,
                                              const VkDebugUtilsMessengerCreateInfoEXT* pCreateInfo,
                                              const VkAllocationCallbacks* pAllocator,
                                              VkDebugUtilsMessengerEXT* pDebugMessenger);
        void destroyDebugUtilsMessengerEXT(VkInstance instance,
                                           VkDebugUtilsMessengerEXT debugMessenger,
                                           const VkAllocationCallbacks* pAllocator);

        VkDebugUtilsMessengerEXT debugMessenger;
    };
}
#endif //OPENTOUHOUANDROID_DEBUGMESSENGER_H