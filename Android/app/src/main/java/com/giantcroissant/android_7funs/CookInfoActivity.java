package com.giantcroissant.android_7funs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class CookInfoActivity extends ActionBarActivity {

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private TextView textView;

    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private CookViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);

        getView();
        setToolbar();
        setButtonListener();

//        mPager = (ViewPager) findViewById(R.id.cookPager);
//        adapter = new CookViewPagerAdapter(CookInfoActivity.this,images);
//        mPager.setAdapter(adapter);
    }

    private void getView()
    {
        toolbar = (Toolbar) findViewById(R.id.cook_tool_bar); // Attaching the layout to the toolbar object
    }

    private void setToolbar()
    {
        setSupportActionBar(toolbar);
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.menu_back);
        toolbar.setNavigationContentDescription("返回");
        textView = (TextView) findViewById(R.id.tool_bar_title);
        textView.setText("料理老師");
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

    public void onClick(View view) {
        if (view.getId() == R.id.show_Info_button) {

            Intent i = new Intent(CookInfoActivity.this, ShowInfoActivity.class);
            startActivity(i);
            finish();

        }

        if (view.getId() == R.id.cook_info_button) {
//
//            Intent i = new Intent(CookInfoActivity.this, CookInfoActivity.class);
//            startActivity(i);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cook, menu);
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

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new CooksFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
