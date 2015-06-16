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
import android.widget.TextView.BufferType;

import java.util.Date;
import java.util.List;

/**
 * Created by liyihao on 15/6/10.
 */
public class VideoListAdapter extends ArrayAdapter<VideoInfo> {

    // 畫面資源編號
    private int resource;
    // 包裝的記事資料
    private List<VideoInfo> videoInfos;

    public VideoListAdapter(Context context, int resource, List<VideoInfo> videoInfos) {
        super(context, resource, videoInfos);
        this.resource = resource;
        this.videoInfos = videoInfos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemView;
        // 讀取目前位置的記事物件
        final VideoInfo videoInfo = getItem(position);

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
        TextView titleView = (TextView) itemView.findViewById(R.id.video_title);
        TextView contentView = (TextView) itemView.findViewById(R.id.video_content_text);
        TextView dateView = (TextView) itemView.findViewById(R.id.video_create_time_text);
        TextView lookView = (TextView) itemView.findViewById(R.id.video_look_count);

        // 設定標題
        titleView.setText(videoInfo.getName());
        contentView.setText(videoInfo.getDescription());

        // 設定日期時間
        dateView.setText(DateDistance.twoDateDistance(videoInfo.getUploadTimestamp(), new Date(System.currentTimeMillis())));

        lookView.setText(String.valueOf(videoInfo.getViewTimes()));

        return itemView;
    }

    // 設定指定編號的記事資料
    public void set(int index, VideoInfo videoInfo) {
        if (index >= 0 && index < videoInfos.size()) {
            videoInfos.set(index, videoInfo);
            notifyDataSetChanged();
        }
    }

}
