package com.bnrc.bnrcbus.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SortAdapter extends ArrayAdapter {

    private String[] strs = {"美食", "公交站", "地铁站","超市","厕所","景点","酒店"};
    private LayoutInflater inflater;
    private int res;

    public SortAdapter(Context context, int resource) {
        super(context, resource);
        inflater = LayoutInflater.from(context);
        res = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(res, null);

        TextView text = convertView.findViewById(android.R.id.text1);
        text.setText(getItem(position));
        text.setTextColor(Color.WHITE);
        text.setTextSize(20);
        text.setGravity(Gravity.CENTER);

        convertView.setBackgroundColor(Color.BLACK);

        return convertView;
    }

    @Override
    public String getItem(int position) {
        return strs[position];
    }

    @Override
    public int getCount() {
        return strs.length;
    }
}