# For more information about using CMake with Android Studio:
# https://d.android.com/studio/projects/add-native-code.html

# Set the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

# Sets the name of the library.
# Sets the library as a shared library.
# Provides a relative path to your source file(s).

add_library(test-lib SHARED
        test/JNIFunc.cpp
        test/TestJNI.cpp)

add_library(audio-engine-jni SHARED
        audioengine/src/AudioManager.cpp
        audioengine/src/AudioEngine.cpp
        audioengine/src/OutputMixer.cpp
        audioengine/src/AudioPlayer.cpp
        audioengine/src/ResourceManager.cpp
        audioengine/src/JNIMapping.cpp
        )


# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

# Sets the name of the path variable.
# Specifies the name of the NDK library that you want CMake to locate.
find_library(log-lib log)
find_library(opensles-lib OpenSLES)
find_library(android-lib android )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

# Specifies the target library.
# Links the target library to the log library
# included in the NDK.
target_link_libraries(test-lib ${log-lib})
target_link_libraries(audio-engine-jni ${log-lib} ${opensles-lib} ${android-lib})