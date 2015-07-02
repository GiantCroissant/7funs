package com.giantcroissant.android_7funs;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class CookBookDetialActivity extends ActionBarActivity implements YouTubeFragment.OnFragmentInteractionListener, ShowFragment.OnFragmentInteractionListener, CookBookPhotoTeachingFragment.OnFragmentInteractionListener, CookBookVideoTeachingFragment.OnFragmentInteractionListener,View.OnClickListener {

    CookBookPhotoTeachingFragment cookBookPhotoTeachingFragment;
    CookBookVideoTeachingFragment cookBookVideoTeachingFragment;

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private TextView textView;
    private ImageButton photoButton;
    private ImageButton videoButton;

    private String cookBookID;
    public CookBook cookBook;
    private int tabIndex = 0;


    private Realm realm;
    private RealmQuery<CookBookRealm> cookBookQuery;
    private RealmResults<CookBookRealm> cookBookRealmResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_book_detial);

        Intent intent = getIntent();
        getView();

        cookBookID = intent.getStringExtra("cookBookID");
        getRealm();


        setToolbar();
        setButtonListener();
        setDefaultFragment();
    }


    private void getView()
    {
        toolbar = (Toolbar) findViewById(R.id.cook_book_detial_tool_bar); // Attaching the layout to the toolbar object
        photoButton = (ImageButton) findViewById(R.id.photo_teaching_button);
        videoButton = (ImageButton) findViewById(R.id.video_teaching_button);
    }

    private void setToolbar()
    {
        setSupportActionBar(toolbar);
        setTitle("");
        toolbar.setNavigationIcon(R.drawable.menu_back);
        toolbar.setNavigationContentDescription("返回");
        textView = (TextView) findViewById(R.id.tool_bar_title);
        textView.setText(cookBook.getName());
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
        fragmentTransaction.add(R.id.teachingfragment, new CookBookPhotoTeachingFragment().newInstance(cookBook));
        fragmentTransaction.commit();
    }

    private void getRealm()
    {
        realm = Realm.getInstance(this);
        cookBookQuery = realm.where(CookBookRealm.class);
        cookBookQuery.equalTo("Id", cookBookID);
        cookBookRealmResult = cookBookQuery.findAll();
        getCookBookData();
    }

    private void getCookBookData()
    {
        for (CookBookRealm cookBookRealm : cookBookRealmResult) {
            CookBook newCookBook = new CookBook(cookBookRealm.getId(), cookBookRealm.getName(), cookBookRealm.getDescription(), cookBookRealm.getUrl(), cookBookRealm.getImageUrl(), cookBookRealm.getIngredient(), cookBookRealm.getSauce(), cookBookRealm.getStep(), cookBookRealm.getViewedPeopleCount(), cookBookRealm.getCollectedPeopleCount(), cookBookRealm.getIsCollected());
            newCookBook.setUploadTimestamp(cookBookRealm.getUploadTimestamp());

            cookBook = newCookBook;
        }
//        cookBook = new CookBook(UUID.randomUUID().toString(), "三色嫩煎鱈魚-白家豪師傅", "CH37東風電視台＿料理美食", "Http://xd.com", "Http://xd.com", "土雞半隻、大蒜十力、老薑一段、紅辣椒兩枝、九層塔一大把", "麻油1/3杯、米酒1杯、醬油1/3杯、冰糖1/2匙", "1.把雞肉切丁\n2.大蒜切末\n3.倒入鍋中\n4.小火慢熬",  9999, 9999, false);
    }


    @Override
    public void onClick(View v)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (v.getId() == R.id.photo_teaching_button && tabIndex != 0) {

            if (cookBookPhotoTeachingFragment == null)
            {
//                cookBookPhotoTeachingFragment = new CookBookPhotoTeachingFragment().newInstance(cookBook.getIngredient(),cookBook.getSauce(),cookBook.getStep(),cookBook.getViewedPeopleCount(),cookBook.getCollectedPeopleCount(),cookBook.getIsCollected(),cookBook);
                cookBookPhotoTeachingFragment = new CookBookPhotoTeachingFragment().newInstance(cookBook);
            }
            fragmentTransaction.replace(R.id.teachingfragment, cookBookPhotoTeachingFragment);
            textView.setText(cookBook.getName());
            photoButton.setImageResource(R.drawable.teaching_photo_selected);
            videoButton.setImageResource(R.drawable.teaching_video);
            tabIndex = 0;

        }

        if (v.getId() == R.id.video_teaching_button && tabIndex != 1) {

//            Intent i = new Intent(ShowAndCookActivity.this, CooksInfoActivity.class);
//            startActivity(i);
//            finish();
            if (cookBookVideoTeachingFragment == null)
            {
                cookBookVideoTeachingFragment = new CookBookVideoTeachingFragment().newInstance(cookBook);
            }

            fragmentTransaction.replace(R.id.teachingfragment, cookBookVideoTeachingFragment);
            textView.setText(cookBook.getName());
            photoButton.setImageResource(R.drawable.teaching_photo);
            videoButton.setImageResource(R.drawable.teaching_video_selected);
            tabIndex = 1;

        }

        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cook_book_detial, menu);
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
    public void onPhotoTeachingFragmentInteraction(String string) {
        // Do stuff
    }

    @Override
    public void onVideoTeachingFragmentInteraction(String string) {
        // Do different stuff
    }


    @Override
    public void onShowFragmentInteraction(String string) {

    }

    @Override
    public void onYouTubeFragmentInteraction(String string) {
        // Do different stuff
    }
}
