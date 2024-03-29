package com.opentouhou.opentouhouandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.opentouhou.opentouhouandroid.game.OpenGLESTest.OpenGLES20TestActivity;
import com.opentouhou.opentouhouandroid.game.OpenGLESTest.OpenTouhou30Activity;
import opentouhou.com.opentouhouandroid.R;

public class PlayActivity extends AppCompatActivity {
  ListView gameSelection = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    /*
     * Set activity content from a layout resource.
     */
    setContentView(R.layout.activity_play_menu);

    /*
     * Set a Toolbar to act as an ActionBar for this activity.
     */
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /*
     * Create an array adapter with the menu items.
     */
    String[] mylist = {"OpenGL ES 2.0 Test", "OpenGL ES 3.0 Test"};
    ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.textview_play_menu, mylist);

    /*
     * Create the list view and set the adapter.
     */
    gameSelection = findViewById(R.id.GameSelection_ListView);
    gameSelection.setAdapter(adapter);

    /*
     * Set the listener for the listview.
     */
    gameSelection.setOnItemClickListener(getOnItemClickListener());
  }

  /*
   * Create the OnItemClickListener.
   */
  private AdapterView.OnItemClickListener getOnItemClickListener() {
    return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { onItemClickMethod(parent, view, position, id);
            }
        };
  }

  private void onItemClickMethod(AdapterView<?> parent, View view, int position, long id) {
    Object listItem = gameSelection.getItemAtPosition(position);

    if (listItem == "OpenGL ES 2.0 Test") {
      startActivity(new Intent(PlayActivity.this, OpenGLES20TestActivity.class));

    } else if (listItem == "OpenGL ES 3.0 Test") {
      startActivity(new Intent(PlayActivity.this, OpenTouhou30Activity.class));
    }
  }
}