package opentouhou.com.opentouhouandroid.game.OpenGLESTest;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class OpenGLES30TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullScreen();

        /*
         * Create a GLSurfaceView instance and set it as the ContentView for this Activity.
         */
        setContentView(new OpenGLES30TestView(this));
    }

    @Override
    protected void onDestroy() {
        Log.d("Activity Log","Exiting game activity.");

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d("Activity Log","Pausing game activity.");

        super.onStop();
    }

    public void setFullScreen() {
        /*
         * Get rid of the title bar.
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*
         * Get rid of the action bar.
         */
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

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