package com.giantcroissant.android_7funs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseInstallation;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(this, "GLT7893zw7eXqcElTXIBCFX7hDjEy9iVlIDSkJey", "xClJ3IbLZzBy7ja0oLjanXFJ7E2Tsp6c4hzouu4L");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.questionButton) {

            Intent i = new Intent(MainActivity.this, QuestionAndResponseActivity.class);
            startActivity(i);

        }

        if (view.getId() == R.id.showAndCookButton) {

//            Intent i = new Intent(MainActivity.this, ShowInfoActivity.class);
//            Intent i = new Intent(MainActivity.this, CooksInfoActivity.class);
            Intent i = new Intent(MainActivity.this, ShowAndCookActivity.class);

            startActivity(i);

        }

        if (view.getId() == R.id.videoListButton) {

            Intent i = new Intent(MainActivity.this, VideoListActivity.class);
            startActivity(i);

        }

        if (view.getId() == R.id.cookBookButton) {

            Intent i = new Intent(MainActivity.this, CookBookActivity.class);
            startActivity(i);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
