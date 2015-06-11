package com.giantcroissant.android_7funs;

import android.content.Context;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by liyihao on 15/6/11.
 */
public class ResponseListAdapter extends ArrayAdapter<ResponseOfQuestion> {
    // 畫面資源編號
    private int resource;
    // 包裝的記事資料
    private List<ResponseOfQuestion> responses;

    public ResponseListAdapter(Context context, int resource, List<ResponseOfQuestion> responses) {
        super(context, resource, responses);
        this.resource = resource;
        this.responses = responses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemView;
        // 讀取目前位置的記事物件
        final ResponseOfQuestion responses = getItem(position);

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
        TextView contentView = (TextView) itemView.findViewById(R.id.response_content);
        TextView dateView = (TextView) itemView.findViewById(R.id.response_create_time_text);
//        ImageView responseIconView = (ImageView) itemView.findViewById(R.id.response_icon);
//        TextView responseView = (TextView) itemView.findViewById(R.id.response_count);

        // 設定標題
        contentView.setText(responses.getOwnerName() + " " + responses.getContent(), TextView.BufferType.SPANNABLE);

        Spannable span = (Spannable) contentView.getText();
//        span.setSpan(new RelativeSizeSpan(0.8f), 0, questions.getOwnerName().length(), 0);
        span.setSpan(new ForegroundColorSpan(0xFFFF5522), 0, responses.getOwnerName().length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        // 設定日期時間
        dateView.setText(DateDistance.twoDateDistance(responses.getCreateTime(), new Date(System.currentTimeMillis())));

        return itemView;
    }

    // 設定指定編號的記事資料
    public void set(int index, ResponseOfQuestion question) {
        if (index >= 0 && index < responses.size()) {
            responses.set(index, question);
            notifyDataSetChanged();
        }
    }

    // 讀取指定編號的記事資料
    public ResponseOfQuestion get(int index) {
        return responses.get(index);
    }



}