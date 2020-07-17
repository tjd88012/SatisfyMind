package com.shcp.satisfymind;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DiaryAdapter extends BaseAdapter {

    ArrayList<DiaryVO> diarys;
    LayoutInflater inflater;

    public DiaryAdapter(ArrayList<DiaryVO> diarys, LayoutInflater inflater) {
        this.diarys = diarys;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return diarys.size();
    }

    @Override
    public Object getItem(int position) {
        return diarys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.diary, parent, false);
        }

        TextView tvDiary=convertView.findViewById(R.id.diary_note);
        TextView tvDate=convertView.findViewById(R.id.diary_date);

        DiaryVO diaryVO=diarys.get(position);

        tvDiary.setText(diaryVO.tvDiary);
        tvDate.setText(diaryVO.tvDate);


        return convertView;
    }
}
