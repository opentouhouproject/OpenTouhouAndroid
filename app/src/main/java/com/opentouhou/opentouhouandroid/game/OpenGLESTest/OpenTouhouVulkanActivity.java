package com.opentouhou.opentouhouandroid.game.OpenGLESTest;

import android.os.Build;
import android.os.Bundle;

import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.google.androidgamesdk.GameActivity;
import com.scarlet.vulkan.NativeLib;

public class OpenTouhouVulkanActivity extends GameActivity {
    static {
        System.loadLibrary("test-lib");
    }

    private NativeLib lib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideSystemUI();

        /*
         * Create a GLSurfaceView instance and set it as the ContentView for this Activity.
         */
        //setContentView(new OpenGLES20TestView(this));
        //lib = new NativeLib();
    }

    @Override
    protected void onDestroy() {
        Log.d("Activity Log", "Exiting game activity.");

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d("Activity Log", "Pausing game activity.");

        super.onStop();
    }

    private void hideSystemUI() {
        // This will put the game behind any cutouts and waterfalls on devices which have
        // them, so the corresponding insets will be non-zero.

        // We cannot guarantee that AndroidManifest won't be tweaked
        // and we don't want to crash if that happens so we suppress warning.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS;
        }
        View decorView = getWindow().getDecorView();
        WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), decorView);
        controller.hide(WindowInsetsCompat.Type.systemBars());
        controller.hide(WindowInsetsCompat.Type.displayCutout());
        controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
    }

    // Filter out back button press, and handle it here after native
    // side done its processing. Application can also make a reverse JNI
    // call to onBackPressed()/finish() at the end of the KEYCODE_BACK
    // processing.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean processed = super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            processed = true;
        }
        return processed;
    }

    @Override
    public void onBackPressed() {
        System.gc();
        System.exit(0);
    }
}
