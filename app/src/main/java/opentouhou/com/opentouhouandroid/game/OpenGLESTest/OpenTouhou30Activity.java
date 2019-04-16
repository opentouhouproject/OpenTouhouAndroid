package opentouhou.com.opentouhouandroid.game.OpenGLESTest;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.scarlet.scene.Stage;

import opentouhou.com.opentouhouandroid.scene.stages.Compatible30.OpenGLES30Test;

public class OpenTouhou30Activity extends Activity {
    private OpenTouhou30View openTouhou30View;

    private Stage stage;

    /*
     * Called when activity is starting.
     * This is where most initialization should go.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullScreen();

        /*
         * Create the new game.
         */
        stage = new OpenGLES30Test("OpenGLES30Test", this);

        /*
         * Create a GLSurfaceView instance and set it as the ContentView for this Activity.
         */
        openTouhou30View = new OpenTouhou30View(this, stage);
        setContentView(openTouhou30View);
    }

    @Override
    protected void onDestroy() {
        Log.d("Activity Log","Exiting game activity.");

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("Activity Log","Pausing game activity.");

        super.onPause();
        openTouhou30View.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("Activity Log","Resuming game activity.");

        super.onResume();
        openTouhou30View.onResume();
    }

    @Override
    protected void onStop() {
        Log.d("Activity Log","Stopping game activity.");

        super.onStop();
    }

    public void setFullScreen() {
        /*
         * Get rid of the title bar.
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*
         * Get rid of navigation bar.
         */
        if ((Build.VERSION.SDK_INT > 11) && (Build.VERSION.SDK_INT < 19)) {
            getWindow().getDecorView().setSystemUiVisibility(View.GONE);
        }
        else if (Build.VERSION.SDK_INT >= 19) {
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        }
    }
}