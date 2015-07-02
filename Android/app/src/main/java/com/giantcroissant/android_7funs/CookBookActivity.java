package com.giantcroissant.android_7funs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class CookBookActivity extends ActionBarActivity {

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private TextView textView;
    private ListView cookBookListView;
    private CookBookListAdapter cookBookListAdapter;
    private ArrayList<CookBook> cookBookList;

    private Realm realm;
    private RealmQuery<CookBookRealm> cookBookQuery;
    private RealmResults<CookBookRealm> cookBookRealmResult;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_book);

        getRealm();


        getView();

        cookBookListAdapter = new CookBookListAdapter(this, R.layout.cook_book_list_item, cookBookList);
        cookBookListView.setAdapter(cookBookListAdapter);

        setToolbar();
        setButtonListener();
    }

    private void getRealm()
    {
        realm = Realm.getInstance(this);
        cookBookQuery = realm.where(CookBookRealm.class);
        cookBookRealmResult = cookBookQuery.findAll();
        if(cookBookRealmResult.size() < 1)
        {
            createFakeData();
        }
        else
        {
            getCookBookListData();
        }

    }


    private void createFakeData()
    {
        cookBookList = new ArrayList<CookBook>();
        cookBookList.add(new CookBook(UUID.randomUUID().toString(), "三色嫩煎鱈魚-白家豪師傅", "CH37東風電視台＿料理美食", "Http://xd.com", "Http://xd.com", "土雞半隻、大蒜十力、老薑一段、紅辣椒兩枝、九層塔一大把", "麻油1/3杯、米酒1杯、醬油1/3杯、冰糖1/2匙", "1.先準備好所需材料雞肉丁表面劃斜線用[雞肉醃料]醃漬入味，其他材料處理乾淨切成適當大小備用。 2.準備一炒鍋用小火煸香花椒粒。 3.接著依序炒香乾辣椒、薑片及蒜頭。 4.然後加入醃漬入味的雞肉丁，拌炒至6~7分熟。 5.接著再加入所有[調味]快速拌勻。 6.然後加入蒜苗、辣椒翻炒均勻。 7.最後完成前再加入熟花生粒拌勻即可。8.夠勁又夠味的宮保雞丁就完成囉。", 9999, 9999, false));
        cookBookList.add(new CookBook(UUID.randomUUID().toString(), "三色嫩煎鮭魚-白家豪師傅", "CH37東風電視台＿料理美食", "Http://xd.com", "Http://xd.com", "土雞半隻、大蒜十力、老薑一段、紅辣椒兩枝、九層塔一大把", "麻油1/3杯、米酒1杯、醬油1/3杯、冰糖1/2匙", "1.先準備好所需材料雞肉丁表面劃斜線用[雞肉醃料]醃漬入味，其他材料處理乾淨切成適當大小備用。 2.準備一炒鍋用小火煸香花椒粒。 3.接著依序炒香乾辣椒、薑片及蒜頭。 4.然後加入醃漬入味的雞肉丁，拌炒至6~7分熟。 5.接著再加入所有[調味]快速拌勻。 6.然後加入蒜苗、辣椒翻炒均勻。 7.最後完成前再加入熟花生粒拌勻即可。8.夠勁又夠味的宮保雞丁就完成囉。",  9999, 9999, false));
        cookBookList.add(new CookBook(UUID.randomUUID().toString(), "三色嫩煎鮪魚-白家豪師傅", "CH37東風電視台＿料理美食", "Http://xd.com", "Http://xd.com", "土雞半隻、大蒜十力、老薑一段、紅辣椒兩枝、九層塔一大把", "麻油1/3杯、米酒1杯、醬油1/3杯、冰糖1/2匙", "1.先準備好所需材料雞肉丁表面劃斜線用[雞肉醃料]醃漬入味，其他材料處理乾淨切成適當大小備用。 2.準備一炒鍋用小火煸香花椒粒。 3.接著依序炒香乾辣椒、薑片及蒜頭。 4.然後加入醃漬入味的雞肉丁，拌炒至6~7分熟。 5.接著再加入所有[調味]快速拌勻。 6.然後加入蒜苗、辣椒翻炒均勻。 7.最後完成前再加入熟花生粒拌勻即可。8.夠勁又夠味的宮保雞丁就完成囉。",  9999, 9999, false));

        for (CookBook cookBook : cookBookList) {
            realm.beginTransaction();

            CookBookRealm cookBookRealm = realm.createObject(CookBookRealm.class);

            cookBookRealm.setId(cookBook.getId());
            cookBookRealm.setName(cookBook.getName());
            cookBookRealm.setDescription(cookBook.getDescription());
            cookBookRealm.setIngredient(cookBook.getIngredient());
            cookBookRealm.setSauce(cookBook.getSauce());
            cookBookRealm.setStep(cookBook.getStep());
            cookBookRealm.setUrl(cookBook.getUrl());
            cookBookRealm.setImageUrl(cookBook.getImageUrl());
            cookBookRealm.setViewedPeopleCount(cookBook.getViewedPeopleCount());
            cookBookRealm.setCollectedPeopleCount(cookBook.getCollectedPeopleCount());
            cookBookRealm.setIsCollected(cookBook.getIsCollected());
            cookBookRealm.setUploadTimestamp(cookBook.getUploadTimestamp());
            cookBookRealm.setCookId("123456789");

            realm.commitTransaction();
        }
    }

    private void getCookBookListData()
    {
        cookBookList = new ArrayList<CookBook>();
        for (CookBookRealm cookBookRealm : cookBookRealmResult) {
            CookBook newCookBook = new CookBook(cookBookRealm.getId(),cookBookRealm.getName(),cookBookRealm.getDescription(),cookBookRealm.getUrl(),cookBookRealm.getImageUrl(),cookBookRealm.getIngredient(),cookBookRealm.getSauce(),cookBookRealm.getStep(), cookBookRealm.getViewedPeopleCount(), cookBookRealm.getCollectedPeopleCount(),cookBookRealm.getIsCollected());
            newCookBook.setUploadTimestamp(cookBookRealm.getUploadTimestamp());
            cookBookList.add(newCookBook);
        }
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

                Intent intent = new Intent(CookBookActivity.this, CookBookDetialActivity.class);

                intent.putExtra("position", position);
                intent.putExtra("cookBookID", cookBookList.get(position).getId());

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
            Log.d("itemPosition",String.valueOf(position));

            RealmQuery<CookBookRealm> beCollectedCookBookQuery;
            RealmResults<CookBookRealm> beCollectedCookBookRealmResult;
            beCollectedCookBookQuery = realm.where(CookBookRealm.class);
            beCollectedCookBookQuery.equalTo("Id", cookBookList.get(position).getId());
            beCollectedCookBookRealmResult  = beCollectedCookBookQuery.findAll();

            realm.beginTransaction();
            beCollectedCookBookRealmResult.get(0).setIsCollected(!cookBookList.get(position).getIsCollected());
            realm.commitTransaction();

            cookBookList.get(position).setIsCollected(beCollectedCookBookRealmResult.get(0).getIsCollected());
//            cookBookList.get(position).setIsCollected(!cookBookList.get(position).getIsCollected());
            updateSingleRow(cookBookListView, position);


//            cookBookListAdapter = new CookBookListAdapter(CookBookActivity.this, R.layout.cook_book_list_item, cookBookList);
//            cookBookListView.setAdapter(cookBookListAdapter);
        }
        else if (view.getId() == R.id.cook_book_image) {
            View parentRow = (View) view.getParent();
            parentRow = (View) parentRow.getParent();
            parentRow = (View) parentRow.getParent();
            ListView listView = (ListView) parentRow.getParent();
            final int position = listView.getPositionForView(parentRow);
            Log.d("itemPosition", String.valueOf(position));

            Intent intent = new Intent(CookBookActivity.this, CookBookDetialActivity.class);

            intent.putExtra("position", position);
            intent.putExtra("cookBookID", cookBookList.get(position).getId());

            startActivityForResult(intent, 0);
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

    private void updateSingleRow(ListView listView, int position) {

        int start = listView.getFirstVisiblePosition();
        for(int i=start, j=listView.getLastVisiblePosition();i<=j;i++)
            if(position == i){
                View view = listView.getChildAt(i-start);
                listView.getAdapter().getView(i, view, listView);
                break;
            }
    }
}
