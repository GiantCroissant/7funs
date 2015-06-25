package com.giantcroissant.android_7funs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class QuestionAndResponseActivity extends ActionBarActivity {

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private TextView textView;
    private ListView questionListView;
    private ArrayList<Question> questionList;
    private QuestionListAdapter questionListAdapter;

    private Realm realm;
    private RealmQuery<QuestionRealm> questionQuery;
    private RealmResults<QuestionRealm> questionRealmResult;

    private boolean isLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_and_response);

        getRealm();

        getView();

        questionListAdapter = new QuestionListAdapter(this, R.layout.question_list_item, questionList);
        questionListView.setAdapter(questionListAdapter);

        setToolbar();
        setButtonListener();

    }


    private void getRealm()
    {
        realm = Realm.getInstance(this);
        questionQuery = realm.where(QuestionRealm.class);
        questionRealmResult = questionQuery.findAll();

        if(questionRealmResult.size() < 1)
        {
            createFakeData();
        }
        else
        {
            getQuestionListData();
        }
    }

    private void createFakeData()
    {
        questionList = new ArrayList<Question>();
        questionList.add(new Question(UUID.randomUUID().toString(), "關於Android Tutorial的事情.", "Hello content", "Gina_12345", "xxx"));
        questionList.add(new Question(UUID.randomUUID().toString(), "一隻非常可愛的小狗狗!", "她的名字叫「大熱狗」，又叫作「奶嘴」，是一隻非常可愛的小狗。", "Gina_12345", "xxx"));
        questionList.get(0).addResponse(new ResponseOfQuestion(UUID.randomUUID().toString(), "我比較喜歡貓", "Gina_12345", "xxx", questionList.get(0) ,questionList.get(0).getCreateTime()));
        questionList.get(1).addResponse(new ResponseOfQuestion(UUID.randomUUID().toString(), "我肚子餓", "Gina_12345", "xxx", questionList.get(1), questionList.get(1).getCreateTime()));

        for (Question question : questionList) {
            realm.beginTransaction();
            QuestionRealm questionRealm = realm.createObject(QuestionRealm.class);
            questionRealm.setId(question.getId());
            questionRealm.setTitle(question.getTitle());
            questionRealm.setContent(question.getContent());
            questionRealm.setOwnerName(question.getOwnerName());
            questionRealm.setOwnerIconUrl(question.getOwnerIconUrl());
            questionRealm.setCreateTime(question.getCreateTime());

            for (ResponseOfQuestion responseOfQuestion : question.getResponseList()) {
                ResponseRealm responseRealm = realm.createObject(ResponseRealm.class);
                responseRealm.setId(responseOfQuestion.getId());
                responseRealm.setContent(responseOfQuestion.getContent());
                responseRealm.setOwnerName(responseOfQuestion.getOwnerName());
                responseRealm.setOwnerIconUrl(responseOfQuestion.getOwnerIconUrl());
                responseRealm.setCreateTime(responseOfQuestion.getCreateTime());
                responseRealm.setQuestion(questionRealm);

                questionRealm.getResponses().add(responseRealm);
            }
            realm.commitTransaction();
        }
    }

    private void getQuestionListData()
    {
        questionList = new ArrayList<Question>();
        for (QuestionRealm questionRealm : questionRealmResult) {
            Question newQuestion = new Question(questionRealm.getId(),questionRealm.getTitle(),questionRealm.getContent(),questionRealm.getOwnerName(),questionRealm.getOwnerIconUrl());
            newQuestion.setCreateTime(questionRealm.getCreateTime());
            questionList.add(newQuestion);
        }
    }


    private void getView()
    {
        questionListView = (ListView) findViewById(R.id.list_question);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object

    }

    private void setToolbar()
    {
        setSupportActionBar(toolbar);
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.menu_back);
        toolbar.setNavigationContentDescription("返回");
        textView = (TextView) findViewById(R.id.tool_bar_title);
        textView.setText("問與答");
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
                if(!isLogin)
                {
                    isLogin = true;
                    Intent i = new Intent(QuestionAndResponseActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(QuestionAndResponseActivity.this, NewQuestionActivity.class);
                    startActivity(i);
                }

                return false;
            }

        };

        questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(QuestionAndResponseActivity.this, ResponseActivity.class);

                intent.putExtra("position", position);
                intent.putExtra("questionID", questionList.get(position).getId());

                startActivityForResult(intent, 0);
            }

        });
        toolbar.setOnMenuItemClickListener(menuItemListener);
        toolbar.setNavigationOnClickListener(navigationListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 如果被啟動的Activity元件傳回確定的結果
        if (resultCode == Activity.RESULT_OK) {

            // 讀取標題
//            String titleText = data.getStringExtra("titleText");
            // 加入標題項目
//            this.data.add(titleText);

            // 通知資料已經改變，ListView元件才會重新顯示
            questionListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question_and_response, menu);
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
