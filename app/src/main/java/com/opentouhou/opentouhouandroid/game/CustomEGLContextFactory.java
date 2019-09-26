package com.opentouhou.opentouhouandroid.game;

import android.opengl.EGL14;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

public class CustomEGLContextFactory implements GLSurfaceView.EGLContextFactory {
  private int version;

  /*
   * Constructor(s).
   */
  public CustomEGLContextFactory(int version) {
        this.version = version;
    }

  /*
   * Implement GLSurfaceView.EGLContextFactory interface.
   */
  public EGLContext createContext(EGL10 egl, EGLDisplay display, EGLConfig eglConfig) {
    int[] attrib_list = {EGL14.EGL_CONTEXT_CLIENT_VERSION, version, EGL10.EGL_NONE };

    return egl.eglCreateContext(display, eglConfig, EGL10.EGL_NO_CONTEXT, attrib_list);
  }

  public void destroyContext(EGL10 egl, EGLDisplay display, EGLContext context) {
    egl.eglDestroyContext(display, context);
  }
}