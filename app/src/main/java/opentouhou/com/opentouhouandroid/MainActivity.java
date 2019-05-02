package opentouhou.com.opentouhouandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.Play_Button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
            }
        });

        Button quitButton = findViewById(R.id.Quit_Button);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    @Override
    // debug testing for button stacking on top of each other
    public void onConfigurationChanged(Configuration newConfiguration) {
        super .onConfigurationChanged(newConfiguration);

        String s = "";

        if(newConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //s = "Landscape Orientation\n";
        } else if (newConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //s = "Portrait Orientation\n";
        }

        //Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
