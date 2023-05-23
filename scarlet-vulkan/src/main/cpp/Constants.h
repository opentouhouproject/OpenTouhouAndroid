/**
 * File for defining constants.
 */
#ifndef OPENTOUHOUANDROID_CONSTANTS_H
#include <vector>
#define OPENTOUHOUANDROID_CONSTANTS_H
namespace scarlet_vulkan {
    inline static const char *APPLICATION_NAME = "Open Touhou";
    inline static const char *ENGINE_NAME = "Scarlet Vulkan";
    inline static const int MAJOR_VERSION = 1;
    inline static const int MINOR_VERSION = 0;
    inline static const int PATCH_VERSION = 0;
    inline static const bool enableValidationLayers = true;
    inline static const std::vector<const char*> validationLayers = {
            "VK_LAYER_KHRONOS_validation"
    };
}
#endif