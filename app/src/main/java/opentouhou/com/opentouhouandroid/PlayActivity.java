package opentouhou.com.opentouhouandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import opentouhou.com.opentouhouandroid.game.OpenGLESTest.OpenGLESTestActivity;

public class PlayActivity extends AppCompatActivity {

    ListView gameSelection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create array adapter
        String[] mylist = {"OpenGL ES Test 1"};

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.textview_play_menu, mylist);

        // Create the list view and set the adapter
        gameSelection = (ListView) findViewById(R.id.GameSelection_ListView);
        gameSelection.setAdapter(adapter);

        // Set listener for the listview
        gameSelection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = gameSelection.getItemAtPosition(position);

                if ((String) listItem == "OpenGL ES Test 1") {
                    startActivity(new Intent(PlayActivity.this, OpenGLESTestActivity.class));
                }
            }
        });
    }
}