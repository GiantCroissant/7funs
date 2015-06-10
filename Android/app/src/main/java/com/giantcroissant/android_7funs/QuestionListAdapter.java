package com.giantcroissant.android_7funs;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liyihao on 15/6/10.
 */
public class QuestionListAdapter extends ArrayAdapter<Question> {

    // 畫面資源編號
    private int resource;
    // 包裝的記事資料
    private List<Question> questions;

    public QuestionListAdapter(Context context, int resource, List<Question> questions) {
        super(context, resource, questions);
        this.resource = resource;
        this.questions = questions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemView;
        // 讀取目前位置的記事物件
        final Question questions = getItem(position);

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
        TextView titleView = (TextView) itemView.findViewById(R.id.question_title);
        TextView dateView = (TextView) itemView.findViewById(R.id.create_time_text);

        // 設定標題與日期時間
        titleView.setText(questions.getOwnerName() + questions.getTitle());
        dateView.setText(questions.getLocaleDatetime());

        return itemView;
    }

    // 設定指定編號的記事資料
    public void set(int index, Question question) {
        if (index >= 0 && index < questions.size()) {
            questions.set(index, question);
            notifyDataSetChanged();
        }
    }

    // 讀取指定編號的記事資料
    public Question get(int index) {
        return questions.get(index);
    }

}
