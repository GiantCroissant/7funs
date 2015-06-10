package com.giantcroissant.android_7funs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;


public class QuestionAndResponseActivity extends ActionBarActivity {

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private TextView textView;
    private ListView questionListView;
    private ArrayList<Question> questionList;
    private QuestionListAdapter questionListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_and_reaponse);

        questionListView = (ListView) findViewById(R.id.list_question);
        questionList = new ArrayList<Question>();
        questionList.add(new Question("關於Android Tutorial的事情.", "Hello content", "xx", "xxx"));
        questionList.add(new Question( "一隻非常可愛的小狗狗!", "她的名字叫「大熱狗」，又叫\n作「奶嘴」，是一隻非常可愛\n的小狗。", "oo", "ooo"));
        questionListAdapter = new QuestionListAdapter(this, R.layout.question_list_item, questionList);
        questionListView.setAdapter(questionListAdapter);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.menu_back);
        toolbar.setNavigationContentDescription("返回");
        textView = (TextView) findViewById(R.id.tool_bar_title);
        textView.setText("問與答");
        processControllers();

    }

    private void processControllers() {
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuestionAndResponseActivity.this, MainActivity.class);
                startActivity(i);
            }

        };



        toolbar.setNavigationOnClickListener(listener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_and_reaponse, menu);
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
