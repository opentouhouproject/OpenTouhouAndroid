package com.scarlet.vulkan

class NativeLib {

    /**
     * A native method that is implemented by the 'vulkan' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'vulkan' library on application startup.
        init {
            System.loadLibrary("vulkan")
        }
    }
}