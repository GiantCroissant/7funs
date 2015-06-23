package com.giantcroissant.android_7funs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class ResponseActivity extends ActionBarActivity {

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private TextView textView;
    private ListView responseListView;
    private Question question;
    private String questionID;
    private ArrayList<ResponseOfQuestion> responseList;
    private ResponseListAdapter responseListAdapter;

    private Realm realm;
    private RealmQuery<QuestionRealm> questionQuery;
    private RealmResults<QuestionRealm> questionRealmResult;

    private TextView contentText;
    private TextView createTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        Intent intent = getIntent();
        getView();

        questionID = intent.getStringExtra("questionID");
        getRealm();


        responseListAdapter = new ResponseListAdapter(this, R.layout.response_list_item, responseList);
        responseListView.setAdapter(responseListAdapter);

        setToolbar();
        setButtonListener();
        setValueToView();

    }

    private void getView()
    {
        responseListView = (ListView) findViewById(R.id.list_response);
        toolbar = (Toolbar) findViewById(R.id.list_response_tool_bar); // Attaching the layout to the toolbar object
        contentText = (TextView) findViewById(R.id.question_content_text);
        createTimeText = (TextView) findViewById(R.id.question_content_create_time);
    }

    private void setToolbar()
    {
        setSupportActionBar(toolbar);
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.menu_back);
        toolbar.setNavigationContentDescription("返回");
        textView = (TextView) findViewById(R.id.tool_bar_title);
        textView.setText(question.getTitle());
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


    private void setValueToView()
    {
        contentText.setText(question.getOwnerName() + " " + question.getContent(), TextView.BufferType.SPANNABLE);

        Spannable span = (Spannable) contentText.getText();
//        span.setSpan(new RelativeSizeSpan(0.8f), 0, questions.getOwnerName().length(), 0);
        span.setSpan(new ForegroundColorSpan(0xFFFF5522), 0, question.getOwnerName().length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        createTimeText.setText(DateDistance.twoDateDistance(question.getCreateTime(), new Date(System.currentTimeMillis())));
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 如果被啟動的Activity元件傳回確定的結果
        if (resultCode == Activity.RESULT_OK) {

            // 通知資料已經改變，ListView元件才會重新顯示

            responseListAdapter.notifyDataSetChanged();


        }
    }

    private void getRealm()
    {
        realm = Realm.getInstance(this);
        questionQuery = realm.where(QuestionRealm.class);
        questionQuery.equalTo("Id", questionID);
        questionRealmResult = questionQuery.findAll();

        getQuestionData();
        getResponseList();
    }

    private void getQuestionData()
    {
        for (QuestionRealm questionRealm : questionRealmResult) {
            Question newQuestion = new Question(questionRealm.getId(),questionRealm.getTitle(),questionRealm.getContent(),questionRealm.getOwnerName(),questionRealm.getOwnerIconUrl());
            newQuestion.setCreateTime(questionRealm.getCreateTime());

            for (ResponseRealm responseRealm : questionRealm.getResponses()) {
                ResponseOfQuestion newResponse = new ResponseOfQuestion(responseRealm.getId(),responseRealm.getContent(),responseRealm.getOwnerName(),responseRealm.getOwnerIconUrl(),question, responseRealm.getCreateTime());
                newQuestion.addResponse(newResponse);
            }


            question = newQuestion;
        }
    }

    private  void getResponseList()
    {
        responseList = question.getResponseList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_response, menu);
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
