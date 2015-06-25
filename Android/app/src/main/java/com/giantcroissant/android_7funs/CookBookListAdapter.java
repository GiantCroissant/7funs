package com.giantcroissant.android_7funs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by liyihao on 15/6/10.
 */
public class CookBookListAdapter extends ArrayAdapter<CookBook> {

    // 畫面資源編號
    private int resource;
    // 包裝的記事資料
    private List<CookBook> cookBooks;

    public CookBookListAdapter(Context context, int resource, List<CookBook> cookBooks) {
        super(context, resource, cookBooks);
        this.resource = resource;
        this.cookBooks = cookBooks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemView;
        // 讀取目前位置的記事物件
        final CookBook cookBook = getItem(position);
        if (convertView == null) {
            // 建立項目畫面元件
            itemView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater)
                    getContext().getSystemService(inflater);
            li.inflate(resource, itemView, true);
        }
        else {
            itemView = (LinearLayout) convertView;
        }

        // 讀取記事顏色、已選擇、標題與日期時間元件
        TextView titleView = (TextView) itemView.findViewById(R.id.cook_book_title_text);
        TextView lookCountView = (TextView) itemView.findViewById(R.id.cook_book_view_count_text);
        TextView collectCountView = (TextView) itemView.findViewById(R.id.collected_count_text);
        ImageButton collectCookBookButton = (ImageButton) itemView.findViewById(R.id.collect_cook_book_Button);

        // 設定標題
        titleView.setText(cookBook.getName());

        // 設定日期時間
        collectCountView.setText(String.valueOf(cookBook.getCollectedPeopleCount()));

        lookCountView.setText(String.valueOf(cookBook.getViewedPeopleCount()));


        collectCookBookButton.setImageResource(R.drawable.heart_outline);
        if(cookBook.getIsCollected())
        {
            collectCookBookButton.setImageResource(R.drawable.heart);
        }
        return itemView;
    }

    // 設定指定編號的記事資料
    public void set(int index, CookBook cookBook) {
        if (index >= 0 && index < cookBooks.size()) {
            cookBooks.set(index, cookBook);
            notifyDataSetChanged();
        }
    }

}
