package com.giantcroissant.android_7funs;

import android.content.Context;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
        final Question question = getItem(position);

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
        ImageView responseIconView = (ImageView) itemView.findViewById(R.id.response_icon);
        TextView responseView = (TextView) itemView.findViewById(R.id.response_count);

        // 設定標題
        titleView.setText(question.getOwnerName() + " " + question.getTitle(), BufferType.SPANNABLE);

        Spannable span = (Spannable) titleView.getText();
//        span.setSpan(new RelativeSizeSpan(0.8f), 0, questions.getOwnerName().length(), 0);
        span.setSpan(new ForegroundColorSpan(0xFFFF5522), 0, question.getOwnerName().length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        // 設定日期時間
        dateView.setText(DateDistance.twoDateDistance(question.getCreateTime(), new Date(System.currentTimeMillis())));

        if(question.getResponseListCount() > 0)
        {
            responseView.setText(String.valueOf(question.getResponseListCount()) + " 篇回覆");
            responseIconView.setAlpha(255f);
        }
        else
        {
            responseView.setText("");
            responseIconView.setAlpha(0f);
        }

        return itemView;
    }

    // 設定指定編號的記事資料
    public void set(int index, Question question) {
        if (index >= 0 && index < questions.size()) {
            questions.set(index, question);
            notifyDataSetChanged();
        }
    }

}
