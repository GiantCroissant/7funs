package com.giantcroissant.android_7funs;

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


public class VideoListActivity extends ActionBarActivity {

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private TextView textView;
    private ListView videoListView;
    private VideoListAdapter videoListAdapter;
    private ArrayList<VideoInfo> videoInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        createFakeData();

        getView();

        videoListAdapter = new VideoListAdapter(this, R.layout.video_list_item, videoInfoList);
        videoListView.setAdapter(videoListAdapter);

        setToolbar();
        setButtonListener();
    }

    private void createFakeData()
    {
        videoInfoList = new ArrayList<VideoInfo>();
        videoInfoList.add(new VideoInfo(UUID.randomUUID().toString(), "美食料理王：蝦蝦的美味", "CH37東風電視台＿料理美食", "Http://xd.com", "Http://xd.com", 10, 9999));
        videoInfoList.add(new VideoInfo(UUID.randomUUID().toString(), "美食料理王：瓜瓜的美味", "CH37東風電視台＿料理美食", "Http://xd.com", "Http://xd.com", 20, 9999));
    }

    private void getView()
    {
        videoListView = (ListView) findViewById(R.id.list_video);
        toolbar = (Toolbar) findViewById(R.id.video_list_tool_bar); // Attaching the layout to the toolbar object

    }

    private void setToolbar()
    {
        setSupportActionBar(toolbar);
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.menu_back);
        toolbar.setNavigationContentDescription("返回");
        textView = (TextView) findViewById(R.id.tool_bar_title);
        textView.setText("節目列表");
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

        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Intent intent = new Intent(QuestionAndResponseActivity.this, ResponseActivity.class);
//
//                intent.putExtra("position", position);
//                intent.putExtra("questionID", questionList.get(position).getId());
//
//                startActivityForResult(intent, 0);
            }

        });

        toolbar.setOnMenuItemClickListener(menuItemListener);
        toolbar.setNavigationOnClickListener(navigationListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_list, menu);
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
