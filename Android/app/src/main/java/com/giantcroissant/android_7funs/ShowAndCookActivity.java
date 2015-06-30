package com.giantcroissant.android_7funs;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ShowAndCookActivity extends ActionBarActivity implements ShowFragment.OnFragmentInteractionListener, CooksFragment.OnFragmentInteractionListener,View.OnClickListener {

    ShowFragment showFragment;
    CooksFragment cooksFragment;

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_and_cook);

        getView();
        setToolbar();
        setButtonListener();
        setDefaultFragment();
    }


    private void getView()
    {
        toolbar = (Toolbar) findViewById(R.id.show_and_cook_tool_bar); // Attaching the layout to the toolbar object
    }

    private void setToolbar()
    {
        setSupportActionBar(toolbar);
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.menu_back);
        toolbar.setNavigationContentDescription("返回");
        textView = (TextView) findViewById(R.id.tool_bar_title);
        textView.setText("節目資訊");
    }


    private void setButtonListener() {

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

//                Intent i = new Intent(ResponseActivity.this, QuestionAndResponseActivity.class);
//                startActivity(i);
                finish();
            }

        };

        toolbar.setNavigationOnClickListener(listener);
    }


    private void setDefaultFragment()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contentfragment, new ShowFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v)
    {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (v.getId() == R.id.show_Info_button) {

//            Intent i = new Intent(ShowAndCookActivity.this, ShowInfoActivity.class);
//            startActivity(i);
//            finish();
            if (showFragment == null)
            {
                showFragment = new ShowFragment();
            }
            fragmentTransaction.replace(R.id.contentfragment, showFragment);
            textView.setText("節目資訊");

        }

        if (v.getId() == R.id.cook_info_button) {

//            Intent i = new Intent(ShowAndCookActivity.this, CooksInfoActivity.class);
//            startActivity(i);
//            finish();
            if (cooksFragment == null)
            {
                cooksFragment = new CooksFragment();
            }

            fragmentTransaction.replace(R.id.contentfragment, cooksFragment);
            textView.setText("料理老師");

        }

        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_and_cook, menu);
        return true;
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

    @Override
    public void onShowFragmentInteraction(String string) {
        // Do stuff
    }

    @Override
    public void onCooksFragmentInteraction(String string) {
        // Do different stuff
    }
}
