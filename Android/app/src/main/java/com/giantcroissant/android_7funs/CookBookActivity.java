package com.giantcroissant.android_7funs;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;


public class CookBookActivity extends ActionBarActivity {

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private TextView textView;
    private ListView cookBookListView;
    private CookBookListAdapter cookBookListAdapter;
    private ArrayList<CookBook> cookBookList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_book);

        createFakeData();

        getView();

        cookBookListAdapter = new CookBookListAdapter(this, R.layout.cook_book_list_item, cookBookList);
        cookBookListView.setAdapter(cookBookListAdapter);

        setToolbar();
        setButtonListener();
    }


    private void createFakeData()
    {
        cookBookList = new ArrayList<CookBook>();
        cookBookList.add(new CookBook(UUID.randomUUID().toString(), "三色嫩煎鱈魚-白家豪師傅", "CH37東風電視台＿料理美食", "Http://xd.com", "Http://xd.com", 9999, 9999, false));
        cookBookList.add(new CookBook(UUID.randomUUID().toString(), "三色嫩煎鮭魚-白家豪師傅", "CH37東風電視台＿料理美食", "Http://xd.com", "Http://xd.com", 9999, 9999, false));
    }

    private void getView()
    {
        cookBookListView = (ListView) findViewById(R.id.list_cook_book);
        toolbar = (Toolbar) findViewById(R.id.cook_book_list_tool_bar); // Attaching the layout to the toolbar object

    }

    private void setToolbar()
    {
        setSupportActionBar(toolbar);
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.menu_back);
        toolbar.setNavigationContentDescription("返回");
        textView = (TextView) findViewById(R.id.tool_bar_title);
        textView.setText("食譜列表");
    }

    private void setButtonListener() {

        View.OnClickListener navigationListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Intent i = new Intent(QuestionAndResponseActivity.this, MainActivity.class);
//                startActivity(i);
                finish();
            }

        };

        Toolbar.OnMenuItemClickListener menuItemListener = new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
//                if(!isLogin)
//                {
//                    isLogin = true;
//                    Intent i = new Intent(QuestionAndResponseActivity.this, LoginActivity.class);
//                    startActivity(i);
//                }
//                else
//                {
//                    Intent i = new Intent(QuestionAndResponseActivity.this, NewQuestionActivity.class);
//                    startActivity(i);
//                }

                return false;
            }

        };

        cookBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (view.getId() == R.id.collect_cook_book_Button) {
//                    cookBookList.get(position).setIsCollected(!cookBookList.get(position).getIsCollected());
//                    cookBookListAdapter = new CookBookListAdapter(CookBookActivity.this, R.layout.cook_book_list_item, cookBookList);
//                    cookBookListView.setAdapter(cookBookListAdapter);
//
//                    Log.e("Click", "Good");
//                }
                Intent intent = new Intent(CookBookActivity.this, MainActivity.class);

                startActivityForResult(intent, 0);
            }

        });

        toolbar.setOnMenuItemClickListener(menuItemListener);
        toolbar.setNavigationOnClickListener(navigationListener);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.collect_cook_book_Button) {
            View parentRow = (View) view.getParent();
            parentRow = (View) parentRow.getParent();
            parentRow = (View) parentRow.getParent();
            parentRow = (View) parentRow.getParent();
            ListView listView = (ListView) parentRow.getParent();
            final int position = listView.getPositionForView(parentRow);
        Log.d("XX",String.valueOf(position));
            cookBookList.get(position).setIsCollected(!cookBookList.get(position).getIsCollected());
            cookBookListAdapter = new CookBookListAdapter(CookBookActivity.this, R.layout.cook_book_list_item, cookBookList);
            cookBookListView.setAdapter(cookBookListAdapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cook_book, menu);
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
}
